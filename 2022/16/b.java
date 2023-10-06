import java.io.File;
import java.util.*;
class b {
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
      pos.findpath(0,0, new ArrayList<Integer>(),"","",true,pos);
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
  static int min = 26;
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
  public void findpath(int total,int time,ArrayList<Integer> used,String strp1,String strp2,boolean p1,Valves other){
    iterations ++;
    if(p1){
      time ++;
      strp1 += " " + name;
      /*if(time == 27){
        System.out.println(time);
      }*/
    }
    else{
      strp2 += " " + name;
    }
    //boolean run = true;
    /*if(time == min/2+1 && !name.equals("AA")){
      str += "\n";
      start.findpath(total,min/2,used,str,);
      run = false;
    }*/
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
      boolean tempp1 = p1;
      for(int j:flowrates){
        if(!used.contains(j)){
          if(tempp1){
            count += j*(min-temptime);
          }
          else if(!tempp1){
            count += j*(min-temptime);
            temptime+=2;
          }
          tempp1 = !tempp1;
          if(temptime >= min){
            break;
          }
        }
      }
      /*if(time == 25){
        System.out.println(count);
      }*/
      if(count>max){
        if(flowrate != 0 && !in){
          //System.out.println("a");
          //System.out.println(flowrate*(min-time) + " " + flowrate);
          //System.out.println(total);
          temp.add(flowrate);
          if(p1){
             other.findpath(total+flowrate*(min-time),time,temp,strp1 + "" + flowrate*(min-time),strp2,!p1,this);
          }
          else{
             other.findpath(total+flowrate*(min-time),time,temp,strp1,strp2 + "" + flowrate*(min-time),!p1,this);
          }
          temp.remove(Integer.valueOf(flowrate));
        }
        //System.out.println(count);
        for(int i = path.size()-1; i>=0;i--){
          //System.out.println(time + " " + i);
          Valves tempv = path.get(i);
          other.findpath(total,time,temp,strp1,strp2,!p1,tempv);
        }
      }
    }
    else{
      System.out.println(total);
      if(total>max){
        //System.out.println(this + " " + total);
        max = total;
        System.out.println(strp1);
        System.out.println(strp2);
        System.out.println();
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