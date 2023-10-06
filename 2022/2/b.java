import java.util.*;
import java.io.File;
class b {
  public static void main(String[] args) {
    int score = 0;
    String string = "";
    File file = new File("guide.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        string = sc.nextLine();
        if(string.substring(2,3).equals("Y")){
          score += 3;
        }
        if(string.substring(2,3).equals("Z")){
          score += 6;
        }
        if(string.equals("A Y") || string.equals("B X") || string.equals("C Z")){
          score ++;
        }
        if(string.equals("B Y") || string.equals("C X") || string.equals("A Z")){
          score += 2;
        }
        if(string.equals("C Y") || string.equals("A X") || string.equals("B Z")){
          score += 3;
        }
      }
      System.out.println("Answer: " + score);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}