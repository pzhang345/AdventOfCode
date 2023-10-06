import java.io.File;
import java.util.*;
public class b {
  public static int amountneeded = 0;
  public static int closest;
  public static void getclosest(Dir d){
    for(Dir i:d.getlist()){
      getclosest(i);
      if(i.getsize()>amountneeded && closest-amountneeded>i.getsize()-amountneeded){
        System.out.println(i.getsize()-amountneeded);
        closest = i.getsize();
      }
    }
  }
  public static void main(String[] args) {
    String str = "";
    Dir files = new Dir("/");
    Dir current = files;
    File txt = new File("dir.txt");
    try{
      Scanner sc = new Scanner(txt);
      while (sc.hasNextLine()){
        //System.out.println("a");
        str = sc.nextLine();
        if(str.equals("$ cd ..")){
          current = current.getprevious();
        }
        else if(str.indexOf("$ cd")==0){
          //if(current.getcontain(str.substring(5)) == null){
            //current.addfile(new Dir(str.substring(5)));
          //}
          current = current.getcontain(str.substring(5));
        }
        else if(str.substring(0,3).equals("dir")){
          current.addfile(new Dir(str.substring(4)));
        }
        else if(str.equals("$ ls")){}
        else{
          current.increasesize(Integer.parseInt(str.substring(0,str.indexOf(" "))));
        }
      }
      amountneeded =  files.getsize() - 40000000;
      System.out.println(amountneeded + " " + files.getsize());
      closest = files.getsize();
      getclosest(files);
      System.out.println("Answer: " + closest);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Dir{
  String name;
  int size = 0;
  Dir previous;
  ArrayList<Dir> contains = new ArrayList<Dir>();
  public Dir(String n){
    name = n;
  }
  public void increasesize(int s){
    size += s; 
  }
  public int getsize(){
    int total = size;
    for(Dir i:contains){
      total += i.getsize();
    }
    return total; 
  }
  public void addfile(Dir d){
    contains.add(d);
    d.setprevious(this);
  }
  public void setprevious(Dir d){
    previous = d;
  }
  public Dir getprevious(){
    return previous;
  }
  public String getname(){
    return name;
  }
  public Dir getcontain(String n){
    for(Dir i:contains){
      if (n.equals(i.getname())){
        return i;
      } 
    }
    return null;
  } 
  public ArrayList<Dir> getlist(){
    return contains;
  }
}
