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
        int count = 0;
        long[] nums = new long[lines.length];
        for(int j = 0; j < nums.length; j ++){
            nums[j] = 1;
        }
        for(String i: lines){
            int mult = 0;
            String[] card = i.split(":")[1].split("\\|");
            String[] front = card[0].replaceAll("  "," ").trim().split(" ");
            String[] back = card[1].replaceAll("  "," ").trim().split(" ");
            boolean[] has = new boolean[100];
            for(int j = 0; j < has.length; j ++){
                has[j] = false;
            }
            for(String j: front){
                has[Integer.parseInt(j)] = true;
            }
            for(String j: back){
                if(has[Integer.parseInt(j)]){
                    mult += 1;
                }
            }
            System.out.println(mult);
            //System.out.println(count);
            for(int j = 1; j <= mult; j++){
                nums[j + count] +=  nums[count];
            }
            count ++;
        }
        long sum = 0;
        for(long i: nums){
            System.out.println(i);
            sum += i;
        }
        System.out.println("Answer: " + sum);
    }
}