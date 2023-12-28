import java.io.File;
import java.util.*;
class b {
  public static int isvisible(int[][] trees, int x, int y){
    int total = 0;
    int temp = 0;
    for(int i = 0; i<trees.length-y-1; i++){
      temp++;
      if(trees[y+i+1][x]>=trees[y][x])
        break;
    }
    total = temp;
    temp = 0;
    for(int i = 0; i<y; i++){
      temp++;
      if(trees[y-i-1][x]>=trees[y][x])
        break;
    }
    total *=temp;
    temp = 0;
    for(int i = 0; i<trees[0].length-x-1; i++){
      temp++;
      if(trees[y][x+1+i]>=trees[y][x])
        break;
    }
    total *=temp;
    temp = 0;
    for(int i = 0; i<x; i++){
      temp++;
      if(trees[y][x-i-1]>=trees[y][x])
        break;
    }
    total *=temp;
    return total;
  }
  public static void main(String[] args) {
    String str = "";
    File file = new File("text.txt");
    int x = 0;
    int y = 0;
    int count = 0;
    int max = 0;
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
      for(int i = 0; i<y; i++){
          for(int j =0; j<x;j++){
            if(max<isvisible(trees,j,i)){
              max = isvisible(trees,j,i);
            }
          }
        }
      System.out.println("Answer: " + max);
      sc.close();
      sc1.close();
    }catch(Exception e){System.out.println(e);}
  }
}