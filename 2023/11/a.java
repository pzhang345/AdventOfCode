import java.io.*;
import java.util.*;
class a{
    public static int findSum(int[][] arr){
        int total = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = i + 1; j < arr.length; j++){
                int distance = Math.abs(arr[i][0] - arr[j][0]) + Math.abs(arr[i][1] - arr[j][1]);
                total += distance;
            }
        }
        return total;
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
        System.out.println("Answer: " + findSum(new Map(lines).pos));
    }
}
class Map{
    boolean[][] map;
    int[][] pos;
    public Map(String[] arr){
        ArrayList<Integer> imap = new ArrayList<Integer>();
        ArrayList<Integer> jmap = new ArrayList<Integer>();
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            boolean has = false;
            for(int j = 0; j < arr[i].length(); j++){
                if(arr[i].charAt(j) == '#'){
                    has = true;
                    count ++;
                }
            }
            if(!has){
                imap.add(i);
            }
        }
        pos = new int[count][2];
        for(int j = 0; j < arr[0].length(); j++){
            boolean has = false;
            for(int i = 0; i < arr.length; i++){
                if(arr[i].charAt(j) == '#'){
                    has = true;
                    break;
                }
            }
            if(!has){
                jmap.add(j);
            }
        }
        System.out.println("" + imap + " " + jmap);
        map = new boolean[arr.length + imap.size()][arr[0].length() + jmap.size()];
        int iadd = 0;
        int index = 0;
        for(int i = 0; i < arr.length; i++){
            int jadd = 0;    
            for(int j = 0; j < arr[0].length(); j++){
                if(arr[i].charAt(j ) == '#'){
                    map[i + iadd][j + jadd] = true;
                    pos[index][0] = i + iadd;
                    pos[index][1] = j + jadd;
                    index ++;
                }
                if(jmap.contains(j)){
                    jadd ++;
                }
            }
            if(imap.contains(i)){
                iadd ++;
            }
        }
        printMap();
        for(int i = 0; i < pos.length; i++){
            System.out.println(pos[i][0] + " " + pos[i][1]);
        }
    }
    public void printMap(){
        for(boolean[] i: map){
            for(boolean j:i){
                if(j){
                    System.out.print("#");
                }
                else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}