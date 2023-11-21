import java.io.*;
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
        int[] crabs = new int[lines[0].split(",").length];
        int max = Integer.parseInt(lines[0].split(",")[0]);
        int min = Integer.parseInt(lines[0].split(",")[0]);
        for(int i = 0; i<lines[0].split(",").length;i++){
            crabs[i] = Integer.parseInt(lines[0].split(",")[i]);
            if(crabs[i]>max){
                max = crabs[i];
            }
            if(crabs[i]<min){
                min = crabs[i];
            }
        }
        int minFuel = -1;
        for(int i = min;i < max;i++){
            int fuel = 0;
            for(int j:crabs){
                int x = Math.abs(j-i);
                fuel += x * (x + 1)/2;
            }
            if(minFuel == -1 || fuel < minFuel){
                minFuel = fuel;
            }
        }
        System.out.println("Answer: " + minFuel);
    }
}


