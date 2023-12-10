import java.io.*;
class a{
    public static int[] StoIntArr(String str){
        String[] snums = str.split(" ");
        int[] nums = new int[snums.length];
        int count = 0;
        for(String i:snums){
            nums[count] = Integer.parseInt(i);
            count ++;
        }
        return nums;
    }
    public static int getNextNum(int[] arr){
        int[] newArr = new int[arr.length-1];
        for(int i = 1; i < arr.length; i++){
            newArr[i-1] = arr[i] - arr[i-1];
            //System.out.print(newArr[i-1] + " ");
        }
        //System.out.println();
        if(is0(newArr)){
            return arr[arr.length - 1];
        }
        else{
            return arr[0] - getNextNum(newArr);
        }
    }
    public static boolean is0(int[] arr){
        for(int i: arr){
            if(i != 0){
                return false;
            }
        }
        return true;
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
        long sum = 0;
        for(String i: lines){
            int[] arr = StoIntArr(i);
            sum += getNextNum(arr);
        }
        System.out.println(sum);
    }
}