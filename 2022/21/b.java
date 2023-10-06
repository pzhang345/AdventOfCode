import java.io.File;
import java.util.*;
class b {
  public static void main(String[] args) {
    ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
    String str = "";
    Monkey m1 = null;
    Monkey m2 = null;
    Monkey root = null;
    Monkey humn = null;
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
        if(monkeys.get(count).name.equals("humn")){
          humn = monkeys.get(count);
        }
        if(str.indexOf(" ")<0){
          monkeys.get(count).setvalue(Long.parseLong(str));
        }
        else{
          for(int i = 0; i<monkeys.size();i++){
            if(str.substring(0,str.indexOf(" ")).equals(monkeys.get(i).name)){
              m1 = monkeys.get(i);
              m1.parmonk = monkeys.get(count);
            }
            if(str.substring(str.lastIndexOf(" ") + 1).equals(monkeys.get(i).name)){
              m2 = monkeys.get(i);
              m2.parmonk = monkeys.get(count);
            }
          }
          monkeys.get(count).setoperation(m1,m2,str.charAt(str.indexOf(" ") + 1));
        }
        count ++;
      }
      humn.getparents();
      System.out.println(humn.parmonk);
      System.out.println(Monkey.usehumn);
      System.out.println(root);
      //humn.value = 0;
      root.dooperation();
      System.out.println("a");
      for(int i = Monkey.usehumn.size() - 1; i>= 0; i--){
        System.out.println(i + ": " + Monkey.usehumn.get(i));
        Monkey.usehumn.get(i).setequal();
      }
      System.out.println(root.monk1.value + " " + root.monk2.value + " " + humn.value);
      System.out.println("Answer: " + humn.value);
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
  Monkey parmonk;
  public static Monkey humn;
  public static ArrayList<Monkey> usehumn = new ArrayList<Monkey>();
  public Monkey(String n){
    name = n;
    hasvalue = false;
    if(name.equals("humn")){
      humn = this;
    }
  }
  public void setvalue(long v){
    value = v;
    hasvalue = true;
  }
  public void setequal(){
    long fixedval = 0;
    Monkey valchange = null;
    if(usehumn.contains(monk1) || monk1.name.equals("humn")){
      fixedval = monk2.value;
      valchange = monk1;
    }
    else if(usehumn.contains(monk2)){
      fixedval = monk1.value;
      valchange = monk2;
    }
    if(name.equals("root")){
      valchange.value = fixedval;
      System.out.println(monk1.value + " " + monk2.value);
    }
    else if(operation == '+'){
      valchange.value = value - fixedval;
    }
    else if(operation == '-'){
      if(valchange == monk1){
        monk1.value = value + fixedval;
      }
      else if(valchange == monk2){
        monk2.value = fixedval - value;
      }
    }
    else if(operation == '*'){
      valchange.value = value / fixedval;
    }
    else if(operation == '/'){
      if(valchange == monk1){
        monk1.value = value * fixedval;
      }
      else if(valchange == monk2){
        monk2.value = fixedval / value;
      }
    }
    System.out.println(monk1.value + " " + monk2.value);
  }
  public void setoperation(Monkey m1, Monkey m2, char o){
    monk1 = m1;
    monk2 = m2;
    operation = o;
  }
  public void setpar(Monkey m){
    parmonk = m;
  }
  public void dooperation(){
    if(!monk1.hasvalue){
      monk1.dooperation();
      //monk1.humneffect();
    }
    if(!monk2.hasvalue){
      monk2.dooperation();
      //monk2.humneffect();
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
  public void getparents(){
    if(!name.equals("root")){
      usehumn.add(parmonk);
      parmonk.getparents();
    }
  }
  public String toString(){
    return name;
  }
}