import java.io.File;
import java.util.*;
class b {
  public static void unscramble(ArrayList<Numbers> list, int id){
    Numbers temp;
    int add;
    for(int i = 0; i <list.size();i++){
      add = 0;
      if(list.get(i).id == id){
        temp = list.get(i);
        list.remove(i);
        long tempval = temp.value;
        tempval = tempval % list.size();
        while(tempval+i+add<0){
          tempval += list.size();
          //list.add(i+temp.value+list.size(),temp);
        }
        while(tempval+i+add>list.size()){
          tempval -= list.size();
          //list.add(i+temp.value-list.size(),temp);
        }
        list.add(i+(int)(tempval),temp);
        break;
      }
    }
    if(id<list.size()){
      unscramble(list,id+1);
    }
  }
  public static long[] find(ArrayList<Numbers> list){
    int start = -1;
    for(int i = 0; i<list.size();i++){
      if(list.get(i).value == 0){
        start = i;
        break;
      }
    }
    long[] code = new long[3];
    int num = start;
    for(int i = 0; i<3;i++){
      num += 1000;
      num = num % list.size();
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
    File file = new File("num.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        list.add(new Numbers(Long.parseLong(str)*811589153));
      }
      //System.out.println(list);
      for(int i = 0; i<10;i++){
        System.out.println(i);
        unscramble(list,0);
      }
      //unscramble(list,0);
      //System.out.println(list);
      long[] ans = find(list);
      long total = 0;
      for(long i:ans){
        System.out.println(i);
        total += i;
      }
      System.out.println("Answer: " + total);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Numbers{
  long value;
  int id;
  static int currid = 0;
  public Numbers(long v){
    id = currid;
    currid ++;
    value = v;
  }
  public String toString(){
    return "" + value;
  }
}