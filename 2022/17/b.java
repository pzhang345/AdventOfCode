//May not work for other test files, to make it work change px to (goalx-(number of rocks to get into the repeating section)/(number of rocks in the repeating section)) and pheight to (height of the repeating sections)*px + (height of the remainder section at the end)
import java.io.File;
import java.util.*;
class b {
  public static void main(String[] args) {
    String str = "";
    int count1 = 0;
    long pheight = 0;
    long goalx = 1000000000001L;
    //goalx = 2023;
    long px = (goalx-1749)/1725;
    pheight = 2734*px + 2773;
    System.out.println(px);
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        System.out.println(str.length());
      }
      Cave cave = new Cave();
      int index = 0;
      cave.createrock();
      while(cave.count<1749 + (goalx-1749)%1725){
        if(str.charAt(index) == '<'){
          cave.left();
        }
        else if(str.charAt(index) == '>'){
          cave.right();
        }
        index ++;
        if(index == str.length()){
          if(count1 <= 1){
            count1 ++;
            index = 0;
          }
          System.out.println(cave);
          System.out.println(cave.count);
        }
        cave.drop();
        System.out.println(cave.count);
        //if(cave.count>1749)
        //System.out.println(cave);
        //System.out.println(cave.height);
      }
      System.out.println(cave);
      System.out.println("Answer: " + (cave.changeheight() + pheight-2773));
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Cave{
  ArrayList<int[]> rocks = new ArrayList<int[]>();
  public static boolean[][] rock1 = {{true,true,true,true}};
  
  public static boolean[][] rock2 = {{false,true,false},
                                     {true,true,true},
                                     {false,true,false}};
  
  public static boolean[][] rock3 = {{false,false,true},
                                     {false,false,true},
                                     {true,true,true}};
  
  public static boolean[][] rock4 = {{true},{true},{true},{true}};
  
  public static boolean[][] rock5 = {{true,true},{true,true}};
  
  int rocknumber = 1;
  int height = 0;
  int count = 1;
  public Cave(){
    int[] temp = {2,2,2,2,2,2,2};
    rocks.add(temp);
  }
  public int changeheight(){
    for(int i = rocks.size()-1; i>=0;i--){
      for(int j = 0; j<7;j++){
        if(rocks.get(i)[j] == 2){
          height = i;
          return height;
        }
      }
      rocks.remove(i);
    }
    return 0;
  }
  /*public void deleteempty(){
    boolean isempty = true;
    for(int i = rocks.size()-1; i>=0;i--){
      isempty = true;
      for(int j = 0; j<7;j++){
        if(rocks.get(i)[j] == 2){
          isempty = false;
          break;
        }
      }
      if(isempty){
        rocks.remove(i);
      }
      else{
        break;
      }
    }
  }*/
  public void createrock(){
    changeheight();
    boolean[][] rock = {{false}};
    switch(rocknumber){
      case 1: rock = rock1; break;
      case 2: rock = rock2; break;
      case 3: rock = rock3; break;
      case 4: rock = rock4; break;
      case 5: rock = rock5; break;
    }
    while(rocks.size()<=height + rock.length + 3){
      int[] empty = {0,0,0,0,0,0,0};
      rocks.add(empty);
      //if(rocknumber == 5){
        //System.out.println(height + rock.length + 3);
      //}
    }
    for(int i = 0; i<rock.length;i++){
      int[] temp = rocks.get(rocks.size()-1-i);
      for(int j = 0; j<rock[0].length;j++){
        if(rock[i][j]){
          temp[j+2] = 1;
        }
        else{
          temp[j+2] = 0;
        }
      }
      rocks.set(rocks.size()-1-i,temp);
    }
    rocknumber ++;
    if(rocknumber == 6){
      rocknumber = 1;
    }
  }
  public void right(){
    boolean rockright = false;
    for(int i = rocks.size()-1; i>=0;i--){
      for(int j = 0; j<7;j++){
        if(rocks.get(i)[j] == 1 && (j == 6||rocks.get(i)[j+1] == 2)){
          rockright = true;
        }
      }
    }
    if(!rockright){
      for(int i = 0; i<rocks.size();i++){
        for(int j = 6; j>=0;j--){
          if(rocks.get(i)[j] == 1){
            int[] temp = rocks.get(i);
            temp[j+1] = 1;
            temp[j] = 0;
            rocks.set(i,temp);
          }
        }
      }
    }
  }
  public void left(){
    boolean rockleft = false;
    for(int i = rocks.size()-1; i>=0;i--){
      for(int j = 0; j<7;j++){
        if(rocks.get(i)[j] == 1 && (j == 0||rocks.get(i)[j-1] == 2)){
          rockleft = true;
        }
      }
    }
    if(!rockleft){
      for(int i = 0; i<rocks.size();i++){
        for(int j = 0; j<7;j++){
          if(rocks.get(i)[j] == 1){
            int[] temp = rocks.get(i);
            temp[j-1] = 1;
            temp[j] = 0;
            rocks.set(i,temp);
          }
        }
      }
    }
  }
  public void drop(){
    boolean rockunder = false;
    for(int i = rocks.size()-1; i>=0;i--){
      for(int j = 0; j<7;j++){
        if(rocks.get(i)[j] == 1 && rocks.get(i-1)[j] == 2){
          rockunder = true;
        }
      }
    }
    if(rockunder){
      for(int i = 0; i<rocks.size();i++){
        for(int j = 0; j<7;j++){
          if(rocks.get(i)[j] == 1){
            int[] temp = rocks.get(i);
            temp[j] = 2;
            rocks.set(i,temp);
          }
        }
      }
      count ++;
      createrock();
    }
    else{
      for(int i = 0; i<rocks.size();i++){
        for(int j = 0; j<7;j++){
          if(rocks.get(i)[j] == 1){
            int[] temp = rocks.get(i-1);
            int[] temp2 = rocks.get(i);
            temp[j] = 1;
            temp2[j] = 0;
            rocks.set(i-1,temp);
            rocks.set(i,temp2);
          }
        }
      }
    }
  }
  public String toString(){
    String str = "";
    for(int i = rocks.size()-1; i>=rocks.size()-10;i--){
      str += "[";
      for(int j = 0; j<7;j++){
        if(rocks.get(i)[j] == 0){
          str +=".";
        }
        else if(rocks.get(i)[j] == 1){
          str +="@";
        }
        else if(rocks.get(i)[j] == 2){
          str +="#";
        }
        /*if(j!=6){
          str += " ";
        }*/
      }
      str += "]\n";
    }
    return str;
  }
}