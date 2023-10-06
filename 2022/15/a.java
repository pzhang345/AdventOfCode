import java.io.File;
import java.util.*;
class a {
  public static void main(String[] args) {
    int lowx = 8;
    int highx = 8;
    int sx;
    int sy;
    int bx;
    int by;
    int distance = 0;
    int count = 0;
    int indy = 2000000;
    String str = "";
    File file = new File("beacons.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        sx = Integer.parseInt(str.substring(12,str.indexOf(",")));
        sy = Integer.parseInt(str.substring(str.indexOf("y=")+2,str.indexOf(":")));
        bx = Integer.parseInt(str.substring(str.lastIndexOf("x=")+2,str.lastIndexOf(",")));
        by = Integer.parseInt(str.substring(str.lastIndexOf("y=")+2));
        System.out.println(sx + " " + sy + " "+ bx + " " + by);
        distance = Math.abs(sx-bx) + Math.abs(sy-by);
        if(lowx> sx - (distance-Math.abs(indy-sy))){
          lowx = sx -(distance-Math.abs(indy-sy));
        }
        else if(highx < sx + (distance-Math.abs(indy-sy))){
          highx = sx + (distance-Math.abs(indy-sy));
        }
      }
      System.out.println(lowx +" " + highx);
      ifbeacon:for(int i = lowx; i<highx+1;i++){
         sc = new Scanner(file);
        while (sc.hasNextLine()){
          str = sc.nextLine();
          bx = Integer.parseInt(str.substring(str.lastIndexOf("x=")+2,str.lastIndexOf(",")));
          by = Integer.parseInt(str.substring(str.lastIndexOf("y=")+2));
          if(bx == i &&by == indy){
            continue ifbeacon;
          }
        }
        sc.close();
        sc = new Scanner(file);
        while (sc.hasNextLine()){
          str = sc.nextLine();
          sx = Integer.parseInt(str.substring(12,str.indexOf(",")));
          sy = Integer.parseInt(str.substring(str.indexOf("y=")+2,str.indexOf(":")));
          bx = Integer.parseInt(str.substring(str.lastIndexOf("x=")+2,str.lastIndexOf(",")));
          by = Integer.parseInt(str.substring(str.lastIndexOf("y=")+2));
          distance = Math.abs(sx-bx) + Math.abs(sy-by);
          if(distance-Math.abs(indy-sy) >= 0 && sx-(distance-Math.abs(indy-sy)) <= i
             && sx + (distance-Math.abs(indy-sy)) >= i){
            if(i%1000 ==0){
              System.out.println(i);
            }
            count++;
            break;
          }
        }
      }
    System.out.println("Answer: " + count);
    sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}