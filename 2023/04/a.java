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
        int sum = 0;
        for(String i: lines){
            int mult = 0;
            String[] card = i.split(":")[1].split("\\|");
            String[] front = card[0].replaceAll("  "," ").trim().split(" ");
            String[] back = card[1].replaceAll("  "," ").trim().split(" ");
            boolean[] has = new boolean[100];
            for(int j = 0; j < has.length; j ++){
                has[j] = false;
            }
            for(String j: front){
                has[Integer.parseInt(j)] = true;
            }
            for(String j: back){
                if(has[Integer.parseInt(j)]){
                    mult *= 2;
                    if(mult == 0){
                        mult = 1;
                    }
                }
            }
            sum += mult;
        }
        System.out.println("Answer: " + sum);
    }
}