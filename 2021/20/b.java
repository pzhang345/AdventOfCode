import java.io.*;
class b{
    public static boolean[][] addWhiteSpace(boolean[][] arr,int num,boolean value){
        boolean[][] newArr = new boolean[arr.length + 2*num][arr[0].length + 2*num];
        System.out.println(newArr[0][0]);
        for(int i = 0; i < newArr.length; i++){
            for(int j = 0; j < newArr.length; j++){
                newArr[i][j] = value;
            }
        }
        for(int i = num; i < arr.length + num; i++){
            for(int j = num; j < arr[0].length + num; j++){
                newArr[i][j] = arr[i-num][j-num];
            }
        }
        return newArr;
    }
    public static int BToD(String b){
        int num = 0;
        for(int i = 0; i < b.length(); i++){
            if(b.charAt(i) == '1'){
                num += Math.pow(2,b.length() - i - 1);
            }
        }
        return num;
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
        boolean[][] startArr = new boolean[lines.length - 2][lines[2].length()];
        boolean[][] newArr = new boolean[lines.length - 2][lines[0].length()];
        String dictionary = lines[0];
        for(int i = 2; i < lines.length; i++){
            for(int j = 0; j < lines[i].length(); j++){
                //System.out.println(i);
                if(lines[i].charAt(j) == '.'){
                    startArr[i - 2][j] = false;
                }
                else if(lines[i].charAt(j) == '#'){
                    startArr[i - 2][j] = true;
                }
            }
        }

        for(int i = 0; i < 50; i++){
            newArr = addWhiteSpace(startArr,3,false);
            if(dictionary.charAt(0) == '#' && i%2 == 1){
                startArr = addWhiteSpace(startArr, 4,true);
            }
            else if(i%2 == 0 && dictionary.charAt(0) == '#' && dictionary.charAt(dictionary.length() - 1) == '.'){
                startArr = addWhiteSpace(startArr, 4,false);
            }
            
            for(int j = 0; j < startArr.length - 2; j++){
                for(int k = 0; k < startArr[j].length - 2; k++){
                    String str = "";
                    for(int l = 0; l < 9; l++){
                        if(startArr[j + l/3][k + l%3]){
                            str += "1";
                        }
                        else{
                            str += "0";
                        }
                    }
                    //System.out.println(str);
                    newArr[j][k] = (dictionary.charAt(BToD(str)) == '#');
                }
            }
            startArr = newArr;
        }
        int count = 0;
        for(boolean[] i: startArr){
            for(boolean j:i){
                if(j){
                    //System.out.print("#");
                    count ++;
                }
                else{
                    //System.out.print(".");
                }
            }
            //System.out.println();
        }
        System.out.println("" + startArr.length + " " + startArr[0].length);
        System.out.println("Answer: " + count);
    }
}


