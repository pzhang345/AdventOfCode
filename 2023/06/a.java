import java.io.*;
class a{
    public static int[][] combineArr(String a, String b){
        while(a.indexOf("  ")  != -1 || b.indexOf("  ") != -1){
            a = a.replace("  "," ");
            b = b.replace("  "," ");
        }
        int[] arr1 = StoIntArr(a.trim().split(" "));
        int[] arr2 = StoIntArr(b.trim().split(" "));
        int[][] temp = new int[arr1.length][2]; 
        for(int i = 0; i < temp.length; i++){
            temp[i][0] = arr1[i];
            temp[i][1] = arr2[i];
        }
        return temp;
    }
    public static int[] StoIntArr(String[] a){
        int[] temp = new int[a.length];
        for(int i = 0; i < temp.length; i++){
            temp[i] = Integer.parseInt(a[i]);
        }
        return temp;
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
        String t = lines[0].split(":")[1];
        String d = lines[1].split(":")[1];
        int[][] tAndD = combineArr(t, d);
        long total = 1;
        int count = 0;
        for(int i = 0; i < tAndD.length; i++){
            count = 0;
            for(int j = 0; j < tAndD[i][0]; j++){
                if(((tAndD[i][0] - j) * j) > tAndD[i][1]){
                    count ++;
                }
            }
            total *= count;
        }
        System.out.println("Answer: " + total);
    }
}