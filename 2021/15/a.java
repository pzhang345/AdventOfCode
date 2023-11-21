import java.io.*;
class a{
    public static int[][] checkSum(int[][] sum, int[][] nums, int i1, int j1){
        for(int i = 0; i < sum.length; i++){
            int sFromI = 0;
            int sFromJ = 0;
            for(int j = 0; j < sum[0].length; j++){
                i -= sFromI;
                j -= sFromJ;
                sFromI = 0;
                sFromJ = 0;
                //System.out.println("" + i + " " + j);
                if(i != 0 && (sum[i - 1][j] == -1 || sum[i - 1][j] > sum[i][j] + nums[i - 1][j])){
                    sum[i - 1][j] = sum[i][j] + nums[i - 1][j];
                    sFromI = 1;
                    sFromJ = 1;
                    
                }
                if(j != 0 && (sum[i][j - 1] == -1 || sum[i][j - 1] > sum[i][j] + nums[i][j - 1])){
                    sum[i][j - 1] = sum[i][j] + nums[i][j - 1];
                    sFromJ = 2;
                }
                if(j == -1){
                    j = sum[0].length - 1;
                    i --;
                }
                if(i != sum.length - 1 && (sum[i + 1][j] == -1 || sum[i + 1][j] > sum[i][j] + nums[i + 1][j])){
                    sum[i + 1][j] = sum[i][j] + nums[i + 1][j];
                    
                }
                if(j != sum[i].length - 1 && (sum[i][j + 1] == -1 || sum[i][j + 1] > sum[i][j] + nums[i][j + 1])){
                    //System.out.println("" + sum[i][j] + " " + nums[i][j + 1]);
                    sum[i][j + 1] = sum[i][j] + nums[i][j + 1];
                }
            }
        }
        return sum;
    }
    public static int[][] baseline(int[][] sum, int[][] nums, int i, int j){
        for(int k = 0; k < 2; k++){
            try{
                if(sum[i + k][j + 1-k] == -1 || sum[i + k][j + 1-k] > sum[i][j] + nums[i + k][j + 1 - k]){
                    sum[i + k][j + 1 - k] = sum[i][j] + nums[i + k][j + 1 - k];
                    baseline(sum,nums,(i + k),(j + 1 - k));
                }
            }catch(Exception e){}
        }
        return sum;
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
        int[][] nums = new int[lines.length][lines[0].length()];
        int[][] sum =  new int[lines.length][lines[0].length()];
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < nums[0].length; j++){
                nums[i][j] = Integer.parseInt("" + lines[i].charAt(j));
                sum[i][j] = -1;
            }
        }
        sum[0][0] = 0;
        sum = checkSum(sum, nums, 0, 0);
        /*
        for(int[] i:sum){
            for(int j:i){
                System.out.print(j + ", ");
            }
            System.out.println();
        }

        */
        System.out.println("Answer: " + sum[sum.length - 1][sum[0].length - 1]);
    }
}


