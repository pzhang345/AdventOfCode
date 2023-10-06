import java.io.File;
import java.util.*;
class a {
  public static void main(String[] args) {
    ArrayList<Valves> all = new ArrayList<Valves>();
    String str = "";
    int count = 0;
    Valves pos;
    File file = new File("tunnels.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        all.add(new Valves(str));
      }
      /*for(Valves i: all){
        System.out.println(i);
      }*/
      sc.close();
      sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        str = str.substring(str.indexOf("valves ") + 7);
        //System.out.println(str);
        for(int i = 0; i<str.split(", ").length;i++){
          for(Valves j:all){
            if(str.split(", ")[i].equals(j.name)){
              all.get(count).addvalve(j);
              break;
            }
          }
        }
        //System.out.println(all.get(count));
        count ++;
      }
      for(Valves i: all){
        System.out.println(i);
      }
      pos = all.get(0);
      for(Valves i: all){
        if(i.name.equals("AA")){
          pos = i;
        }
      }
      pos.findpath(0,0, new ArrayList<Integer>());
      System.out.println(Valves.iterations + " " + Valves.max);
      System.out.println("Answer: " + Valves.max);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Valves{
  ArrayList<Valves> path;
  int flowrate;
  String name;
  static long iterations = 0;
  static int max = 0;
  static int min = 30;
  static ArrayList<Integer> flowrates = new ArrayList<Integer>();
  public Valves(String str){
    name = str.substring(6,8);
    flowrate = Integer.parseInt(str.substring(str.indexOf("=") + 1, str.indexOf(";")));
    path = new ArrayList<Valves>();
    if(flowrate != 0){
      flowrates.add(flowrate);
    }
  }
  public void addvalve(Valves o){
    path.add(o);
  }
  /*public Valves highvalve(){
    int high = -1;
    Valves pipe;
    for(Valves i:path){
      if(i.flowrate>pipe){
        pipe = i;
        high = i.flowrate;
      }  
    }
    if(high != 0){
      return pipe;
    }
  }
  private Valves highvaluer(){
  
  }*/
  public void findpath(int total,int time,ArrayList<Integer> used){
    iterations ++;
    time ++;
    if (iterations%100000000 == 0){
      System.out.println(iterations);
    }
    if(time <= min){
      boolean in = false;
      ArrayList<Integer> temp = new ArrayList<Integer>();
      for(int i:used){
        if(flowrate == i){
          in = true; 
        }
        temp.add(i);
      }
      int count = total;
      int temptime = time;
      for(int j:flowrates){
        if(!used.contains(j)){
          count += j*(min-temptime);
          temptime++;
          if(temptime >= min){
            break;
          }
        }
      }
      //System.out.println(count);
      for(Valves i:path){
        //System.out.println(time + " " + i);
        if(count>max){
          i.findpath(total,time,temp);
        }
      }
      if(flowrate != 0 && !in){
        //System.out.println("a");
        total += flowrate*(min-time);
        //System.out.println(flowrate*(min-time) + " " + flowrate);
        //System.out.println(total);
        temp.add(flowrate);
        this.findpath(total,time,temp);
      }
    }
    else{
      if(total>max){
        //System.out.println(this + " " + total);
        max = total;
      }
    }
  }
  public ArrayList<Valves> getpath(){
    return path;
  }
  public String toString(){
    String temp = "[";
    for(int i = 0; i<path.size();i++){
      temp += path.get(i).name;
      if(i!=path.size()-1){
        temp += ", ";
      }
    }
    temp += "]";
    return name + " " + flowrate + " " + temp;
  }
}