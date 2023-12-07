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
        long t = Long.parseLong(lines[0].replaceAll(" ","").split(":")[1]);
        long d = Long.parseLong(lines[1].replaceAll(" ","").split(":")[1]);
        int count = 0;
            for(int i = 0; i < t; i++){
                if(((t - i) * i) > d){
                    count ++;
                }
            }
        System.out.println("Answer: " + count);
    }
}