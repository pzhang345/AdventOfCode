import java.io.File;
import java.util.*;
class a {
  /*public static void getgeodeamount(Storage storage,int p1,int p2,int p3){
    for(int i = 1; i<=24;i++){
      if(i>p3 && storage.obsidianrobots.number>0){
        storage.phase = 3;
      }
      else if(i>p2 && storage.clayrobots.number>0){
        storage.phase = 2;
      }
      else if(i>p1 && storage.orerobots.number>0){
        storage.phase = 1;
        //System.out.println("a");
      }
    storage.build();
    }
  }*/
  public static int max = 0;
  public static void getgeodeamount(Storage storage,int count){
    //if(count == 5){
      //System.out.println("a");
    //}
    int total = storage.geode + storage.geoderobots.number;
    for(int i = count; i<24; i++){
      total += (24 - i + storage.geoderobots.number);
    }
    //System.out.println(total);
    if(count <= 24 && total>max){
      if(storage.isenough(3)){
        Storage stor1 = storage.copy();
        stor1.build(3);
        getgeodeamount(stor1,count+1);
      }
      if(storage.isenough(2)){
        Storage stor2 = storage.copy();
        stor2.build(2);
        getgeodeamount(stor2,count+1);
      }
      if(storage.isenough(1)){
        Storage stor3 = storage.copy();
        stor3.build(1);
        getgeodeamount(stor3,count+1);
      }
      if(storage.isenough(0)){
        Storage stor4 = storage.copy();
        stor4.build(0);
        getgeodeamount(stor4,count+1);
      }
      storage.tick();
      getgeodeamount(storage,count+1);
    }
    else{
      if(storage.geode>max){
        //System.out.println(max);
        max = storage.geode;
      }
    }
  }
  public static void main(String[] args) {
    String str = "";
    File file = new File("machines.txt");
    //int max = 0;
    Robots ore;
    Robots clay;
    Robots obsidian;
    Robots geode;
    Storage storage;
    int count = 0;
    int total = 0;
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        ore = new Robots(Integer.parseInt(str.substring(str.indexOf("costs")+6,str.indexOf(" ore."))),0,0,1);
        str = str.substring(str.indexOf(" ore.") + 5);
        clay = new Robots(Integer.parseInt(str.substring(str.indexOf("costs")+6,str.indexOf(" ore."))),0,0,0);
        str = str.substring(str.indexOf(" ore.") + 5);
        obsidian = new Robots(Integer.parseInt(str.substring(str.indexOf("costs")+6,str.indexOf(" ore"))),
                              Integer.parseInt(str.substring(str.indexOf("and ")+4,str.indexOf(" clay."))),0,0);
        str = str.substring(str.indexOf(" clay.") + 6);
        geode = new Robots(Integer.parseInt(str.substring(str.indexOf("costs")+6,str.indexOf(" ore"))), 0,
                              Integer.parseInt(str.substring(str.indexOf("and ")+4,str.indexOf(" obsidian."))),0);
        //System.out.println(ore);
        //System.out.println(clay);
        //System.out.println(obsidian);
        //System.out.println(geode);
        max = 0;
        count ++;
        storage = new Storage(ore,clay,obsidian,geode);
        getgeodeamount(storage,1);
        /*for(int i = 1; i<=24;i++){
          for(int j = i; j<=24;j++){
            for(int k = j; k<=24;k++){
              storage.reset();
              getgeodeamount(storage,i,j,k);
              System.out.println(storage.ore);
              if(storage.geode>max){
                System.out.println(i + " " + j + " " + k);
                System.out.println(storage.ore + " " + storage.clay + " " + storage.obsidian + " " + storage.geode);
                System.out.println(storage.orerobots.number + " " + storage.clayrobots.number + " " + storage.obsidianrobots.number + " " + storage.geoderobots.number);
                max = storage.geode;
              }
            }
          }
        }*/
        System.out.println(max);
        total += count * max;
      }
      System.out.println(count + " " + total);
      System.out.println("Answer: " + total);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Storage{
  Robots orerobots;
  Robots clayrobots;
  Robots obsidianrobots;
  Robots geoderobots;
  int ore;
  int clay;
  int obsidian;
  int geode;
  //int phase;
  public Storage(Robots o, Robots c, Robots ob, Robots g){
    orerobots = o;
    clayrobots = c;
    obsidianrobots = ob;
    geoderobots = g;
    ore = 0;
    clay = 0;
    obsidian = 0;
    geode = 0;
  }
  public Storage(Robots o, Robots c, Robots ob, Robots g, int or, int cl, int obs, int ge){
    orerobots = o;
    clayrobots = c;
    obsidianrobots = ob;
    geoderobots = g;
    ore = or;
    clay = cl;
    obsidian = obs;
    geode = ge;
  }
  public Storage copy(){
    return new Storage(orerobots.copy(),clayrobots.copy(),obsidianrobots.copy(),geoderobots.copy(),ore,clay,obsidian,geode);
  }
  public void tick(){
    ore += orerobots.number;
    clay += clayrobots.number;
    obsidian += obsidianrobots.number;
    geode += geoderobots.number;
  }
  public boolean isenough(int num){
    switch(num){
      case 0: 
        if(ore>=orerobots.ore && clay>=orerobots.clay && obsidian>=orerobots.obsidian){
          return true;
        }
      case 1:
        if(ore>=clayrobots.ore && clay>=clayrobots.clay && obsidian>=clayrobots.obsidian){
          return true;
        }
      case 2:
        if(ore>=obsidianrobots.ore && clay>=obsidianrobots.clay && obsidian>=obsidianrobots.obsidian){
          return true;
        }
      case 3:
        if(ore>=geoderobots.ore && clay>=geoderobots.clay && obsidian>=geoderobots.obsidian){
          return true;
        }
    }
    return false;
  }
  public void build(int num){
    switch(num){
      case 0: 
        if(ore>=orerobots.ore && clay>=orerobots.clay && obsidian>=orerobots.obsidian){
          removematerials(orerobots);
          orerobots.number ++;
          ore --;
        }
        break;
      case 1:
        if(ore>=clayrobots.ore && clay>=clayrobots.clay && obsidian>=clayrobots.obsidian){
          removematerials(clayrobots);
          clayrobots.number ++;
          clay --;
        }
        break;
      case 2:
        if(ore>=obsidianrobots.ore && clay>=obsidianrobots.clay && obsidian>=obsidianrobots.obsidian){
          removematerials(obsidianrobots);
          obsidianrobots.number ++;
          obsidian --;
        }
        break;
      case 3:
        if(ore>=geoderobots.ore && clay>=geoderobots.clay && obsidian>=geoderobots.obsidian){
          removematerials(geoderobots);
          geoderobots.number ++;
          geode --;
        }
        break;
    }
    tick();
  }
  public void reset(){
    //phase = 0;
    ore = 0;
    clay = 0;
    obsidian = 0;
    geode = 0;
    orerobots.number = 1;
    clayrobots.number = 0;
    obsidianrobots.number = 0;
    geoderobots.number = 0;
  }
  public void removematerials(Robots r){
    ore -= r.ore;
    clay -= r.clay;
    obsidian -= r.obsidian;
  }
}
class Robots{
  int ore;
  int clay;
  int obsidian;
  int number;
  public Robots(int o, int c, int ob,int n){
    ore = o;
    clay = c;
    obsidian = ob;
    number = n;
  }
  public Robots copy(){
    return new Robots(ore,clay,obsidian,number);
  }
  public String toString(){
    return ore + " " + clay + " " + obsidian + " " + number;
  }
}