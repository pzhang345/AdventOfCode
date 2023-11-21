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
        int[] incorrect = new int[4];
        char[] chars = {')',']','}','>'};
        for(String i:lines){
            while(i.contains("()") || i.contains("[]") || i.contains("{}") || i.contains("<>")){
                i = i.replace("()","");
                i = i.replace("[]","");
                i = i.replace("{}","");
                i = i.replace("<>","");
                System.out.println(i);
                
            }
            if(i.length() != 0){
                for(int j = 0; j < i.length();j++){
                    for(int k = 0; k < chars.length; k++){
                        if(i.charAt(j) == chars[k]){
                            incorrect[k] ++;
                            System.out.println(chars[k]);
                            j = i.length();
                            break;
                        }
                    }
                } 
            }
            System.out.println("a");
        }
        System.out.println("Answer: " + ((3 * incorrect[0]) + (57 * incorrect[1]) + (1197 * incorrect[2]) + (25137 * incorrect[3])));
    }
}


