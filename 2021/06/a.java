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
        ArrayList<Integer> fish = new ArrayList<Integer>();
        for(String i:lines[0].split(",")){
            fish.add(Integer.parseInt(i)); 
        }
        for(int i = 0;i < 80;i++){
            for(int j = fish.size() - 1;j >= 0;j--){
                if(fish.get(j) == 0){
                    fish.set(j,6);
                    fish.add(8);
                }
                else{
                    fish.set(j,fish.get(j)-1);
                }
            }
        }
        System.out.println("Answer: " + fish.size());
    }
}


