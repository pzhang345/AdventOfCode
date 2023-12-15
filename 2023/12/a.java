import java.io.*;
import java.util.*;
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
        int total = 0;
        for(String i: lines){
            Seq seq = new Seq(i);
            total += seq.getCount();
            //System.out.println(total);
        }
        System.out.println(total);
    }
}
class Seq{
    char[] seq; 
    int[] nums;
    public Seq(String str){
        seq = str.split(" ")[0].toCharArray();
        nums = toIntArr(str.split(" ")[1]);
    }
    public int[] toIntArr(String str){
        String[] arr = str.split(",");
        int[] arrnum = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            arrnum[i] = Integer.parseInt(arr[i]);
        }
        return arrnum;
    }
    public boolean isCorrect(char[] thisseq){
        int count = 0;
        int index = 0;
        //printArr(thisseq);
        for(int i = 0; i < thisseq.length; i++){
            if(thisseq[i] == '#'){
                count ++;
            }
            else if(count != 0 && nums[index] != count){
                return false;
            }
            else if(count != 0){
                index ++;
                count = 0;
            }
        }
        if(index == nums.length || nums[index] == count){
            return true;
        }
        return false;
    }
    public int getCount(){
        ArrayList<Integer> indices = new ArrayList<Integer>();
        int count = 0;
        for(int i: nums){
            count += i;
        }
        for(int i = 0; i < seq.length; i++){
            if(seq[i] == '?'){
                indices.add(i);
            }
            if(seq[i] == '#'){
                count --;
            }
        }
        boolean[] bool = new boolean[indices.size()];
        //System.out.println(count);
        for(int i = 0; i < count; i++){
            bool[i] = true;
        }
        //printArr(bool);
        //System.out.println(count);
        int sum = 0;
        do{
            if(check(bool,indices)){
                sum++;
                //System.out.println("------------------------------------------");
            }
        }while(change(bool,count));
        //System.out.println(sum);
        return sum;
    }
    public boolean check(boolean[] bool, ArrayList<Integer> indices){
        char[] temp = copy();
        for(int i = 0; i < bool.length; i++){
            if(bool[i]){
                temp[indices.get(i)] = '#';
            }
            else{
                temp[indices.get(i)] = '.';
            }
        }
        //printArr(temp);
        return isCorrect(temp);
    }
    public boolean change(boolean[] arr, int count){
        do{
            for(int i = 0; i < arr.length; i++){
                if(i == arr.length - 1 && arr[i] == true){
                    return false;
                }
                if(!arr[i]){
                    arr[i] = true;
                    break;
                }
                else{
                    arr[i] = false;
                }
            }
        }while(!hasDigit(arr, count));
        return true;
        
    }
    public boolean hasDigit(boolean[] arr, int c){
        int count = 0;
        for(boolean i:arr){
            //System.out.print(i + " ");
            if(i){
                count ++;
            }
        }
        //System.out.println("|" + (count == c) + count + " " + c);
        return count == c;
    }
    public char[] copy(){
        char[] temp = new char[seq.length];
        for(int i = 0; i < seq.length; i++){
            temp[i] = seq[i];
        }
        return temp;
    }
    public void printArr(boolean[] bool){
        for(boolean i: bool){
            if(i){
                System.out.print("1");
            }
            else{
                System.out.print("0");
            }
        }
        System.out.println();
    }
    public void printArr(char[] arr){
        for(char i: arr){
            System.out.print(i);
        }
        System.out.println();
    }
}