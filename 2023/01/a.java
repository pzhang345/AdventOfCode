import java.io.*;
class a{
    public static String[] fileToArr(String fileName){
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
        return lines;
    }
    public static void main(String[] args){
        String[] lines = fileToArr("text.txt");
        int sum = 0;
        for(int i = 0; i<lines.length; i++){
            for(int j = 0; j < lines[i].length(); j++){
                try{
                    sum += Integer.parseInt("" + lines[i].charAt(j)) * 10;
                    break;
                }catch(Exception e){

                }
            }
            for(int j = lines[i].length(); j >= 0; j--){
                try{
                    sum += Integer.parseInt("" + lines[i].charAt(j));
                    break;
                }catch(Exception e){

                }
            }
        }
        System.out.println("Answer: " + sum);
    }
}