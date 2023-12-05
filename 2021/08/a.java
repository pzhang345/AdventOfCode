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
        int count = 0;
        for(String i:lines){
            String[] arr = {i.substring(0, i.indexOf(" \u007C ")), i.substring(i.indexOf(" \u007C ") + 3)};
            //System.out.println(i.contains("\u007C"));
            for(String j: arr[1].split(" ")){
                System.out.print(j + " ");
                if(j.length() == 2 || j.length() == 3 || j.length() == 4 || j.length() == 7){
                    count ++;
                }
            }
            System.out.println();
        }
        System.out.println("Answer: " + count);
    }
}


