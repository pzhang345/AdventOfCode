import java.io.*;

class Test{
    public static void main(String[] args){

        File file = new File("Test.java");
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] lines = reader.lines().toArray(size -> new String[size]);
            reader.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    
}

