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
        Reactor reactor = new Reactor();
        for(String i:lines){
            reactor.doInstruct(i);
        }
        long count = 0;
        for(Group i: Group.groups){
            count += i.getSize();
        }
        System.out.println("Answer: " + count);
    }
}
class Group{
    int minx;
    int maxx;
    int miny;
    int maxy;
    int minz;
    int maxz;
    boolean isOn;
    public static ArrayList<Group> groups = new ArrayList<Group>();
    public static int count;
    public Group(int[] start, int[] end,boolean isOn){
        minx = start[0];
        miny = start[1];
        minz = start[2];
        maxx = end[0];
        maxy = end[1];
        maxz = end[2];
        this.isOn = isOn;
        System.out.println(this);
        if(isOn && (maxx >= minx && maxy >= miny && maxz >= minz) && !add()){
            groups.add(this);
        }
        if(!isOn){
            removeOverlap();
        }
    }
    public Group(int sx,int sy, int sz, int ex, int ey, int ez,boolean isOn){
        minx = sx;
        miny = sy;
        minz = sz;
        maxx = ex;
        maxy = ey;
        maxz = ez;
        this.isOn = isOn;
        if(isOn && (maxx >= minx && maxy >= miny && maxz >= minz) && !add()){
            groups.add(this);
        }
        if(!isOn){
            removeOverlap();
        }
    }
    public Group(int sx,int sy, int sz, int ex, int ey, int ez,boolean isOn, ArrayList<Group> newGroup){
        minx = sx;
        miny = sy;
        minz = sz;
        maxx = ex;
        maxy = ey;
        maxz = ez;
        this.isOn = isOn;
        if(maxx >= minx && maxy >= miny && maxz >= minz){
            newGroup.add(this);
        }
    }
    public void removeOverlap(){
        ArrayList<Group> newGroups = new ArrayList<Group>();
        for(Group i: groups){
            int highminx = getExtreme(minx, i.minx, true);
            int lowmaxx = getExtreme(maxx, i.maxx, false);
            int highminy = getExtreme(miny, i.miny, true);
            int lowmaxy = getExtreme(maxy, i.maxy, false);
            int highminz = getExtreme(minz, i.minz, true);
            int lowmaxz = getExtreme(maxz, i.maxz, false);
            int[] x = new int[4];
            int[] y = new int[4];
            int[] z = new int[4];
            x[0] = i.minx;
            x[1] = highminx;
            x[2] = lowmaxx + 1;
            x[3] = i.maxx + 1; 
            y[0] = i.miny;
            y[1] = highminy;
            y[2] = lowmaxy + 1;
            y[3] = i.maxy + 1; 
            z[0] = i.minz;
            z[1] = highminz;
            z[2] = lowmaxz + 1;
            z[3] = i.maxz + 1; 
            if(highminx <= lowmaxx && highminy <= lowmaxy && highminz <= lowmaxz){
                for(int j = 1; j < x.length; j++){
                    for(int k = 1; k < y.length; k++){
                        for(int l = 1; l < z.length; l++){
                            if(!((minx <= x[j-1] && x[j] - 1 <= maxx) && (miny <= y[k-1] && y[k] - 1 <= maxy) && (minz <= z[l-1] && z[l] - 1 <= maxz))){
                                new Group(x[j - 1],y[k - 1],z[l - 1],x[j] - 1, y[k] - 1, z[l] - 1,true,newGroups);
                            }
                        }
                    }
                }
            }
            else{
                new Group(i.minx,i.miny,i.minz,i.maxx,i.maxy,i.maxz,true,newGroups);
            }
        }
        groups = newGroups;
    }
    public boolean add(){
        for(Group i: groups){
            int highminx = getExtreme(minx, i.minx, true);
            int lowmaxx = getExtreme(maxx, i.maxx, false);
            int highminy = getExtreme(miny, i.miny, true);
            int lowmaxy = getExtreme(maxy, i.maxy, false);
            int highminz = getExtreme(minz, i.minz, true);
            int lowmaxz = getExtreme(maxz, i.maxz, false);
            int[] x = new int[4];
            int[] y = new int[4];
            int[] z = new int[4];
            x[0] = minx;
            x[1] = highminx;
            x[2] = lowmaxx + 1;
            x[3] = maxx + 1; 
            y[0] = miny;
            y[1] = highminy;
            y[2] = lowmaxy + 1;
            y[3] = maxy + 1; 
            z[0] = minz;
            z[1] = highminz;
            z[2] = lowmaxz + 1;
            z[3] = maxz + 1; 
            if(highminx <= lowmaxx && highminy <= lowmaxy && highminz <= lowmaxz){
                for(int j = 1; j < x.length; j++){
                    for(int k = 1; k < y.length; k++){
                        for(int l = 1; l < z.length; l++){
                            if(!((i.minx <= x[j-1] && x[j] - 1 <= i.maxx) && (i.miny <= y[k-1] && y[k] - 1 <= i.maxy) && (i.minz <= z[l-1] && z[l] - 1 <= i.maxz))){
                                new Group(x[j - 1],y[k - 1],z[l - 1],x[j] - 1, y[k] - 1, z[l] - 1,true);
                            }
                        }
                    }
                }
                return true;
            }
            
        }
        return false;
    }
    private int getExtreme(int i, int j, boolean isHigh){
        if(isHigh){
            if(i > j){
                return i;
            }
            else{
                return j;
            }
        }
        else{
            if(i < j){
                return i;
            }
            else{
                return j;
            }
        }
    }
    public long getSize(){
        return (long)(maxx - minx + 1) * (long)(maxy - miny + 1) * (long)(maxz - minz + 1);
    }
    public String toString(){
        return "|x=" + minx + ".." + maxx + ",y=" + miny + ".." + maxy + ",z=" + minz + ".." + maxz + "|";
    }
}
class Reactor{
    public Reactor(){
        
    }
    public void doInstruct(String str){
        String[] range = str.split(",");
        int[] start = new int[3];
        int[] end = new int[3];
        for(int i = 0; i < range.length; i++){
            start[i] = Integer.parseInt(range[i].split("=")[1].split("\\.\\.")[0]);
            end[i] = Integer.parseInt(range[i].split("=")[1].split("\\.\\.")[1]);
        }
        
        if(str.substring(0,2).equals("on")){
            new Group(start,end,true);
        }
        if(str.substring(0,3).equals("off")){
            new Group(start,end,false);
        }
    }
}


