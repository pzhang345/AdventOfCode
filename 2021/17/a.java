import java.io.*;
class a{
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
        System.out.println(lines[0].split("=")[1]);
        int minx = Integer.parseInt(lines[0].split("=")[1].split("\\.\\.")[0]);
        int maxx = Integer.parseInt(lines[0].split("=")[1].split("\\.\\.")[1].split(",")[0]);
        int miny = Integer.parseInt(lines[0].split("=")[2].split("\\.\\.")[0]);
        int maxy = Integer.parseInt(lines[0].split("=")[2].split("\\.\\.")[1]);
        System.out.println("Answer: " + ((miny)*(miny + 1)/2));
        
    }
}


