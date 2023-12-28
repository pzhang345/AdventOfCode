//Did this one manually
import java.io.File;
import java.util.*;
class b {
  public static void main(String[] args) {
    int count = 0;
    String str = "";
    char[][] mountain;
    int x = 0;
    int y = 20;
    File file = new File("text.txt");
    //System.out.println((int)('a') - (int)('A'));
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        y++;
      }
      mountain = new char[y][str.length()];
      y = 0;
      Scanner sc1 = new Scanner(file);
      while (sc1.hasNextLine()){
        str = sc1.nextLine();
        for(int i = 0; i<str.length(); i++){
          mountain[y][i]  = str.charAt(i);
        }
        y++;
      }
      y = 20;
      String temp;
      Scanner input = new Scanner(System.in);
      while(mountain[y][x] != 'E'){
        for(int i = 0; i < mountain.length;i++){
          System.out.println();
          for(int j = 0; j< mountain[0].length; j++){
            if(i == y && j == x){
              System.out.print((char)(mountain[i][j]-32));
            }
            else{
              System.out.print(mountain[i][j]);
            }
          }
        }
        //for(int i = 0; i < mountain.length;i++){
          //for(int j = 0; j< mountain[0].length; j++){
            //if(mountain[i][j] == 'b'){
              //System.out.println(j + " " + i);
            //}
          //}
        //}
        temp = input.nextLine();
        if(temp.equals("w")){
          y--;
          count++;
        }
        else if(temp.equals("s")){
          y++;
          count++;
        }
        else if(temp.equals("a")){
          x--;
          count++;
        }
        else if(temp.equals("d")){
          x++;
          count++;
        }
        //7 steps from S to best A
        System.out.println(count +" "+ x +" "+ y);
      }
     System.out.println(count);
     sc.close();
     sc1.close();
     input.close();
    }catch(Exception e){System.out.println(e);}
  }
}