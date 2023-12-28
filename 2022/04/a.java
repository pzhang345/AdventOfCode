import java.io.File;
import java.util.*;
class a{
  public static void main(String[] args) {
    String string = "";
    int low1;
    int low2;
    int high1;
    int high2;
    int count = 0;
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        string = sc.nextLine();
        low1 = Integer.parseInt(string.substring(0,string.indexOf("-")));
        high1 = Integer.parseInt(string.substring(string.indexOf("-")+1,string.indexOf(",")));
        low2 = Integer.parseInt(string.substring(string.indexOf(",")+1,string.indexOf("-",string.indexOf(","))));
        high2 = Integer.parseInt(string.substring(string.indexOf("-",string.indexOf(","))+1));
        //System.out.println(low1 + " " + high1 + " "+ low2 + " " + high2);
        if((low1<=low2 && high1>=high2) || (low2<=low1 && high2>=high1))
          count++;
      }
      System.out.println("Answer: " + count);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}