import java.io.*;
import java.util.*;
class b{
    public static int getNumber(String str){
        int sum = 0;
        for(int i = 0; i < str.length(); i++){
            sum += str.charAt(i);
            sum *= 17;
            sum %= 256;
        }
        return sum;
    }
    public static int contains(ArrayList<Lens> arr, String n){
        for(int i = 0; i < arr.size(); i++){
            if(arr.get(i).name.equals(n)){
                return i;
            }
        }
        return -1;
    }
    public static long getStrength(ArrayList<ArrayList<Lens>> arr){
        long count = 0;
        for(int i = 0; i < arr.size(); i++){
            for(int j = 0; j < arr.get(i).size(); j++){
                 Lens lens = arr.get(i).get(j);
                 count += (i + 1) * (j + 1) * lens.strength;
            }
        }
        return count;
    }
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
        ArrayList<ArrayList<Lens>> arr = new ArrayList<ArrayList<Lens>>();
        for(int i = 0; i < 256; i++){
            arr.add(new ArrayList<Lens>());
        }
        for(String i: lines[0].split(",")){
            if(i.contains("=")){
                String str = i.split("=")[0];
                int boxnum = getNumber(str);
                int index = contains(arr.get(boxnum),str);
                if(index != -1){
                    arr.get(boxnum).set(index,new Lens(Integer.parseInt(i.split("=")[1]),str));                    
                }
                else{
                    arr.get(boxnum).add(new Lens(Integer.parseInt(i.split("=")[1]),str));
                }
            }
            else{
                String str = i.split("-")[0];
                int boxnum = getNumber(str);
                int index = contains(arr.get(boxnum),str);
                if(index != -1){
                    arr.get(boxnum).remove(index);                    
                }
            }
            //System.out.println(i);
            //System.out.println(arr.get(0) + " " + arr.get(1) + " " + arr.get(2) + " " + arr.get(3));
        }
        //System.out.println(arr.get(0) + " " + arr.get(1) + " " + arr.get(2) + " " + arr.get(3));
        System.out.println("Answer: " + getStrength(arr));
    }
}
class Lens{
    int strength;
    String name;
    public Lens(int s, String n){
        strength = s;
        name = n;
    }
    public String toString(){
        return name + ": " + strength;
    }
}