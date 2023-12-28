import java.util.*;
import java.io.File;
class a {
  public static void main(String[] args) {
    int score = 0;
    String string = "";
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        string = sc.nextLine();
        if(string.substring(2,3).equals("X")){
          score ++;
        }
        if(string.substring(2,3).equals("Y")){
          score += 2;
        }
        if(string.substring(2,3).equals("Z")){
          score += 3;
        }
        if(string.equals("A X") || string.equals("B Y") || string.equals("C Z")){
          score += 3;
        }
        if(string.equals("A Y") || string.equals("B Z") || string.equals("C X")){
          score += 6;
        }
      }
      System.out.println("Answer: " + score);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}