import java.io.File;
import java.util.*;
class a {
  public static void main(String[] args) {
    String str = "";
    File file = new File("cubes.txt");
    ArrayList<Cube> cubes = new ArrayList<Cube>();
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        cubes.add(new Cube(Integer.parseInt(str.split(",")[0]),  
                          Integer.parseInt(str.split(",")[1]),   
                          Integer.parseInt(str.split(",")[2]),
                          false));
      }
      int total = 0;
      for(Cube i:cubes){
        int sides = 6;
        for(Cube j:cubes){
          if(i!=j && i.istouching(j)){
            sides --;
          }
        }
        System.out.println(sides);
        total += sides;
      }
      System.out.println("Answer: " + total);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Cube{
  int x;
  int y;
  int z;
  public Cube(int x,int y,int z,boolean a){
    this.x = x;
    this.y = y; 
    this.z = z;
  }
  public boolean istouching(Cube cube){
    if((x==cube.x&&y==cube.y&&(z-1 == cube.z||z+1 == cube.z))||         (y==cube.y&&z==cube.z&&(x-1 == cube.x||x+1 == cube.x))||         (x==cube.x&&z==cube.z)&&(y-1 == cube.y||y+1 == cube.y)){
      return true;
    }
    return false;
  }
}