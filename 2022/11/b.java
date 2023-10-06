//May not work with other text files, To make it work change combined to all the tests multiplied by each other
import java.io.File;
import java.util.*;
class b {
  public static void main(String[] args) {
    String[] str = new String[7];
    File file = new File("Monkeys.txt");
    Monkey[] monkeys = new Monkey[8];
    for(int i = 0; i<monkeys.length;i++){
      monkeys[i] = new Monkey();
    }
    int count = 0;
    long max1 = 0;
    long max2 = 0;
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        for(int i = 0; i<str.length;i++){
          str[i] = sc.nextLine();
          //System.out.println(str[i]);
        }
        str[1] = str[1].substring(18);
        str[2] = str[2].substring(23);
        str[3] = str[3].substring(21);
        str[4] = str[4].substring(29);
        str[5] = str[5].substring(30);
        monkeys[count].setvalues(str[1].split(", "), str[2],Integer.parseInt(str[3]));
        monkeys[count].settcon(monkeys[Integer.parseInt(str[4])]);
        System.out.println(Integer.parseInt(str[4]));
        monkeys[count].setfcon(monkeys[Integer.parseInt(str[5])]);
        count ++;
      }
      System.out.println("a");
      for(int i = 0; i<10000;i++){
        for(int j = 0; j<monkeys.length;j++){
          System.out.println(i +" "+ j);
          monkeys[j].pass();
        }
      }
      for(int i = 0; i<monkeys.length;i++){
        if(max1<monkeys[i].getcount()){
          max1 = monkeys[i].getcount();
          count = i;
        }
      }
      for(int i = 0; i<monkeys.length;i++){
        if(max2<monkeys[i].getcount() && i!=count){
          max2 = monkeys[i].getcount();
        }
      }
      System.out.println(monkeys[0].getcount());
      System.out.println(monkeys[1].getcount());
      System.out.println(monkeys[2].getcount());
      System.out.println(monkeys[3].getcount());
      //System.out.println(monkeys[0].getitems());
      //System.out.println()
      System.out.println("Answer: " + (max1 * max2));
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Monkey{
  public static long combined = 9699690;
  ArrayList<Long> items;
  long count = 0;
  String operation;
  int test;
  Monkey truecon;
  Monkey falsecon;
  public Monkey(){}
  public void  setvalues(String[] iitems, String o, int t){
    items = new ArrayList<Long>();
    operation = o;
    test = t;
    //combined *= test;
    for(int i = 0; i<iitems.length;i++){
      additem(Long.parseLong(iitems[i]));
    }
    //count = iitems.length;
  }
  public long getcount(){
    return count;
  }
  public ArrayList<Long> getitems(){
    return items;
  }
  public void settcon(Monkey tcon){
    truecon = tcon;
  }
  public void setfcon(Monkey fcon){
    falsecon = fcon;
  }
  public void increasecount(){
    count++;
  }
  public void additem(long i){
    long temp;
    if(operation.substring(2).equals("old")){
      temp = i;
    }
    else{
      temp = Long.parseLong(operation.substring(2));
    }
    if(operation.charAt(0) == '+'){
      i += temp;
    }
    else if(operation.charAt(0) == '*'){
      i *= temp;
    }
    items.add(i%combined);
  }
  public void getbored(){
    for(int i = 0; i<items.size();i++){
      items.set(i,items.get(i)/3);
    }
  }
  public void pass(){
    //System.out.println(items);
    //getbored();
    for(int i = 0; i<items.size();i++){
      increasecount();
      if(items.get(i)%test == 0){
        truecon.additem(items.get(i));
        //truecon.increasecount();
      }
      else{
        falsecon.additem(items.get(i));
        //falsecon.increasecount();
      }
    }
    items.clear();
  }
}