import java.io.File;
import java.util.*;
class b {
  public static void main(String[] args) {
    String str = "";
    ArrayList<Character> temp = new ArrayList<Character>();
    boolean repeat = false;
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        for(int i= 0; i<str.length(); i++){
          repeat = false;
          temp.add(str.charAt(i));
          if(temp.size()==15){
            temp.remove(0);
            System.out.println(temp);
            for(int x = 0; x<temp.size(); x++){
              for(int y = 0; y<temp.size(); y++){
                if(x != y && temp.get(x) == temp.get(y)){
                  repeat = true;
                }
              }
            }
            if(!repeat){
              System.out.println("Answer: " + (i + 1));
              break;
            }
          }
        }
      }
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}