import java.io.File;
import java.util.*;
class a {
  public static void main(String[] args) {
    String string = "";
    String sack1 = "";
    String sack2 = "";
    char common = ' ';
    int total = 0;
    boolean b = false;
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        string = sc.nextLine();
        sack1 = string.substring(0,string.length()/2);
        sack2 = string.substring(string.length()/2);
        for(int i=0; i<sack1.length(); i++){
          for(int j=0; j<sack2.length(); j++){
            if(sack1.substring(i,i+1).equals(sack2.substring(j,j+1))){
              common = sack1.charAt(i);
              System.out.println(common);
              if(Character.isUpperCase(common)){
                total += (int)(common)-38; 
              }
              else{
                total += (int)(common)-96;
              }
              b = true;
              common = ' ';
              break;
            }
          }
          if(b){
            b = false;
            break;
          }
        } 
      }
      common = 'a';
      System.out.println(common + " " + (int)(common));
      System.out.println("Answer: " + total);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}