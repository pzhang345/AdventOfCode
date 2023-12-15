import java.io.*;
import java.util.*;
class a{
    public static void printArr(char[][] arr){
        for(char[] i:arr){
            for(char j:i){
                System.out.print(j);
            }
            System.out.println();
        }
    }
    public static char[][] copy(char[][] arr){
        char[][] temp = new char[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }
    public static int checkRepeat(ArrayList<char[][]> arrList, char[][] arr){
        int count = 0;
        for(char[][] arrs:arrList){
            out:for(int i = 0; i < arr.length; i++){
                for(int j = 0; j < arr[i].length; j++){
                    if(arrs[i][j] != arr[i][j]){
                        break out;
                    }
                    if(i == arr.length - 1 && j == arr[i].length - 1){
                        return count;
                    }
                }
            }
            count ++;
        }
        return -1;
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
        char[][] map = new char[lines.length][lines[0].length()];
        for(int i = 0; i < map.length; i++){
            map[i] = lines[i].toCharArray();
        }
        printArr(map);
        System.out.println();
        ArrayList<char[][]> arrs = new ArrayList<char[][]>();
        for(int n = 0; n < 1000000000; n++){
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[i].length; j++){
                    if(map[i][j] == 'O'){
                        int count = 0;
                        try{
                            while(map[i - count - 1][j] == '.'){
                                map[i - count][j] = '.';
                                map[i - count - 1][j] = 'O';
                                count ++;
                            }
                        }catch(Exception e){}
                    }
                }
            }
            for(int j = 0; j < map[0].length; j++){
                for(int i = 0; i < map.length; i++){
                    if(map[i][j] == 'O'){
                        int count = 0;
                        try{
                            while(map[i][j - count - 1] == '.'){
                                map[i][j - count] = '.';
                                map[i][j - count - 1] = 'O';
                                count ++;
                            }
                        }catch(Exception e){}
                    }
                }
            }
            for(int i = map.length - 1; i >= 0; i--){
                for(int j = 0; j < map[i].length; j++){
                    if(map[i][j] == 'O'){
                        int count = 0;
                        try{
                            while(map[i + count + 1][j] == '.'){
                                map[i + count][j] = '.';
                                map[i + count + 1][j] = 'O';
                                count ++;
                            }
                        }catch(Exception e){}
                    }
                }
            }
            for(int j = map[0].length - 1; j >= 0; j--){
                for(int i = 0; i < map.length; i++){
                    if(map[i][j] == 'O'){
                        int count = 0;
                        try{
                            while(map[i][j  + count + 1] == '.'){
                                map[i][j + count] = '.';
                                map[i][j + count + 1] = 'O';
                                count ++;
                            }
                        }catch(Exception e){}
                    }
                }
            }
            int index = checkRepeat(arrs, map);
            System.out.println(index);
            int range = n - index;
            if(index != -1){
                System.out.println(index + " " + n);
                while(n + range < 1000000000){
                    //System.out.println(n);
                    n += range;
                }
            }
            arrs.add(copy(map));
        }
        printArr(map);
        int sum = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == 'O'){
                    sum += (map.length - i);
                }
            }
        }
        System.out.println(sum);
    }
}