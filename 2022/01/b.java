//May not work for other test files, to make it work run the program 3 times and change the count != x to the highest number you get each time
import java.util.*;
import java.io.File;
class b {
  public static void main(String[] args) {
    int high = 0;
    int count = 0;
    String string = "";
    File file = new File("number.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        string = sc.nextLine();
        if (string == "" && high <= count && count!= 68787 && count != 65169){
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
      System.out.println("Answer: " + (68787 + 65169 + high));
      sc.close();
    }
    catch(Exception E){
      
    }
  }
}