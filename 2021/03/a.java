import java.io.*;
class a{
    public static int BtoD(String str){
        int count = 0;
        int pow = 1;
        for(int i = 0;i < str.length();i++){
            if(str.charAt(str.length() - i - 1) == '1'){
                count += pow;
            }
            pow *= 2;
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
        int[] count = new int[lines[0].length()];
        for(int i = 0;i<count.length;i++){
            count[i] = 0;
        }
        for(String i:lines){
            for(int j = 0; j<i.length();j++){
                if(i.charAt(j) == '0'){
                    count[j] ++;
                }
            }
        }
        String num1 = "";
        String num2 = "";
        for(int i = 0;i<count.length;i++){
            if(count[i]<lines.length/2){
                num1 += "0";
                num2 += "1";
            }
            else{
                num1 += "1";
                num2 += "0";
            }
        }
        System.out.println(num1 + " " + num2);
        System.out.println(BtoD(num1) + " " + BtoD(num2));
        System.out.println("Answer: " + (BtoD(num1) * BtoD(num2)));
    }
}


