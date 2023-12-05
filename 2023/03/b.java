import java.io.*;
class b{
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
    public static int nextToNum(String[] lines, int i1, int j1){
        int num1 = 0;
        int num2 = 0;
        int i2 = -1;
        int j2 = -1;
        for(int i = -1; i <= 1; i ++){
            for(int j = -1; j <= 1; j++){
                if(isNum("" + lines[i1 + i].charAt(j + j1))){
                    int[] info = getNum(lines, i1 + i, j1 + j);
                    if(num1 == 0){
                        num1 = info[0];
                        i2 = info[1];
                        j2 = info[2];
                    }
                    else if(info[1] != i2 || info[2] != j2){
                        num2 = info[0];
                    }
                }
            }
        }
        System.out.println("" + num1 + " " + num2);
        return num1 * num2;
    }
    public static int[] getNum(String[] lines, int i1, int j1){
        int[] info = new int[3];
        String num = "";
        int j = j1;
        try{
            while(true){
                Integer.parseInt("" + lines[i1].charAt(j));
                num += lines[i1].charAt(j);
                j++;
            }
        }catch(Exception e){}
        j = j1 - 1;
        try{
            while(true){
                Integer.parseInt("" + lines[i1].charAt(j));
                num = lines[i1].charAt(j) + num;
                j--;
            }
        }catch(Exception e){}
        info[0] = Integer.parseInt(num);
        info[1] = i1;
        info[2] = j;
        return info;
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
            for(int j = 0; j < lines[i].length();j++){
                if(lines[i].charAt(j) == '*'){
                    sum += nextToNum(lines, i, j);
                }
            }
        }
        System.out.println("Answer: " + sum);
    }
}