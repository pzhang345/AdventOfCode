import java.util.*;
import java.io.File;
class a {
  public static void main(String[] args) {
    int high = 0;
    int count = 0;
    String string = "";
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        string = sc.nextLine();
        if (string == "" && high <= count){
          System.out.println(count + "a");
          high = count;
          count = 0;
        }
          else if(string == ""){
            count = 0;
          }
        else{
          count += Integer.parseInt(string);
        }
      }
      System.out.println("Answer: " + high);
      sc.close();
    }
    catch(Exception E){
      
    }
  }
}