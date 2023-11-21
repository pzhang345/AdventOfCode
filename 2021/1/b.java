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
        int[] nums = new int[lines.length];
        for(int  i = 0;i < lines.length;i++){
            nums[i] = Integer.parseInt(lines[i]);
        }
        int previous = nums[0] + nums[1] + nums[2];
        int count = 0;
        for(int i = 0; i < lines.length-2;i++){
            int sum = nums[i] + nums[i+1] + nums[i+2];
            if(previous < sum){
                count ++;
            }
            previous = sum;
        }
        System.out.println("Answer: " + count);
    }
    
    
}

