import java.io.*;
class a{
    public static int getNumber(String str){
        int sum = 0;
        for(int i = 0; i < str.length(); i++){
            sum += str.charAt(i);
            sum *= 17;
            sum %= 256;
        }
        return sum;
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
        long total = 0;
        for(String i: lines[0].split(",")){
            System.out.println(i + " " + getNumber(i));
            total += getNumber(i);
        }
        System.out.println("Answer: " + total);
    }
}