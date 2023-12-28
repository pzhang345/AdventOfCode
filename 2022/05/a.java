import java.io.File;
import java.util.*;
class a{
  public static void main(String[] args) {
    String[] boxes = {"QFLSR","TPGQZN","BQMS","QBCHJZGT","SFNBMHP","GVLSNQCP","FCW","MPVWZGHQ","RNCKDZG"};
    String string = "";
    int amount;
    int start;
    int end;
    String temp;
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        string = sc.nextLine();
        amount = Integer.parseInt(string.substring(5,string.indexOf("f")-1));
        start = Integer.parseInt(string.substring(string.indexOf("t")-2,string.indexOf("t")-1));
        end = Integer.parseInt(string.substring(string.length()-1));
        for(int i=0; i<amount; i++){
          temp = boxes[start-1].substring(0,1);
          boxes[start-1] = boxes[start-1].substring(1);
          boxes[end-1] = temp + boxes[end-1];
        }
        //System.out.println(amount + " " + start + " " + end);
      }
      for(int i = 0; i<boxes.length; i++){
        System.out.print(boxes[i] + " ");
      }
      System.out.println();
      System.out.print("Answer: ");
      for(int i = 0; i<boxes.length; i++){
        System.out.print(boxes[i].charAt(0));
      }
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}