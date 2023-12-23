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
        Map map = new Map(lines);
        for(int i = 0; i < 64; i++){
            map.move();
            System.out.println(map.pos.size());
        }
        System.out.println("Answer: " + map.pos.size());
    }
}
class Map{
    char[][] map;
    ArrayList<int[]> pos;
    int size;
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
        size = 1;
    }
    public void move(){
        ArrayList<int[]> temppos = new ArrayList<int[]>();
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
        try{
            if(map[i][j] == '.' && !has(i,j,temppos)){
                temppos.add(new int[]{i,j});
            }
        }catch(Exception e){}
    }
    public boolean has(int i, int j, ArrayList<int[]> temppos){
        for(int i1 = 0; i1 < temppos.size(); i1++){
            if(temppos.get(i1)[0] == i && temppos.get(i1)[1] == j){
                return true;
            }
        }
        return false;
    }
}