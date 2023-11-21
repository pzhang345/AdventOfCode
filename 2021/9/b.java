import java.io.*;
import java.util.*;
class a{
    public static int spread(int[] pos,int[][] map){
        int[][] arr = new int[map.length][map[0].length];
        int count = 1;
        boolean doessomething = true;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                arr[i][j] = 0;
                if(i == pos[0] && j == pos[1]){
                    arr[i][j] = 1;
                }
            }
        }
        while(doessomething){
            doessomething = false;
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[i].length; j++){
                    if(arr[i][j] == 1){
                        if(i != 0 && map[i][j] < map[i-1][j] && arr[i-1][j] == 0 && map[i-1][j] != 9){
                            arr[i-1][j] = 1;
                            count ++;
                            doessomething = true;
                        }
                        if(i != map.length - 1 && map[i][j] < map[i+1][j] && arr[i+1][j] == 0 && map[i+1][j] != 9){
                            arr[i+1][j] = 1;
                            count ++;
                            doessomething = true;
                        }
                        if(j != 0 && map[i][j] < map[i][j-1] && arr[i][j-1] == 0 && map[i][j-1] != 9){
                            arr[i][j-1] = 1;
                            count ++;
                            doessomething = true;
                        }
                        if(j != map[0].length - 1 && map[i][j] < map[i][j+1] && arr[i][j+1] == 0 && map[i][j+1] != 9){
                            arr[i][j+1] = 1;
                            count ++;
                            doessomething = true;
                        }
                        arr[i][j] = 2;
                    }
                    
                }
            }
        }
        /*
        for(int[] i:arr){
            for(int j:i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        */
        return count;
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
        int[][] map = new int[lines.length][lines[0].length()];
        ArrayList<int[]> pos = new ArrayList<int[]>();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                map[i][j] = Integer.parseInt("" + lines[i].charAt(j));
            }
        }
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if((i == 0 || map[i][j] < map[i-1][j]) && (i == map.length - 1 || map[i][j] < map[i+1][j])
                && (j == 0 || map[i][j] < map[i][j-1]) && (j == map[0].length - 1|| map[i][j] < map[i][j+1])){
                    int[] p = {i,j};
                    pos.add(p);
                }
            }
        }
        ArrayList<Integer> sizes = new ArrayList<Integer>();
        for(int[] i:pos){
            //System.out.println(spread(i,map));
            sizes.add(spread(i, map));
        }
        sizes.sort(Comparator.reverseOrder());
        System.out.println("Answer: " + (sizes.get(0) * sizes.get(1) * sizes.get(2)));
    }
}


