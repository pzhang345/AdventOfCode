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
        long count = 0;
        for(Brick i:map.bricks){
            count += map.remove(i);
            //System.out.println(i.name + " " + count);
        }
        System.out.println(count);
    }
}
class Brick{
    char name;
    int id;
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
        return id + ":" + minx + "," + miny + "," + minz + "~" + maxx + "," + maxy + "," + maxz;
    }
}
class Map{
    ArrayList<Brick> bricks;
    ArrayList<Integer> q;
    boolean[] isRev;
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
            curr.id = i;
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
    public int remove(Brick curr){
        isRev = new boolean[bricks.size()];
        q = new ArrayList<Integer>();
        isRev[curr.id] = true;
        for(Brick i: curr.onTop){
            q.add(i.id);
        }
        while(q.size() != 0){
            int index = q.get(0);
            curr = bricks.get(index);
            boolean isSupport = false;
            for(Brick i: curr.support){
                if(!isRev[i.id]){
                    isSupport = true;
                    break;
                }
            }
            if(!isSupport){
                isRev[curr.id] = true;
                for(Brick i: curr.onTop){
                    q.add(i.id);
                }
            }
            q.remove(0);
        }


        int count = 0;
        for(boolean i: isRev){
            if(i){
                count++;
            }
        }
        return count - 1;
    }
}