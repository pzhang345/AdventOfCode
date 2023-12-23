import java.io.*;
import java.util.*;
class a{
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
        Map map = new Map(lines);
        System.out.println();
        map.fall();
        int count = 0;
        for(Brick i:map.bricks){
            boolean canRemove = true;
            System.out.println(i);
            System.out.print(i.name + ": ");
            for(Brick j:i.onTop){
                if(j.support.size() == 1){
                    canRemove = false;
                }
                System.out.print(j.name + " ");
            }
            if(canRemove){
                count ++;
                //System.out.println(i);
            }
            System.out.println();
        }
        System.out.println(count);
    }
}
class Brick{
    char name;
    int minx;
    int maxx;
    int miny;
    int maxy;
    int minz;
    int maxz;
    ArrayList<Brick> support;
    ArrayList<Brick> onTop;
    public static int count = 0;
    public Brick(String line){
        String[] numstart = line.split("~")[0].split(",");
        String[] numend = line.split("~")[1].split(",");
        minx = Integer.parseInt(numstart[0]);
        miny = Integer.parseInt(numstart[1]);
        minz = Integer.parseInt(numstart[2]);
        maxx = Integer.parseInt(numend[0]);
        maxy = Integer.parseInt(numend[1]);
        maxz = Integer.parseInt(numend[2]);
        support = new ArrayList<Brick>();
        onTop = new ArrayList<Brick>();
        name = (char)('A' + count);
        count ++;
        System.out.println(this);
    }
    public String toString(){
        return name + ":" + minx + "," + miny + "," + minz + "~" + maxx + "," + maxy + "," + maxz;
    }
}
class Map{
    ArrayList<Brick> bricks;
    public Map(String[] lines){
        bricks = new ArrayList<Brick>();
        for(String i: lines){
            Brick temp = new Brick(i);
            boolean hasAdd = false;
            for(int j = 0; j < bricks.size(); j++){
                if(temp.minz <= bricks.get(j).minz){
                    bricks.add(j,temp);
                    hasAdd = true;
                    break;
                }
            }
            if(!hasAdd){
                bricks.add(temp);
            }
        }
        //System.out.println(bricks);
    }
    public void fall(){
        for(int i = 0; i < bricks.size(); i++){
            ArrayList<Brick> support = new ArrayList<Brick>();
            int maxz = 0;
            Brick curr = bricks.get(i);
            for(int j = 0; j < i; j++){
                Brick comp = bricks.get(j);
                if((curr.minx <= comp.maxx && comp.minx <= curr.maxx) &&
                   (curr.miny <= comp.maxy && comp.miny <= curr.maxy)){
                    if(maxz == 0 || maxz < comp.maxz){
                        maxz = comp.maxz;
                        support.clear();
                        support.add(comp);
                    }
                    else if(comp.maxz == maxz){
                        support.add(comp);
                    }
                }
            }
            curr.support = support;
            int range = curr.maxz - curr.minz;
            curr.minz = maxz + 1;
            curr.maxz = maxz + 1 + range;
            for(Brick j:support){
                j.onTop.add(curr);
            }
        }
    }
}