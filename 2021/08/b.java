import java.io.*;
class b{
    public static boolean compare(String str1,String str2){
        for(int i = 0; i < str1.length(); i++){
            if(!str2.contains("" + str1.charAt(i))){
                return false;
            }
        }
        return true;
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
        for(String i:lines){
            String[] arr = {i.substring(0, i.indexOf(" \u007C ")), i.substring(i.indexOf(" \u007C ") + 3)};
            //System.out.println(i.contains("\u007C"));
            String[] match = new String[10];
            for(String j: arr[0].split(" ")){
                if(j.length() == 2){
                    match[1] = j;
                }
                else if(j.length() == 4){
                    match[4] = j;
                }
                else if(j.length() == 3){
                    match[7] = j;
                }
                else if(j.length() == 7){
                    match[8] = j;
                }
            }
            for(String j: arr[0].split(" ")){
                if(j.length() == 6){
                    if(compare(match[4],j)){
                        match[9] = j;
                    }
                    else if(compare(match[1],j)){
                        match[0] = j;
                    }
                    else{
                        match[6] = j;
                    }
                }
            }
            for(String j: arr[0].split(" ")){
                if(j.length() == 5){
                    if(compare(match[7],j)){
                        match[3] = j;
                    }
                    else if(compare(j,match[9])){
                        match[5] = j;
                    }
                    else{
                        match[2] = j;
                    }
                }
            }
            /*
            for(String j: match){
                System.out.print(j + " ");
            }
            */
            int num = 0;
            for(String j: arr[1].split(" ")){
                //System.out.print(j + " ");
                for(int k = 0; k < match.length; k++){
                    if(match[k].length() == j.length() && compare(match[k],j)){
                        num = 10 * num + k;
                    }
                }
            }
            sum += num;
            //System.out.println();
        }
        System.out.println("Answer: " + sum);
    }
}


