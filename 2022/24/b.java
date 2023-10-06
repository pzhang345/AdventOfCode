import java.io.File;
import java.util.*;
class b {
  public static void main(String[] args) {
    String str = "";
    File file = new File("blizzard.txt");
    int x = 0;
    int y = 0;
    char[][] all;
    Map m;
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        y++;
      }
      x = str.length();
      all = new char[y][x];
      sc.close();
      sc = new Scanner(file);
      y = 0;
      while (sc.hasNextLine()){
        str = sc.nextLine();
        System.out.println(str);
        for(int i = 0; i<str.length();i++){
          all[y][i] = str.charAt(i);
        }
        y++;
      }
      System.out.println(all.length + " " + all[0].length);
      m = new Map(all);
      System.out.println(m.startpos.get(0)[0] + " " + m.startpos.get(0)[1]);
      m.move(m.startpos);
      m.move(m.endpos);
      m.move(m.startpos);
      System.out.println();
      System.out.println("Answer: " + m.count);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Map{
  boolean[][] all;
  boolean[][] up;
  boolean[][] down;
  boolean[][] left;
  boolean[][] right;
  ArrayList<int[]> startpos;
  ArrayList<int[]> endpos;
  int count;
  int num = 0;
  public Map(char [][] a){
    all = new boolean[a.length][a[0].length];
    up = new boolean[a.length][a[0].length];
    down = new boolean[a.length][a[0].length];
    left = new boolean[a.length][a[0].length];
    right = new boolean[a.length][a[0].length];
    startpos = new ArrayList<int[]>();
    endpos = new ArrayList<int[]>();
    for(int i = 0; i<a.length;i++){
      for(int j = 0; j<a[i].length;j++){
        if(a[i][j] == '.'){
          if(i==0){
            int[] temp = {j,0};
            //System.out.println(temp.length);
            startpos.add(temp);
          }
          if(i==a.length-1){
            int[] temp = {j,a.length-1};
            //System.out.println(temp.length);
            endpos.add(temp);
          }
          all[i][j] = false;
        }
        else{
          all[i][j] = true;
        }
        if(a[i][j] == '^'){
          up[i][j] = true;
        }
        else{
          up[i][j] = false;
        }
        if(a[i][j] == 'v'){
          down[i][j] = true;
        }
        else{
          down[i][j] = false;
        }
        if(a[i][j] == '<'){
          left[i][j] = true;
        }
        else{
          left[i][j] = false;
        }
        if(a[i][j] == '>'){
          right[i][j] = true;
        }
        else{
          right[i][j] = false;
        }
      }
    }
  }
  public void changeall(){
    for(int i = 1; i<all.length-1;i++){
      for(int j = 1; j<all[i].length-1;j++){
        if(up[i][j] || down[i][j] || left[i][j] || right[i][j]){
          all[i][j] = true;
        }
        else{
          all[i][j] = false;
        }
      }
    }
  }
  public void tick(){
    for(int i = 1; i<all.length-1;i++){
      for(int j = 1; j<all[i].length-1;j++){
        if(left[i][j]){
          left[i][j] = false;
          left[i][j-1] = true;
        }
        if(up[i][j]){
          up[i][j] = false;
          up[i-1][j] = true;
        }
        if(right[i][all[i].length - j - 1]){
          right[i][all[i].length - j - 1] = false;
          right[i][all[i].length - j] = true;
        }
        if(down[all.length-i-1][j]){
          down[all.length-i-1][j] = false;
          down[all.length-i][j] = true;
        }
      }
    }
    for(int i = 1; i<all.length-1;i++){
      if(left[i][0]){
        left[i][0] = false;
        left[i][all[0].length-2] = true;
      }
      if(right[i][all[0].length - 1]){
        right[i][all[0].length - 1] = false;
        right[i][1] = true;
      }
    }
    for(int i = 1; i<all[0].length-1;i++){
      if(up[0][i]){
        up[0][i] = false;
        up[all.length-2][i] = true;
      }
      if(down[all.length - 1][i]){
        down[all.length - 1][i] = false;
        down[1][i] = true;
      }
    }
    changeall();
  }
  public void printmap(){
    for(boolean[] i:all){
      for(boolean j:i){
        if(j){
          System.out.print("#");
        }
        else{
          System.out.print(".");
        }
      }
      System.out.println();
    }
  }
  public void move(ArrayList<int[]> pos){
    System.out.println(count);
    ArrayList<int[]> newpos = new ArrayList<int[]>();
    tick();
    for(int[] i:pos){
      if(!all[i[1]][i[0]] && !contains(newpos,i[0],i[1])){
        int[] temp ={i[0],i[1]};
        newpos.add(temp);
      }
      //System.out.println("A");
      if(!all[i[1]][i[0]-1] && !contains(newpos,i[0]-1,i[1])){
        int[] temp = {i[0]-1,i[1]};
        newpos.add(temp);
      }
      //System.out.println("A");
      if(!all[i[1]][i[0]+1] && !contains(newpos,i[0]+1,i[1])){
        int[] temp ={i[0]+1,i[1]};
        newpos.add(temp);
      }
      //System.out.println("A");
      if(i[1] != 0 && !all[i[1]-1][i[0]] && !contains(newpos,i[0],i[1]-1)){
        int[] temp ={i[0],i[1]-1};
        newpos.add(temp);
      }
      //System.out.println("A");
      if(i[1] != all.length - 1 && !all[i[1]+1][i[0]] && !contains(newpos,i[0],i[1]+1)){
        int[] temp ={i[0],i[1]+1};
        newpos.add(temp);
      }
    }
    count ++;
    System.out.println(newpos.size());
    if(!end(newpos)){
      move(newpos);
    }
    else{
      num ++;
    }
  }
  public boolean contains(ArrayList<int[]> pos,int x,int y){
    for(int[] i:pos){
      if(i[0] == x && i[1] == y){
        return true;
      }
    }
    return false;
  }
  public boolean end(ArrayList<int[]> pos){
    for(int[] i:pos){
      if(num % 2 == 0 && i[1] == all.length -1){
        return true;
      }
      else if(num % 2 == 1 && i[1] == 0){
        return true;
      }
    }
    return false;
  }
}