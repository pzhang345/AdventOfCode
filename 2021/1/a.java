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
        int previous = Integer.parseInt(lines[0]);
        int count = 0;
        for(String i: lines){
            if(previous < Integer.parseInt(i)){
                count ++;
            }
            previous = Integer.parseInt(i);
        }
        System.out.println("Answer: " + count);
    }
    
    
}

