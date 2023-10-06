import java.io.File;
import java.util.*;
class a {
  public static void main(String[] args) {
    String str = "";
    File file = new File("*.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
      }
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}