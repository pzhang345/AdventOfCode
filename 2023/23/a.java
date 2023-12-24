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
        System.out.println("Answer: " + map.move());
    }
}
class Path{
    int i;
    int j;
    boolean[][] hasGone;
    int count;
    public Path(boolean[][] h, int newi, int newj, int count){
        hasGone = copy(h);
        i = newi;
        j = newj;
        hasGone[i][j] = true;
        count ++;
    }
    public Path(Path p, int newi, int newj){
        hasGone = copy(p.hasGone);
        i = newi;
        j = newj;
        hasGone[i][j] = true;
        count = p.count + 1;
    }
    public boolean[][] copy(boolean[][] arr){
        boolean[][] temp = new boolean[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }
}
class Map{
    char[][] map;
    public ArrayList<Path> q;
    public Map(String[] lines){
        map = new char[lines.length][];
        q = new ArrayList<Path>();
        for(int i = 0; i < map.length; i++){
            map[i] = lines[i].toCharArray();
        }
    }
    public int move(){
        int max = -1;
        q.add(new Path(new boolean[map.length][map[0].length],0,1, 0));
        while(q.size() != 0){
            Path p = q.get(0);
            if(p.i == map.length - 1 && p.j == map[0].length - 2){
                if(max == -1 || p.count > max){
                    max = p.count;
                }
            }
            if(map[p.i][p.j] == '.'){
                addPath(p.i - 1,p.j,p);
                addPath(p.i + 1,p.j,p);
                addPath(p.i,p.j - 1,p);
                addPath(p.i,p.j + 1,p);
            }
            else if(map[p.i][p.j] == '^'){
                addPath(p.i - 1,p.j,p);
            }
            else if(map[p.i][p.j] == 'v'){
                addPath(p.i + 1,p.j,p);
            }
            else if(map[p.i][p.j] == '<'){
                addPath(p.i,p.j - 1,p);
            }
            else if(map[p.i][p.j] == '>'){
                addPath(p.i,p.j + 1,p);
            }
            q.remove(0);
        }
        return max;
    }
    public void addPath(int i, int j,Path path){
        try{
            if(map[i][j] != '#' && !path.hasGone[i][j]){
                q.add(new Path(path,i,j));
            }
        }catch(Exception e){}
    }
}