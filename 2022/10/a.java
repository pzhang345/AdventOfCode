import java.io.File;
import java.util.*;
class a {
  public static void main(String[] args) {
    String str = "";
    File file = new File("text.txt");
    int cyclenumber = 0;
    int signal = 1;
    int total = 0;
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        if(str.substring(0,4).equals("addx")){
          cyclenumber++;
        }
        else{
          cyclenumber++;
        }
        for(int i =0; i<10;i++){
          if(cyclenumber==20+i*40){
            total += signal*cyclenumber;
            System.out.println(cyclenumber + " " + signal + " "+ signal*cyclenumber);
          }
        }
        if(str.substring(0,4).equals("addx")){
          cyclenumber ++;
          for(int i =0; i<10;i++){
            if(cyclenumber==20+i*40){
              total += signal*cyclenumber;
              System.out.println(cyclenumber + " " + signal + " "+ signal*cyclenumber);
            }
          }
          signal += Integer.parseInt(str.substring(5));
        }
      }
      System.out.println("Answer: " + total);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}