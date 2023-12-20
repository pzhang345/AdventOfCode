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
        int count = 0;
        for(String i: lines){
            if(i.equals("")){
                break;
            }
            System.out.println(i);
            new Map(i);
            count ++;
        }
        for(Map i:Map.maps){
            for(Equation j: i.equations){
                j.createMap();
            }
            System.out.println(i.name);
        }
        int sum = 0;
        for(int i = count + 1; i < lines.length; i++){
            Nums nums = new Nums(lines[i]);
            if(nums.move()){
                sum += nums.getSum();
            }
        }
        System.out.println("Answer: " + sum);
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
    public boolean calculate(Nums n){
        if(!hasCompare){
            return true;
        }
        else if(var == 'x'){
            if(isGreater){
                return n.x > num;
            }
            else{
                return n.x < num;
            }
        }
        else if(var == 'm'){
            if(isGreater){
                return n.m > num;
            }
            else{
                return n.m < num;
            }
        }
        else if(var == 'a'){
            if(isGreater){
                return n.a > num;
            }
            else{
                return n.a < num;
            }
        }
        else if(var == 's'){
            if(isGreater){
                return n.s > num;
            }
            else{
                return n.s < num;
            }
        }
        return true;
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
    int x;
    int m;
    int a;
    int s;
    Map currm;
    public Nums(String line){
        line = line.substring(1,line.length() - 1);
        String[] arr = line.split(",");
        x = Integer.parseInt(arr[0].split("=")[1]);
        m = Integer.parseInt(arr[1].split("=")[1]);
        a = Integer.parseInt(arr[2].split("=")[1]);
        s = Integer.parseInt(arr[3].split("=")[1]);
        for(Map i:Map.maps){
            if(i.name.equals("in")){
                currm = i;
            }
        }
    }
    public boolean move(){
        while (true) {
            ArrayList<Equation> eq = currm.equations;
            for(int i = 0; i < eq.size(); i++){
                if(eq.get(i).calculate(this)){
                    if(eq.get(i).hasNext){
                        currm = eq.get(i).resultm;
                        break;
                    }
                    else if(eq.get(i).result.equals("A")){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
    }
    public int getSum(){
        return x + m + a + s;
    }
}