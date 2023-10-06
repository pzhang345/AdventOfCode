import java.io.File;
import java.util.*;
class a {
  public static void main(String[] args) {
    ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
    String str = "";
    Monkey m1 = null;
    Monkey m2 = null;
    Monkey root = null;
    File file = new File("monkeys.txt");
    int count = 0;
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        monkeys.add(new Monkey(str.substring(0,str.indexOf(":"))));
      }
      sc.close();
      sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        str = str.substring(str.indexOf(":")+2);
        if(monkeys.get(count).name.equals("root")){
          root = monkeys.get(count);
        }
        if(str.indexOf(" ")<0){
          monkeys.get(count).setvalue(Long.parseLong(str));
        }
        else{
          for(int i = 0; i<monkeys.size();i++){
            if(str.substring(0,str.indexOf(" ")).equals(monkeys.get(i).name)){
              m1 = monkeys.get(i);
            }
            if(str.substring(str.lastIndexOf(" ") + 1).equals(monkeys.get(i).name)){
              m2 = monkeys.get(i);
            }
          }
          monkeys.get(count).setoperation(m1,m2,str.charAt(str.indexOf(" ") + 1));
        }
        count ++;
      }
      root.dooperation();
      System.out.println("Answer: " + root.value);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Monkey{
  String name;
  long value;
  boolean hasvalue;
  char operation;
  Monkey monk1;
  Monkey monk2;
  public Monkey(String n){
    name = n;
    hasvalue = false;
  }
  public void setvalue(long v){
    value = v;
    hasvalue = true;
  }
  public void setoperation(Monkey m1, Monkey m2, char o){
    monk1 = m1;
    monk2 = m2;
    operation = o;
  }
  public void dooperation(){
    if(!monk1.hasvalue){
      monk1.dooperation();
    }
    if(!monk2.hasvalue){
      monk2.dooperation();
    }
    if(operation == '+'){
      value = monk1.value + monk2.value;
    }
    if(operation == '-'){
      value = monk1.value - monk2.value;
    }
    if(operation == '*'){
      value = monk1.value * monk2.value;
    }
    if(operation == '/'){
      value = monk1.value / monk2.value;
    }
  }
}