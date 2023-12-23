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
        Map map = new Map(lines);
        ArrayList<Integer> store = new ArrayList<Integer>();
        int iterations = 26501365;
        for(int i = 1; i <= iterations%(map.map.length * 2) + 4 * map.map.length; i++){
            map.move();
            if(i%(map.map.length * 2) == iterations%(map.map.length * 2)){
                store.add(map.pos.size());
            }
            System.out.println(i);
        }
        System.out.println(map.pos.size());
        System.out.println(store);
        int[] diff = new int[store.size() - 1]; 
        for(int i = 0; i < store.size() - 1; i++){
            diff[i] = store.get(i + 1) - store.get(i);
            System.out.println(diff[i]);
        }
        long constant = diff[0];
        long fact = diff[1] - diff[0];
        System.out.println(constant + " " + fact);
        long answer = store.get(0);
        int count = iterations%(map.map.length * 2);
        long index = 0;
        while(count != iterations){
            answer += constant + index * fact;
            count += map.map.length * 2;
            //System.out.println(answer);
            index ++;
        }
        System.out.println("Answer: " + answer);
    }
}
class Map{
    char[][] map;
    ArrayList<int[]> pos;
    boolean[][] hasDone;
    public Map(String[] lines){
        map = new char[lines.length][lines[0].length()];
        pos = new ArrayList<int[]>();
        for(int i = 0; i < lines.length; i++){
            if(lines[i].contains("S")){
                pos.add(new int[]{i,lines[i].indexOf("S")});
                lines[i] = lines[i].replace("S",".");
            }
            map[i] = lines[i].toCharArray();
        }
        hasDone = new boolean[10000][10000];
    }
    public void move(){
        ArrayList<int[]> temppos = new ArrayList<int[]>();
        hasDone = new boolean[10000][10000];
        for(int i = 0; i < pos.size(); i++){
            int[] arr = pos.get(i);
            addToPos(arr[0] - 1,arr[1],temppos);
            addToPos(arr[0] + 1,arr[1],temppos);
            addToPos(arr[0],arr[1] - 1,temppos);
            addToPos(arr[0],arr[1] + 1,temppos);
        }
        pos = temppos;
    }
    public void addToPos(int i, int j,ArrayList<int[]> temppos){
        int tempi = i - (i/map.length) * map.length;
        int tempj = j - (j/map[0].length) * map[0].length;
        if(tempi < 0){
            tempi += map.length;
        }
        if(tempj < 0){
            tempj += map[0].length;
        }
        if(map[tempi][tempj] == '.' && !hasDone[i + 5000][j + 5000]){
            temppos.add(new int[]{i,j});
            hasDone[i + 5000][j + 5000] = true;
        }
    }
}