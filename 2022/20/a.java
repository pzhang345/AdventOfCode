import java.io.File;
import java.util.*;
class a {
  public static void unscramble(ArrayList<Numbers> list, int id){
    Numbers temp;
    int add;
    for(int i = 0; i <list.size();i++){
      add = 0;
      if(list.get(i).id == id){
        temp = list.get(i);
        list.remove(i);
        while(temp.value+i+add<0){
          add += list.size();
          //list.add(i+temp.value+list.size(),temp);
        }
        while(temp.value+i+add>list.size()){
          add -= list.size();
          //list.add(i+temp.value-list.size(),temp);
        }
        list.add(i+temp.value+add,temp);
        break;
      }
    }
    if(id<list.size()){
      unscramble(list,id+1);
    }
  }
  public static int[] find(ArrayList<Numbers> list){
    int start = -1;
    for(int i = 0; i<list.size();i++){
      if(list.get(i).value == 0){
        start = i;
        break;
      }
    }
    int[] code = new int[3];
    int num = start;
    for(int i = 0; i<3;i++){
      num += 1000;
      while(num>=list.size()){
        num -= list.size();
      }
      code[i] = list.get(num).value;
    }
    return code;
  }
  public static void main(String[] args) {
    String str = "";
    ArrayList<Numbers> list = new ArrayList<Numbers>();
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        list.add(new Numbers(Integer.parseInt(str)));
      }
      System.out.println(list);
      unscramble(list,0);
      System.out.println(list);
      int[] ans = find(list);
      int total = 0;
      for(int i:ans){
        System.out.println(i);
        total += i;
      }
      System.out.println("Answer: " + total);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Numbers{
  int value;
  int id;
  static int currid = 0;
  public Numbers(int v){
    id = currid;
    currid ++;
    value = v;
  }
  public String toString(){
    return "" + value;
  }
}