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
        for(String i: lines){
            if(i.equals("")){
                break;
            }
            System.out.println(i);
            new Map(i);
        }
        for(Map i:Map.maps){
            for(Equation j: i.equations){
                j.createMap();
            }
            System.out.println(i.name);
        }
        System.out.println();
        new Nums();
        //new Nums(new long[]{1,10},new long[]{1,10},new long[]{1,10},new long[]{1,10});
        Nums.runq();
        System.out.println("Answer: " + Nums.sum);
    }
}
class Equation{
    boolean hasCompare;
    char var;
    boolean isGreater;
    boolean hasNext;
    int num;
    String result;
    Map resultm;
    public Equation(String line,boolean h){
        hasCompare = h;
        if(hasCompare){
            var = line.charAt(0);
            isGreater = line.contains(">");
            result = line.split(":")[1];
            num = Integer.parseInt(line.substring(2).split(":")[0]);
        }
        else{
            var = ' ';
            result = line;
        }
        if(!(result.equals("A") || result.equals("R"))){
            hasNext = true;
        }
    }
    public void createMap(){
        if(hasNext){
            for(Map i:Map.maps){
                if(result.equals(i.name)){
                    resultm = i;
                }
            }
        }
    }
    public void createNum(Nums n,boolean needSplit){
        if(!needSplit){
            if(hasNext){
                new Nums(n,resultm);
            }
            else if(result.equals("A")){
                Nums.sum += n.sum();
            }
        }
        else{
            if(hasNext){
                new Nums(n,var,num,isGreater,resultm);
            }
            else if(result.equals("A")){
                long tempx = n.x[1] - n.x[0] + 1;
                long tempm = n.m[1] - n.m[0] + 1;
                long tempa = n.a[1] - n.a[0] + 1;
                long temps = n.s[1] - n.s[0] + 1;
                if(var == 'x'){
                    if(isGreater){
                        tempx = n.x[1] - num;
                    }
                    else{
                        tempx = num - n.x[0];
                    }
                }
                else if(var == 'm'){
                    if(isGreater){
                        tempm = n.m[1] - num;
                    }
                    else{
                        tempm = num - n.m[0];
                    }
                }
                else if(var == 'a'){
                    if(isGreater){
                        tempa = n.a[1] - num;
                    }
                    else{
                        tempa = num - n.a[0];
                    }
                }
                else if(var == 's'){
                    if(isGreater){
                        temps = n.s[1] - num;
                    }
                    else{
                        temps = num - n.s[0];
                    }
                }
                Nums.sum += tempx * tempm * tempa * temps;
            }
            if(var == 'x'){
                if(isGreater){
                    n.x[1] = num;
                }
                else{
                    n.x[0] = num;
                }
            }
            if(var == 'm'){
                if(isGreater){
                    n.m[1] = num;
                }
                else{
                    n.m[0] = num;
                }
            }
            if(var == 'a'){
                if(isGreater){
                    n.a[1] = num;
                }
                else{
                    n.a[0] = num;
                }
            }
            if(var == 's'){
                if(isGreater){
                    n.s[1] = num;
                }
                else{
                    n.s[0] = num;
                }
            }
        }
    }
    public boolean splitNum(Nums n){
        if(!hasCompare){
            createNum(n,false);
        }
        else if(var == 'x'){
            if(isGreater){
                if(n.x[0] > num){ 
                    createNum(n,false);
                    return true;
                }
                else if(n.x[1] < num){
                    
                }
                else{
                    createNum(n,true);
                }
            }
            else{
                if(n.x[0] > num){ 
                    
                }
                else if(n.x[1] < num){
                    createNum(n,false);
                    return true;
                }
                else{
                    createNum(n,true);
                }
            }
        }
        else if(var == 'm'){
             if(isGreater){
                if(n.m[0] > num){ 
                    createNum(n,false);
                    return true;
                }
                else if(n.m[1] < num){

                }
                else{
                    createNum(n,true);
                }
            }
            else{
                if(n.m[0] > num){ 

                }
                else if(n.m[1] < num){
                    createNum(n,false);
                    return true;
                }
                else{
                    createNum(n,true);
                }
            }
        }
        else if(var == 'a'){
             if(isGreater){
                if(n.a[0] > num){ 
                    createNum(n,false);
                    return true;
                }
                else if(n.a[1] < num){

                }
                else{
                    createNum(n,true);
                }
            }
            else{
                if(n.a[0] > num){ 
                    
                }
                else if(n.a[1] < num){
                    createNum(n,false);
                    return true;
                }
                else{
                    createNum(n,true);
                }
            }
        }
        else if(var == 's'){
            //System.out.println("a");
            if(isGreater){
                if(n.s[0] > num){ 
                    createNum(n,false);
                    return true;
                }
                else if(n.s[1] < num){

                }
                else{
                    createNum(n,true);
                }
            }
            else{
                if(n.s[0] > num){
                    
                }
                else if(n.s[1] < num){
                    createNum(n,false);
                    return true;
                }
                else{
                    //System.out.println("a");
                    createNum(n,true);
                }
            }
        }
        return false;
    }
}
class Map{
    String name;
    ArrayList<Equation> equations = new ArrayList<Equation>();
    public static ArrayList<Map> maps = new ArrayList<Map>();
    public Map(String line){
        name = line.split("\\{")[0];
        String[] elem = line.split("\\{")[1].split("\\}")[0].split(",");
        for(int i = 0; i < elem.length; i++){
            if(i != elem.length - 1){
                equations.add(new Equation(elem[i],true));
            }
            else{
                equations.add(new Equation(elem[i], false));
            }
        }
        maps.add(this);
    }
}
class Nums{
    long[] x;
    long[] m;
    long[] a;
    long[] s;
    Map currm;
    public static long sum = 0;
    public static ArrayList<Nums> q = new ArrayList<Nums>();
    public Nums(){
        x = new long[]{1,4000};
        m = new long[]{1,4000};
        a = new long[]{1,4000};
        s = new long[]{1,4000};
        for(Map i:Map.maps){
            if(i.name.equals("in")){
                currm = i;
            }
        }
        q.add(this);
    }
    public Nums(Nums n, char c, int num, boolean isGreater, Map map){
        currm = map;
        x = new long[]{n.x[0],n.x[1]};
        m = new long[]{n.m[0],n.m[1]};
        a = new long[]{n.a[0],n.a[1]};
        s = new long[]{n.s[0],n.s[1]};
        if(c == 'x'){
            if(isGreater){
                x[0] = num + 1; 
            }
            else{
                x[1] = num - 1;
            }
        }
        else if(c == 'm'){
            if(isGreater){
                m[0] = num + 1; 
            }
            else{
                m[1] = num - 1;
            }
        }
        else if(c == 'a'){
            if(isGreater){
                a[0] = num + 1; 
            }
            else{
                a[1] = num - 1;
            }
        }
        else if(c == 's'){
            if(isGreater){
                s[0] = num + 1; 
            }
            else{
                s[1] = num - 1;
            }
        }
        q.add(this);
    }
    public Nums(Nums n, Map map){
        currm = map;
        x = new long[]{n.x[0],n.x[1]};
        m = new long[]{n.m[0],n.m[1]};
        a = new long[]{n.a[0],n.a[1]};
        s = new long[]{n.s[0],n.s[1]};
        q.add(this);
    }
    public Nums(long[] x, long[] m, long[] a, long[] s){
        this.x = x;
        this.m = m;
        this.a = a;
        this.s = s;
        for(Map i:Map.maps){
            if(i.name.equals("in")){
                currm = i;
            }
        }
        q.add(this);
    }
    public static void runq(){
        while(!q.isEmpty()){
            q.get(0).move();
            q.remove(0);
        }
    }
    public void move(){
        ArrayList<Equation> eq = currm.equations;
        for(int i = 0; i < eq.size(); i++){
            System.out.println(this);
            if(eq.get(i).splitNum(this)){
                break;
            }
        }
    }
    public long sum(){
        long sum = (x[1] - x[0] + 1) * (m[1] - m[0] + 1) * (a[1] - a[0] + 1) * (s[1] - s[0] + 1);
        System.out.println(sum);
        return sum;
    }
    public String toString(){
        return currm.name + " " + x[0] + " " + x[1] + " " + m[0] + " " + m[1] + " " + a[0] + " " + a[1] + " " + s[0] + " " + s[1];
    }
}