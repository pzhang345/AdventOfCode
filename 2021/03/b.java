import java.io.*;
import java.util.*;
class a{
    public static int BtoD(String str){
        int count = 0;
        int pow = 1;
        for(int i = 0;i<str.length();i++){
            if(str.charAt(str.length() - i - 1) == '1'){
                count += pow;
            }
            pow *= 2;
        }
        return count;
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
        ArrayList<String> most = new ArrayList<String>(); 
        ArrayList<String> least = new ArrayList<String>(); 
        for(int i = 0;i<lines.length;i++){
            most.add(lines[i]);
            least.add(lines[i]);
        }
        int count = 0;
        int index = 0;
        char c;
        while(most.size() != 1){
            count = 0;
            for(String i: most){
                if(i.charAt(index) == '0'){
                    count ++;
                }
            }
            if(count > most.size()/2){
                c = '0';
            }
            else{
                c = '1';
            }
            for(int i = most.size() - 1;i >= 0;i--){
                if(c != most.get(i).charAt(index)){
                    most.remove(i);
                }
            }
            index ++;
        }
        index = 0;
        while(least.size() != 1){
            count = 0;
            for(String i: least){
                if(i.charAt(index) == '0'){
                    count ++;
                }
            }
            if(count > least.size()/2){
                c = '1';
            }
            else{
                c = '0';
            }
            for(int i = least.size() - 1; i >= 0;i--){
                if(c != least.get(i).charAt(index)){
                    least.remove(i);
                }
            }
            index ++;
        }
        System.out.println(most.size() + " " + most.get(0));
        System.out.println(least.size() + " " + least.get(0));
        System.out.println("Answer: " + (BtoD(most.get(0)) * BtoD(least.get(0))));
    }
}


