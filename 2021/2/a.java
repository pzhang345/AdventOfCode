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
        int x = 0;
        int y = 0;
        for(String i:lines){
            String str = i.substring(0,i.indexOf(" "));
            int number = Integer.parseInt(i.substring(i.indexOf(" ") + 1));
            //System.out.println(number);
            if(str.equals("forward")){
                x += number;
            }
            else if(str.equals("down")){
                y += number;
            }
            else if(str.equals("up")){
                y -= number;
            }
        }
        System.out.println("" + x + " " + y);
        System.out.println("Answer: " + (x*y));
    }
}