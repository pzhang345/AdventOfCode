import java.io.File;
import java.util.*;
class b {
  public static void checkair(ArrayList<Cube> cubes, ArrayList<Cube> airs){
    for(int i = 0; i<airs.size();i++){
      int x = airs.get(i).x;
      int y = airs.get(i).y;
      int z = airs.get(i).z;
      if(x<=Cube.maxvalue && !contains(airs,x+1,y,z) && !contains(cubes,x+1,y,z)){
        airs.add(new Cube(x+1,y,z,true));
      }
      if(x>=Cube.minvalue && !contains(airs,x-1,y,z) && !contains(cubes,x-1,y,z)){
        airs.add(new Cube(x-1,y,z,true));
      }
      if(y<=Cube.maxvalue && !contains(airs,x,y+1,z) && !contains(cubes,x,y+1,z)){
        airs.add(new Cube(x,y+1,z,true));
      }
      if(y>=Cube.minvalue && !contains(airs,x,y-1,z) && !contains(cubes,x,y-1,z)){
        airs.add(new Cube(x,y-1,z,true));
      }
      if(z<=Cube.maxvalue && !contains(airs,x,y,z+1) && !contains(cubes,x,y,z+1)){
        airs.add(new Cube(x,y,z+1,true));
      }
      if(z>=Cube.minvalue && !contains(airs,x,y,z-1) && !contains(cubes,x,y,z-1)){
        airs.add(new Cube(x,y,z-1,true));
      }
    }
  }
  public static void createair(ArrayList<Cube> cubes, ArrayList<Cube> airs){
    for(int j = Cube.minvalue - 1; j<=Cube.maxvalue + 1;j++){
      for(int k = Cube.minvalue - 1; k<=Cube.maxvalue + 1;k++){
        if(!contains(airs,j,k,Cube.minvalue - 1)){
          airs.add(new Cube(j,k,Cube.minvalue - 1,true));
        }
        if(!contains(airs,j,Cube.minvalue - 1,k)){
          airs.add(new Cube(j,Cube.minvalue - 1,k,true));
        }
        if(!contains(airs,Cube.minvalue - 1,j,k)){
          airs.add(new Cube(Cube.minvalue - 1,j,k,true));
        }
        if(!contains(airs,j,k,Cube.maxvalue + 1)){
          airs.add(new Cube(j,k,Cube.maxvalue + 1,true));
        }
        if(!contains(airs,j,Cube.maxvalue + 1,k)){
          airs.add(new Cube(j,Cube.maxvalue + 1,k,true));
        }
        if(!contains(airs,Cube.maxvalue + 1,j,k)){
          airs.add(new Cube(Cube.maxvalue + 1,j,k,true));
        }
      
    }
    }
  }
  public static boolean contains(ArrayList<Cube> cubes,int x, int y, int z){
    for(Cube i:cubes){
      if(i.x == x && i.y == y && i.z == z){
        return true;
      }
    }
    return false;
  }
  public static void main(String[] args) {
    String str = "";
    File file = new File("cubes.txt");
    ArrayList<Cube> cubes = new ArrayList<Cube>();
    ArrayList<Cube> airs = new ArrayList<Cube>();
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        cubes.add(new Cube(Integer.parseInt(str.split(",")[0]),  
                          Integer.parseInt(str.split(",")[1]),   
                          Integer.parseInt(str.split(",")[2]),
                          false));
      }
      System.out.println(Cube.maxvalue);
      createair(cubes,airs);
      checkair(cubes,airs);
      int total = 0;
      for(Cube i:cubes){
        for(Cube j:airs){
          if(i.istouching(j)){
            total ++;
          }
        }
        //System.out.println(sides);
      }
      System.out.println(total + " " + airs.size());
      System.out.println("Answer: " + total);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Cube{
  int x;
  int y;
  int z;
  static int maxvalue = 0;
  static int minvalue = 100;
  boolean air;
  public Cube(int x,int y,int z,boolean a){
    this.x = x;
    this.y = y; 
    this.z = z;
    air = a;
    if(!air){
      if(x>maxvalue){
        maxvalue = x;
      }
      if(y>maxvalue){
        maxvalue = y;
      }
      if(z>maxvalue){
        maxvalue = z;
      }
      if(x<minvalue){
        minvalue = x;
      }
      if(y<minvalue){
        minvalue = y;
      }
      if(z<minvalue){
        minvalue = z;
      }
    }
  }
  public boolean istouching(Cube cube){
    if((x==cube.x&&y==cube.y&&(z-1 == cube.z||z+1 == cube.z))||
      (y==cube.y&&z==cube.z&&(x-1 == cube.x||x+1 == cube.x))||
       (x==cube.x&&z==cube.z)&&(y-1 == cube.y||y+1 == cube.y)){
      return true;
    }
    return false;
  }
  public boolean istouching(int x, int y, int z){
    if((this.x==x&&this.y==y&&(this.z-1 == z||this.z+1 == z))||
      (this.y==y&&this.z==z&&(this.x-1 == x||this.x+1 == x))||
       (this.x==x&&this.z==z)&&(this.y-1 == y||this.y+1 == y)){
      return true;
    }
    return false;
  }
}