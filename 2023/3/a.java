import java.io.*;
class a{
    public static boolean isNum(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public static boolean nextToSymbol(String[] lines, int i1, int j1){
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                try{
                    if(lines[i+i1].charAt(j+j1) != '.' && !isNum("" + lines[i+i1].charAt(j+j1))){
                        System.out.println(lines[i+i1].charAt(j+j1));
                        return true;
                    }
                }catch(Exception e){}
            }
        }
        return false;
    }
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
        for(int i = 0; i < lines.length; i++){
            boolean nextToSymbol = false;
            int num = 0;
            for(int j = 0; j < lines[i].length();j++){
                if(isNum("" + lines[i].charAt(j))){
                    num = num*10 + Integer.parseInt("" + lines[i].charAt(j));
                    if(nextToSymbol || nextToSymbol(lines, i, j)){
                        nextToSymbol = true;
                    }
                }
                else{
                    if(nextToSymbol){
                        sum += num;
                        System.out.println(num);
                    }
                    num = 0;
                    nextToSymbol = false;
                }
            }
            if(nextToSymbol){
                sum += num;
                System.out.println(num);
            }
            num = 0;
            nextToSymbol = false;
        }
        System.out.println("Answer: " + sum);
    }
}