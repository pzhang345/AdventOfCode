import java.io.*;
class b{
    public static String[] fileToArr(String fileName){
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
        return lines;
    }
    public static String StoInt(String str){
        String[] nums = {"one","two","three","four","five","six","seven","eight","nine"};
        a:for(int i = 0; i < str.length(); i++){
            for(int j = 0; j < nums.length; j ++){
                try{
                    if(str.substring(i,i+nums[j].length()).equals(nums[j])){
                        str = str.substring(0,i) + (j + 1) + str.substring(i+nums[j].length());
                        break a;
                    }
                }
                catch(Exception e){
                    
                }
                try{
                    Integer.parseInt("" + str.charAt(i));
                    break a;
                }catch(Exception e){}
            }
        }
        a:for(int i = str.length(); i >= 0; i--){
            for(int j = 0; j < nums.length; j ++){
                try{
                    if(str.substring(i - nums[j].length(),i).equals(nums[j])){
                        str = str.substring(0,i - nums[j].length()) + (j + 1) + str.substring(i);
                        break a;
                    }
                }
                catch(Exception e){

                }
                try{
                    Integer.parseInt("" + str.charAt(i));
                    //System.out.println(str.charAt(i));
                    break a;
                }catch(Exception e){}
            }
        }
        return str;
    }
    public static void main(String[] args){
        String[] lines = fileToArr("text.txt");
        int sum = 0;
        for(int i = 0; i<lines.length; i++){
            System.out.print(lines[i] + " ");
            lines[i] = StoInt(lines[i]);
            System.out.println(lines[i]);
            for(int j = 0; j < lines[i].length(); j++){
                try{
                    sum += Integer.parseInt("" + lines[i].charAt(j)) * 10;
                    break;
                }catch(Exception e){

                }
            }
            for(int j = lines[i].length(); j >= 0; j--){
                try{
                    sum += Integer.parseInt("" + lines[i].charAt(j));
                    break;
                }catch(Exception e){

                }
            }
        }
        System.out.println("Answer: " + sum);
    }
}