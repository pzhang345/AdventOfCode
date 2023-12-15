import java.io.*;
import java.util.*;
class a{
    public static int min(int a, int b){
        if(a > b){
            return b;
        }
        else{
            return a;
        }
    }
    public static void printMap(char[][][] a){
        for(char[][] i:a){
            for(char[] j:i){
                for(char k:j){
                    System.out.print(k);
                }
                System.out.println();
            }
            System.out.println();
        }
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
        int count = 0;
        ArrayList<Integer> index = new ArrayList<Integer>();
        index.add(-1);
        for(String i:lines){
            if(i.equals("")){
                index.add(count);
            }
            count++;
        }
        index.add(lines.length);
        count = 0;
        char[][][] maps = new char[index.size() - 1][][];
        for(int i = 0; i < maps.length; i++){
            maps[i] = new char[index.get(i + 1) - index.get(i) - 1][];
            for(int j = 0; j < maps[i].length; j++){
                maps[i][j] = lines[count].toCharArray();
                count ++;
            }
            count ++;
        }
        printMap(maps);
        int sum = 0;
        for(int i = 0; i< maps.length; i++){
            //System.out.println(maps[i].length);
            ArrayList<Integer> possible = new ArrayList<Integer>();
            for(int j = 1; j < maps[i][0].length; j++){
                possible.add(j);
            }
            System.out.println(possible);
            for(int j = 0; j < maps[i].length; j++){
                for(int k = possible.size()-1; k >= 0; k--){
                    for(int l = 0; l < min(possible.get(k),maps[i][j].length - possible.get(k)); l ++){
                        if(maps[i][j][possible.get(k) - l - 1] != maps[i][j][possible.get(k) + l]){
                            possible.remove(k);
                            break;
                        }
                    }
                }
            }
            if(possible.size() == 1){
                sum += possible.get(0);
                System.out.println(possible);
            }
        }
        for(int i = 0; i< maps.length; i++){
            //System.out.println(maps[i].length);
            ArrayList<Integer> possible = new ArrayList<Integer>();
            for(int j = 1; j <  maps[i].length; j++){
                possible.add(j);
            }
            System.out.println(possible);
            for(int j = 0; j < maps[i][0].length; j++){
                for(int k = possible.size()-1; k >= 0; k--){
                    for(int l = 0; l < min(possible.get(k),maps[i].length - possible.get(k)); l++){
                        if(maps[i][possible.get(k) - l - 1][j] != maps[i][possible.get(k) + l][j]){
                            possible.remove(k);
                            break;
                        }
                    }
                }
            }
            if(possible.size() == 1){
                sum += possible.get(0) * 100;
                System.out.println(possible);
            }
        }
        System.out.println("Answer: " + sum);
    }
}