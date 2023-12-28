import java.io.File;
import java.util.*;
class b {
  public static void main(String[] args) {
    String str = "";
    ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
    int count = 0;
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        count ++;
      }
      for(int i = 0; i<count;i++){
        map.add(new ArrayList<Integer>());
      }
      sc.close();
      sc = new Scanner(file);
      count = 0;
      while (sc.hasNextLine()){
        str = sc.nextLine();
        for(int i = 0; i<str.length();i++){
          if(str.charAt(i) == '.'){
              map.get(count).add(0);
          }
          if(str.charAt(i) == '#'){
              map.get(count).add(1);
          }
        }
        count ++;
      }
      /*for(int i = 0; i<map.size(); i++){
        for(int j = 0; j<map.get(i).size(); j++){
          if(map.get(i).get(j) == 0){
            System.out.print(".");
          }
          else if(map.get(i).get(j) == 1){
            System.out.print("#");
          }
        }
        System.out.println();
      }*/
      count = 0;
      Map m = new Map(map);
      //m.printmap();
      //System.out.println();
      while(true){
        System.out.println(++count);
        m.setdirection();
        //m.printmap();
        //System.out.println();
        if(!m.canmove()){
          break;
        }
        m.move();
        m.startdir = (m.startdir + 1) % 4;
      }
      /*count = 0;
      int maxx = 0;
      int maxy = 0;
      int minx = 100;
      int miny = 100;
      for(int i = 0; i<m.map.size(); i++){
        for(int j = 0; j<m.map.get(i).size(); j++){
          if(m.map.get(i).get(j) == 1){
            count ++;
            if(i<miny){
              miny = i;
            }
            if(i>maxy){
              maxy = i;
            }
            if(j<minx){
              minx = j;
            }
            if(j>maxx){
              maxx = j;
            }
          }
        }
      }
      System.out.println((maxx-minx+1)*(maxy-miny+1)-count);
      //m.printmap();*/
      System.out.println("Answer: " + count);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Map{
  ArrayList<ArrayList<Integer>> map;
  int startdir = 0;
  public Map(ArrayList<ArrayList<Integer>> m){
    map = m;
  }
  public void setdirection(){
    int count;
    int dir;
    for(int i = 0; i<map.size(); i++){
      for(int j = 0; j<map.get(i).size(); j++){
        if(map.get(i).get(j) == 1 && surronds(j,i)){
          //System.out.println("A");
          count = 0;
          dir = startdir;
          while(!setnumbers(dir,i,j) && count<4){
            //System.out.println(dir);
            count ++;
            dir = (dir + 1) % 4;
          }
        }
      }
    }
  }
  public boolean surronds(int x,int y){
    for(int i = -1; i<2; i++){
      for(int j = -1; j<2;j++){
        try{
          if(map.get(i+y).get(j+x) != 0 && !(i == 0 && j == 0)){
            return true;
          }
        }
        catch(Exception e){}
      }
    }
    //System.out.println("A");
    return false;
  }
  public boolean setnumbers(int num,int i,int j){
    if(num == 0){
      if(i == 0 ||((j == 0 || map.get(i-1).get(j-1) == 0) && map.get(i-1).get(j) == 0 && (j == map.get(0).size()-1 ||map.get(i-1).get(j+1) == 0))){
        map.get(i).set(j,2);
        return true;
      }
    }
    if(num == 1){
      if(i == map.size() - 1 ||((j == 0 || map.get(i+1).get(j-1) == 0) && map.get(i+1).get(j) == 0 && (j == map.get(0).size()-1 ||map.get(i+1).get(j+1) == 0))){
        map.get(i).set(j,3);
        return true;
      }
    }
    if(num == 2){
      if(j == 0 ||((i == 0 || map.get(i-1).get(j-1) == 0) && map.get(i).get(j-1) == 0 && (i == map.size()-1 ||map.get(i+1).get(j-1) == 0))){
        map.get(i).set(j,4);
        return true;
      }
    }
     if(num == 3){
      if(j == map.get(0).size() - 1 ||((i == 0 || map.get(i-1).get(j+1) == 0) && map.get(i).get(j+1) == 0 && (i == map.size()-1 ||map.get(i+1).get(j+1) == 0))){
        map.get(i).set(j,5);
        return true;
      }
    }
     return false;
  }
  public ArrayList<Integer> emptyarr(){
    ArrayList<Integer> temp = new ArrayList<Integer>();
    for(int i = 0; i<map.get(0).size();i++){
      temp.add(0);
    }
    return temp;
  }
  public void addcol(boolean first){
    for(int i = 0; i<map.size();i++){
      if(first){
        map.get(i).add(0,0);
      }
      else{
        map.get(i).add(0);
      }
    }
  }
  public void move(){
    for(int i = 0; i<map.size(); i++){
      for(int j = 0; j<map.get(i).size(); j++){
        if(map.get(i).get(j) != 0 || map.get(i).get(j) != 1){
          if(map.get(i).get(j) == 2){
            if(i == 0){
              map.add(0,emptyarr());
              i++;
            }
            map.get(i).set(j,0);
            map.get(i-1).set(j,1);
          }
          if(map.get(i).get(j) == 3){
            if(i == map.size()-1){
              map.add(emptyarr());
            }
            if(i<map.size()-2 && map.get(i+2).get(j) == 2){
              map.get(i).set(j,1);
              map.get(i+2).set(j,1);
            }
            else{
              map.get(i).set(j,0);
              map.get(i+1).set(j,1);
            }
          }
          if(map.get(i).get(j) == 4){
            if(j == 0){
              addcol(true);
              j++;
            }
            map.get(i).set(j,0);
            map.get(i).set(j-1,1);
          }
          if(map.get(i).get(j) == 5){
            if(j == map.get(i).size()-1){
              addcol(false);
            }
            if(j<map.get(i).size()-2 && map.get(i).get(j+2) == 4){
              map.get(i).set(j,1);
              map.get(i).set(j+2,1);
            }
            else{
              map.get(i).set(j,0);
              map.get(i).set(j+1,1);
            }
          }
        }
      }
    }
  }
  public void printmap(){
    for(ArrayList<Integer> i:map){
      for(int j:i){
        if(j == 0){
          System.out.print(".");
        }
        else{
          System.out.print(j);
        }
      }
      System.out.println();
    }
  }
   public boolean canmove(){
    for(ArrayList<Integer> i:map){
      for(int j:i){
        if(j != 0 && j!=1){
          return true;
        }
      }
    }
    return false;
  }
}
