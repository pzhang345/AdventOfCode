import java.io.File;
import java.util.*;
class a {
  public static boolean isvisible(int[][] trees, int x, int y){
    boolean temp = true;
    for(int i = 0; i<trees.length-y-1; i++){
      if(trees[trees.length-i-1][x]>=trees[y][x])
        temp = false;
    }
    if(temp){
      return true;
    }
    temp = true;
    for(int i = 0; i<y; i++){
      if(trees[i][x]>=trees[y][x])
        temp = false;
    }
    if(temp){
      return true;
    }
    temp = true;
    for(int i = 0; i<trees[0].length-x-1; i++){
      if(trees[y][trees[0].length-i-1]>=trees[y][x])
        temp = false;
    }
    if(temp){
      return true;
    }
    temp = true;
    for(int i = 0; i<x; i++){
      if(trees[y][i]>=trees[y][x])
        temp = false;
    }
    if(temp){
      return true;
    }
    return false;
  }
  public static void main(String[] args) {
    String str = "";
    File file = new File("text.txt");
    int x = 0;
    int y = 0;
    int count = 0;
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        y++;
      }
      x = str.length();
      sc.close();
      int[][] trees = new int[y][x];
      Scanner sc1 = new Scanner(file);
      while (sc1.hasNextLine()){
        str = sc1.nextLine();
        for(int j =0; j<x;j++){
          trees[count][j] = Integer.parseInt(str.substring(j,j+1));
        }
        count++;
      }
      count = 0;
      for(int i = 0; i<y; i++){
          for(int j =0; j<x;j++){
            if(isvisible(trees,j,i)){
              System.out.println(trees[i][j]);
              count++;
            }
          }
        }
      System.out.println("Answer: " + count);
      sc.close();
      sc1.close();
    }catch(Exception e){System.out.println(e);}
  }
}