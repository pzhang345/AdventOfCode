import java.io.*;
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
        long total = 0;
        // Seq seq = new Seq(lines[0]);
        // System.out.println(seq.getCount(0,0,0));
        for(String i: lines){
           Seq seq = new Seq(i);
           System.out.println(i);
           total += seq.getCount(0,0,0);
           //System.out.print(seq.getCount());
           //System.out.println(total);
        }
        System.out.println("Answer:" + total);
    }
}
class Seq{
    char[] seq; 
    int[] nums;
    long[][][] save;
    public Seq(String str){
        seq = fiveTimes(str.split(" ")[0].toCharArray());
        nums = toIntArr(str.split(" ")[1],true);
        //seq = str.split(" ")[0].toCharArray();
        //nums = toIntArr(str.split(" ")[1],false);
        save = new long[seq.length + 1][nums.length + 1][max(nums) + 2];
        for(int i = 0; i < save.length; i++){
            for(int j = 0; j < save[i].length; j++){
                for(int k = 0; k < save[i][j].length; k++){
                    save[i][j][k] = -1;
                }
            }
        }
    }
    public int[] toIntArr(String str,boolean toFive){
        int[] arrnum;
        if(toFive){
            String[] arr = str.split(",");
            arrnum = new int[arr.length * 5];
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < arr.length; j++){
                    arrnum[i * arr.length + j] = Integer.parseInt(arr[j]);
                }
            }
        }
        else{
            String[] arr = str.split(",");
            arrnum = new int[arr.length];
            for(int i = 0; i < arr.length; i++){
                arrnum[i] = Integer.parseInt(arr[i]);
            }
        }
        return arrnum;
    }
    public int max(int[] a){
        int max = 0;
        for(int i: a){
            if(i > max){
                max = i;
            }
        }
        return max;
    }
    public char[] fiveTimes(char[] arr){
        char[] temp = new char[5 * arr.length + 4];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < arr.length; j++){
                temp[i * (arr.length + 1) + j] = arr[j];
            }
            try{
            temp[i * (arr.length + 1) - 1] = '?';
            }catch(Exception e){}
        }
        //printArr(temp);
        return temp;
    }
    public long getCount(int indexarr,int index, int count){
        //System.out.println(indexarr + " " + index + " " + count);
        if(save[indexarr][index][count] != -1){
            return save[indexarr][index][count];
        }
        long num = 0;
        if(indexarr == seq.length){
            if(index == nums.length - 1 && count == nums[index]){
                return 1;
            }
            else if(index == nums.length && count == 0){
                return 1;
            }
            else{
                return 0;
            }
        } 
        else if(seq[indexarr] == '#'){
            if(index == nums.length || nums[index] < count){
                //System.out.println("a" + index + " " + count);
                num = 0;
            }
            else{
                num = getCount(indexarr + 1, index, count + 1);
            }
        }
        else if(seq[indexarr] == '.'){
            if(count == 0){
                num = getCount(indexarr + 1, index, 0);
            }
            else if(count > 0){
                if(count == nums[index]){
                    num = getCount(indexarr + 1, index + 1, 0);
                } 
                else{
                    num = 0;
                }
            }
        }
        else{
            //if #
            if(index == nums.length || nums[index] < count){
                num += 0;
            }
            else{
                num += getCount(indexarr + 1, index, count + 1);
            }
            //if .
            if(count == 0){
                num += getCount(indexarr + 1, index, 0);
            }
            else if(count > 0){
                if(count == nums[index]){
                    num += getCount(indexarr + 1, index + 1, 0);
                } 
                else{
                    num += 0;
                }
            }
        }
        save[indexarr][index][count] = num;
        return num;
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