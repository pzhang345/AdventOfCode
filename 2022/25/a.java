import java.io.File;
import java.util.*;
class a {
  public static String b10tob5(long num){
    long place = 1;
    while(num/place != 0){
      place *= 5;
    }
    place /= 5;
    return num/place + b10tob5(num%place,place/5);
  }
  public static String b10tob5(long num,long place){
    if(place != 0){
      return num/place + b10tob5(num%place,place/5);
    }
    return "";
  }
  public static String formatb5(String b5){
    String temp = "";
    int[] strdigits = new int[b5.length() + 1];
    strdigits[0] = 0;
    for(int i = 0;i<b5.length();i++){
      strdigits[i+1] = Integer.parseInt(b5.substring(i,i+1));
    }
    for(int i = strdigits.length - 1; i>=0;i--){
      if(strdigits[i]<=2){
        continue;
      }
      else{
        strdigits[i] -= 5;
        strdigits[i-1] += 1;
      }
    }
    for(int i = 0; i<strdigits.length;i++){
      if(strdigits[i]>=0){
        temp += strdigits[i];
      }
      else if(strdigits[i] == -1){
        temp += "-";
      }
      else if(strdigits[i] == -2){
        temp += "=";
      }
    }
    return temp;
  }
  public static void main(String[] args) {
    long place;
    long total = 0;
    long num;
    String str = "";
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        num = 0;
        place = 1;
        while(str.length() != 0){
          try{
            num += Long.parseLong(str.substring(str.length()-1))*place;
          }
          catch(Exception e){
            if(str.charAt(str.length()-1) == '-'){
              num -= place;
            }
            else if(str.charAt(str.length()-1) == '='){
              num -= 2*place;
            }
          }
          str = str.substring(0,str.length()-1);
          place *= 5;
        }
        total += num;
        //System.out.println(num);
      }
      System.out.println(total);
      System.out.println(b10tob5(total));
      System.out.println("Answer: " + formatb5(b10tob5(total)));
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}