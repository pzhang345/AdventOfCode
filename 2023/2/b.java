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
        int sum = 0;
        for(int i = 0; i < lines.length;i++){
            String[] rounds = lines[i].split(":")[1].split(";");
            int maxr = 0;
            int maxb = 0;
            int maxg = 0;
            for(int j = 0; j < rounds.length; j++){
                String[] numcolor = rounds[j].trim().split(", ");
                for(int k = 0; k < numcolor.length; k++){
                    int num = Integer.parseInt(numcolor[k].split(" ")[0].trim());
                    String color = numcolor[k].split(" ")[1].trim();
                    if(color.equals("red") && num > maxr){
                        maxr = num;
                    }
                    else if(color.equals("green") && num > maxb){
                         maxb = num;
                    }
                    else if(color.equals("blue") && num > maxg){
                         maxg = num;
                    }
                }
            }
            sum += maxr * maxb * maxg;
        }
        System.out.println(sum);
    }
}