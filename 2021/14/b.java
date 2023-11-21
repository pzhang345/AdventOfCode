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
        String str = lines[0];
        String[][] replacements = new String[lines.length-2][2]; 
        long[] nums = new long[replacements.length];
        long[] count = new long[128];
        for(int i = 0; i < count.length; i++){
            count[i] = 0;
        }
        for(int i = 0; i<replacements.length; i++){
            replacements[i] = lines[i+2].split(" -> ");
        }
        //System.out.println(replacements[0][0]);
        for(int i = 0; i < lines[0].length();i++){
            count[lines[0].charAt(i)] ++;
            if(i < lines[0].length() - 1){
                for(int j = 0; j < replacements.length;j++){
                    if(str.substring(i,i+2).equals(replacements[j][0])){
                        nums[j] ++;
                        break;
                    }
                }
            }
        }
        for(int i = 0; i < nums.length; i++){
            System.out.print("" + nums[i] + "-" + replacements[i][0] + ", ");
        }
        System.out.println();
        long[] tempnums = new long[replacements.length];
        for(int i = 0; i < 40; i++){
            for(int j = 0; j < replacements.length; j++){
                count[replacements[j][1].charAt(0)] += nums[j];
                tempnums[j] = nums[j];
                nums[j] = 0;
            }
            for(int j = 0; j < replacements.length; j++){
                for(int k = 0; k < replacements.length; k++){
                    if(("" + replacements[j][0].charAt(0) + replacements[j][1]).equals(replacements[k][0])){
                        nums[k] += tempnums[j];
                        /*
                        if(tempnums[j] > 0){
                            System.out.println("" + replacements[j][0].charAt(0) + replacements[j][1]);
                        }
                        */
                    }
                    if(("" + replacements[j][1] + replacements[j][0].charAt(1)).equals(replacements[k][0])){
                        nums[k] += tempnums[j];
                        //System.out.print(nums[k] + " ");
                        /*
                        if(tempnums[j] > 0){
                            System.out.println("" + replacements[j][1] + replacements[j][0].charAt(1));
                        }
                        */
                    }
                }
                //System.out.println();
            }
        }
        for(int i = 0; i < nums.length; i++){
            System.out.print("" + nums[i] + "-" + replacements[i][0] + ", ");
        }
        long max = -1;
        long min = -1;
        for(long i: count){
            if(i != 0){
                if(max == -1 || i > max){
                    max = i;
                }
                if(min == -1 || i < min){
                    min = i;
                }
            }
        }
        System.out.println(str);
        System.out.println("" + max + " " + min);
        System.out.println("Answer: " + (max-min));
    }
}


