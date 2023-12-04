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
        top: for(int i = 0; i < lines.length;i++){
            String[] rounds = lines[i].split(":")[1].split(";");
            for(int j = 0; j < rounds.length; j++){
                String[] numcolor = rounds[j].trim().split(", ");
                for(int k = 0; k < numcolor.length; k++){
                    int num = Integer.parseInt(numcolor[k].split(" ")[0].trim());
                    String color = numcolor[k].split(" ")[1].trim();
                    if(color.equals("red") && num > 12){
                        continue top;
                    }
                    else if(color.equals("green") && num > 13){
                         continue top;
                    }
                    else if(color.equals("blue") && num > 14){
                         continue top;
                    }
                }
            }
            sum += 1 + i;
        }
        System.out.println(sum);
    }
}