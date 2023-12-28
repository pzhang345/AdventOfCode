import java.io.File;
import java.util.*;
class b {
  public static void main(String[] args) {
    String sack1 = "";
    String sack2 = "";
    String sack3 = "";
    char common = ' ';
    int total = 0;
    boolean b = false;
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        sack1 = sc.nextLine();
        sack2 = sc.nextLine();
        sack3 = sc.nextLine();
        for(int x=0; x<sack1.length(); x++){
          for(int i=0; i<sack2.length(); i++){
            for(int j=0; j<sack3.length(); j++){
              if(sack1.substring(x,x+1).equals(sack2.substring(i,i+1)) && sack2.substring(i,i+1).equals(sack3.substring(j,j+1))){
                common = sack1.charAt(x);
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
              break;
            }
          } 
          if(b){
            b = !b;
            break;
          }
        }
      }
      //common = 'a';
      //System.out.println(common + " " + (int)(common));
      System.out.println("Answer: " + total);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}