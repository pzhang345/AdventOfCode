import java.io.File;
import java.util.*;
class a {
  public static void main(String[] args) {
    String str1 = "";
    String str2 = "";
    Arr arr1;
    Arr arr2;
    int count = 0;
    int total = 0;
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        count ++;
        str1 = sc.nextLine();
        //System.out.println("a");
        //System.out.println(str1);
        arr1 = new Arr(str1);
        //System.out.println(arr1);
        str2 = sc.nextLine();
        arr2 = new Arr(str2);
        //System.out.println(arr2);
        System.out.println(count + ": " + arr1.compare(arr2)); 
        if (arr1.compare(arr2) == 0){
            total += count;
        }
        if(sc.hasNextLine()){
          sc.nextLine();
        }
        //break;
      }
      System.out.println("Answer: " + total);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Value{
  boolean isarr;
  Arr arr;
  int value;
  public Value(int i){
    isarr = false;
    value = i;
  }
  public Value(Arr a){
    isarr = true;
    arr = a;
  }
  public boolean getisarr(){
      return isarr;
  }
  public int getvalue(){
      return value;
  }
  public Arr getarr(){
      return arr;
  }
  public String toString(){
    if(isarr){
      return arr.toString();
    }
    else{
      return "" + value;
    }
  }
}
class Arr{
  Value[] valarr;
  String str;
  public Arr(String str){
    this.str = str;
    createarr();
  }
  public Arr(Value[] val){
      valarr = val;
  }
  public void createarr(){
    int count = 0;
    int start = 1;
    int total = 1;
    if(str.equals("[]")){
    total = 0;
    }
    for(int i = 1 ; i<str.length()-1;i++){
      if(str.charAt(i)=='['){
        count++;
      }
      else if(str.charAt(i)==']'){
        count--;
      }
      if(count == 0 && str.charAt(i) == ','){
        total++;
      }
    }
    valarr = new Value[total];
    total = 0;
    //count = 0;
    //System.out.println(str);
    for(int i = 1 ; i<str.length()-1;i++){
      //System.out.println(str.charAt(i));
      if(str.charAt(i)=='['){
        count++;
        if(count == 1){
          start = i;
        }
      }
      else if(str.charAt(i)==']'){
        count--;
        if(count == 0){
          //System.out.println("b");
          valarr[total] = new Value(new Arr(str.substring(start,i+1)));
          total ++;
        }
      }
      if(count == 0 && str.charAt(i) == ',' && !str.substring(i-1,i+1).equals("],")){
        if (total == 0){
           valarr[total] = new Value(Integer.parseInt(str.substring(1,i)));
        }
        else{
          valarr[total] = new Value(Integer.parseInt(str.substring(str.lastIndexOf(",",i-1)+1,i)));
        }
        total ++;
      }
    }
    if(/*str.charAt(str.length()-2) != ']' && */total != valarr.length){
      if(valarr.length == 1){
        valarr[total] = new Value(Integer.parseInt(str.substring(1,str.length()-1)));
      }
      else{
        valarr[total] = new Value(Integer.parseInt(str.substring(str.lastIndexOf(",")+1,str.length()-1)));
      }
    }
    //System.out.println(str);
  }
  public int getlength(Arr arr2){
    if(this.valarr.length<arr2.valarr.length){
      return this.valarr.length;
    }
    else{
      return arr2.valarr.length;
    }
  }
  public int compare(Arr arr2){
    Value[] valarr2 = arr2.getvalarr();
    for(int i = 0; i<this.getlength(arr2); i++){
      if(!valarr[i].getisarr() && !valarr2[i].getisarr()){
        if(valarr[i].getvalue() > valarr2[i].getvalue()){
          return 2;
        }
        else if(valarr[i].getvalue() < valarr2[i].getvalue()){
          return 0;
        }
      }
      else if(valarr[i].getisarr() && valarr2[i].getisarr()){
        if(valarr[i].getarr().compare(valarr2[i].getarr()) == 0){
          return 0;
        }
        else if(valarr[i].getarr().compare(valarr2[i].getarr()) == 2){
          return 2;
        }
      }
      else{
        Arr val1;
        Arr val2;
        if(!valarr[i].getisarr()){
          Value[] temp = new Value[1];
          temp[0] = new Value(valarr[i].getvalue());
          val1 = new Arr(temp);
          val2 = valarr2[i].getarr();
        }
        else{
          Value[] temp = new Value[1];
          temp[0] = new Value(valarr2[i].getvalue());
          val2 = new Arr(temp);
          val1 = valarr[i].getarr();
        }
        if(val1.compare(val2) == 0){
          return 0;
        }
        else if(val1.compare(val2) == 2){
          return 2;
        }
      }
    }
    if(valarr.length == valarr2.length){
      return 1;
    }
    else if(valarr.length < valarr2.length){
      return 0;
    }
    else{
      return 2;
    }
  }
  public Value[] getvalarr(){
    return valarr;
  }
  public String toString(){
    //System.out.println("a");
    String temp = "";
    temp += "[";
    for(int i = 0; i<valarr.length;i++){
      temp += valarr[i];
      if(i != valarr.length-1){
        temp += ",";
      }
    }
    temp += "]";
    return temp;
  }
}