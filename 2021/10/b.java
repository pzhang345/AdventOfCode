import java.io.*;
import java.util.*;
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
        char[] chars = {'(','[','{','<'};
        ArrayList<Long> scores = new ArrayList<Long>();
        for(String i:lines){
            while(i.contains("()") || i.contains("[]") || i.contains("{}") || i.contains("<>")){
                i = i.replace("()","");
                i = i.replace("[]","");
                i = i.replace("{}","");
                i = i.replace("<>","");
                System.out.println(i);
                
            }
            Long score = 0L;
            if(!(i.contains(")") || i.contains("]") || i.contains("}") || i.contains(">"))){
                for(int j = i.length() - 1; j >= 0; j--){
                    score *= 5;
                    for(int k = 0; k < chars.length; k++){
                        if(i.charAt(j) == chars[k]){
                            score += 1 + k;
                            break;
                        }
                    }
                }
                scores.add(score);
            }
            System.out.println("a");
        }
        scores.sort(Comparator.naturalOrder());
        System.out.println("Answer: " + scores.get(scores.size()/2));
    }
}


