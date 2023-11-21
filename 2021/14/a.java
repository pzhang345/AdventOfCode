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
        String str = lines[0];
        String[][] replacements = new String[lines.length-2][2]; 
        for(int i = 0; i<replacements.length; i++){
            replacements[i] = lines[i+2].split(" -> ");
        }
        System.out.println(replacements[0][0]);
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < str.length()-1;j++){
                for(String[] k:replacements){
                    if(str.substring(j,j+2).equals(k[0])){
                        str = str.substring(0,j+1) + k[1] + str.substring(j+1);
                        j++;
                        break;
                    }
                }
            }
        }
        int[] count = new int[128];
        for(int i = 0; i < count.length; i++){
            count[i] = 0;
        }
        for(int i = 0; i < str.length(); i++){
            count[str.charAt(i)] ++;
        }
        int max = -1;
        int min = -1;
        for(int i: count){
            if(i != 0){
                if(max == -1 || i > max){
                    max = i;
                }
                if(min == -1 || i < min){
                    min = i;
                }
            }
        }
        System.out.println("Answer: " + (max-min));
    }
}


