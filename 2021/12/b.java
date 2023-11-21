import java.io.*;
import java.util.*;
class b{
    public static void main(String[] args){

        File file = new File("text.txt");
        String[] lines = new String[0];
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            lines = reader.lines().toArray(size -> new String[size]);
            reader.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        for(String i:lines){
            for(String j:i.split("-")){
                boolean hasCave = false;
                for(Cave k:Cave.allCaves){
                    if(j.equals(k.name)){
                        hasCave = true;
                    }
                }
                if(!hasCave){
                    new Cave(j);
                }
            }
        }
        for(String i:lines){
            Cave.findName(i.split("-")[0]).addLink(Cave.findName(i.split("-")[1]));
        }
        for(Cave i:Cave.allCaves){
            System.out.println(i + " " + i.link);
        }
        System.out.println(Cave.allCaves);
        Cave.findName("start").move(new ArrayList<Cave>(),true);
        System.out.println("Answer: " + Cave.count);
    }
}
class Cave{
    String name;
    int size;
    ArrayList<Cave> link;
    public static ArrayList<Cave> allCaves = new ArrayList<Cave>();
    public static int count = 0;
    public static Cave findName(String name){
        for(Cave i: allCaves){
            if(i.name.equals(name)){
                return i;
            }
        }
        return null;
    }
    public static ArrayList<Cave> copyArr(ArrayList<Cave> cave){
        ArrayList<Cave> temp = new ArrayList<Cave>();
        for(Cave i:cave){
            temp.add(i);
        }
        return temp;
    }
    public Cave(String n){
        name = n;
        if(name.toUpperCase().equals(name)){
            size = -1;
        }
        else{
            size = 1;
        }
        link = new ArrayList<Cave>();
        allCaves.add(this);
    }
    public void addLink(Cave c){
        link.add(c);
        c.link.add(this);
    }
    public String toString(){
        return name;
    }
    public void move(ArrayList<Cave> path,boolean canRepeat){
        path = Cave.copyArr(path);
        path.add(this);
        for(Cave i:link){
            if(i.name.equals("end")){
                count ++;
                //System.out.println(path);
            }
            else if(i.name.equals("start")){}
            else if(i.size == -1 || !path.contains(i)|| canRepeat){
                if(i.size == 1 && path.contains(i)){
                    i.move(path,false);
                }
                else{
                    i.move(path,canRepeat);
                }
                //System.out.println(this + "-" + i);
            }
        }
    }
}