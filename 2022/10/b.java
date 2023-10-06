import java.io.File;
import java.util.*;
class b {
  public static void main(String[] args) {
    String str = "";
    File file = new File("code.txt");
    int cyclenumber = 0;
    int signal = 0;
    ArrayList<Boolean> screen = new ArrayList<Boolean>();
    Boolean hasdone = false;
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        hasdone =  false;
        for(int i=0; i<3;i++){
          if(signal+i==cyclenumber%40){
            hasdone = true;
            screen.add(true);
          }
        }
        if(!hasdone){
           screen.add(false);
        }
        if(str.substring(0,4).equals("addx")){
          cyclenumber++;
        }
        else{
          cyclenumber++;
        }
        hasdone = false;
        if(str.substring(0,4).equals("addx")){
          for(int i=0; i<3;i++){
            if(signal+i==cyclenumber%40){
              hasdone = true;
              screen.add(true);
            }
          }
          if(!hasdone){
             screen.add(false);
          }
          cyclenumber ++;
          signal += Integer.parseInt(str.substring(5));
        }
      }
      System.out.println("Answer: ");
      for(int i = 0;i<6;i++){
        for(int j = 0;j<40;j++){
          if(screen.get(i*40+j)){
            System.out.print("#");
          }
          else{
            System.out.print(".");
          }
        }
        System.out.println();
      }
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}