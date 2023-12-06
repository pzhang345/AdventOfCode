import java.io.*;
class b{
    public static long[] StoLArr(String[] arr){
        long[] l = new long[arr.length];
        for(int i = 0; i < arr.length; i++){
            l[i] = Long.parseLong(arr[i]);
        }
        return l;
    }
    public static long aToB(long[][] arr, long a){
        for(int i = 0; i < arr.length; i++){
            if(arr[i][1] <= a &&  a <= arr[i][1] + arr[i][2]){
                return arr[i][0] + (a - arr[i][1]);
            }
        }
        return a;
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
        String[] arr = lines[0].split(":")[1].trim().split(" ");
        long[] seeds = StoLArr(arr);
        int[] sep = new int[7];
        int index = 0;
        for(int i = 0; i < lines.length; i++){
            if(lines[i].equals("")){
                sep[index] = i + 2;
                index ++;
            }
        }
        long[][][] aToB = new long[sep.length][][];
        for(int i = 0; i < aToB.length; i++){
            int max = lines.length + 2;
            try{
                aToB[i] = new long[sep[i+1] - sep[i] - 2][3];
                max = sep[i + 1];
            }catch(Exception e){
                aToB[i] = new long[lines.length - sep[i]][3];
            }
            System.out.println("" + sep[i] +" " +  max);
            for(int j = sep[i]; j < max - 2; j++){
                aToB[i][j - sep[i]] = StoLArr(lines[j].split(" "));
                System.out.println(aToB[i][j - sep[i]][1]);
            }
        }
        long min = -1;
        long count = 0;
        for(int i = 0; i < seeds.length/2; i++){
            for(long j = 0; j < seeds[2 * i + 1]; j++){ 
                long val = seeds[2 * i] + j;
                for(int k = 0; k < aToB.length; k++){
                    val = aToB(aToB[k], val);
                }
                if(min == -1 || min > val){
                    min = val;
                }
                count ++;
                if(count % 1000000 == 0){
                    System.out.println(count);
                }
            }
        }
        System.out.println("Answer: " + min);
    }
}
