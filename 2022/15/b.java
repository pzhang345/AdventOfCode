import java.io.File;
import java.util.*;
class b {
  public static int xval = 0;
  public static int yval = 0;
  public static int leng = 4000000;
  public static void sort(int[][] pos) {
    int small;
    int index;
    int[] temp;
    for (int i = 0; i < pos.length; i++) {
      small = pos[i][0];
      index = i;
      for (int j = i; j < pos.length; j++) {
        if (pos[j][0] < small) {
          small = pos[j][0];
          index = j;
        }
      }
      //System.out.println(small);
      temp = pos[index];
      pos[index] = pos[i];
      pos[i] = temp;
    }
   }

  public static boolean checkrow(ArrayList<Sbsystem> systems, int num) {
    int count = 0;
    int[][] pos = new int[systems.size()][2];
    for (Sbsystem i : systems) {
      if(Math.abs(i.sy-num)<i.distance){
        pos[count][0] = i.sx - (i.distance - Math.abs(i.sy-num));
        pos[count][1] = i.sx + (i.distance - Math.abs(i.sy-num));
      }
      else{
        pos[count][0] = 0;
        pos[count][1] = 0;
      }
        count++;
    }
    sort(pos);
    /*if(num == 12){
      for(int[] j:pos){
         System.out.println(j[0] + " " + j[1]);
      }
    }*/
    count = 0;
    int max = 0;
    if (pos[0][0] <= 0) {
      max = pos[0][1];
    }
    else{
      return false;
    }
    for (int[] i : pos) {
      if(i[0]<=max + 1 && i[1]>max){
        /*if(num == 12){
          System.out.println(max);
        }*/
        max = i[1];
      }
    }
    if(max>leng){
      return true;
    }
    else{
      xval = max + 1;
      return false;
    }
  }

  public static void main(String[] args) {
    int sx;
    int sy;
    int bx;
    int by;
    int count = 0;
    ArrayList<Sbsystem> systems = new ArrayList<Sbsystem>();
    /*int [][] test = {{21,100},{32,50},{23,40},{42,33},{5,13},{63,5}};
    sort(test);
    for(int[] i:test){
      for(int j:i){
        System.out.print(j + " ");
      }
      System.out.println();
    }*/
    String str;
    //File file = new File("beaconstest.txt");
    File file = new File("text.txt");
    try {
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()) {
        str = sc.nextLine();
        sx = Integer.parseInt(str.substring(12, str.indexOf(",")));
        sy = Integer.parseInt(str.substring(str.indexOf("y=") + 2, str.indexOf(":")));
        bx = Integer.parseInt(str.substring(str.lastIndexOf("x=") + 2, str.lastIndexOf(",")));
        by = Integer.parseInt(str.substring(str.lastIndexOf("y=") + 2));
        systems.add(new Sbsystem(sx, sy, bx, by));
      }
      for(int i = 0; i<leng; i++){
        if(i%1000 ==0){
          System.out.println(i);
        }
        if(!checkrow(systems,i)){
          yval = i;
          count ++;
        }
      }
      System.out.println("x:" + xval + " y:" + yval + " result: " + ((long)xval*4000000 + yval));
      System.out.println(count);
      System.out.println("Answer: " + ((long)xval*4000000 + yval));
      sc.close();
    }catch (Exception e) {
       System.out.println(e);
    }
  }
}

class Sbsystem{
  int distance;
  int sx;
  int sy;
  int bx;
  int by;
  public Sbsystem(int sx, int sy, int bx, int by) {
    this.sx = sx;
    this.sy = sy;
    this.bx = bx;
    this.by = by;
    distance = Math.abs(sx - bx) + Math.abs(sy - by);
  }
}
