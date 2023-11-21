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
        long[] fish = new long[9];
        for(String i:lines[0].split(",")){
            fish[Integer.parseInt(i)] ++; 
        }
        for(int i = 0;i < 256;i++){
            long num0 = fish[0];
            for(int j = 0;j < fish.length - 1;j++){
                fish[j] = fish[j+1];
            }
            fish[6] += num0;
            fish[8] = num0;
        }
        long count = 0;
        for(long i:fish){
            count += i;
        }
        System.out.println("Answer: " + count);
    }
}


