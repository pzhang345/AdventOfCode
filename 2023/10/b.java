import java.io.*;
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
        map.move();
        map.printMap();
        System.out.println("Answer: " + map.count());
    }
}
class Map{
    char[][] map;
    int ia;
    int ja;
    int[][] room;
    public Map(String[] arr){
        map = new char[arr.length][arr[0].length()];
        room = new int[3*map.length + 2][3*map[0].length + 2];
        System.out.println(room[0][0]);
        for(int i = 0; i < arr.length; i ++){
            for(int j = 0; j < arr[0].length(); j++){
                if(arr[i].charAt(j) == 'S'){
                    ia = i;
                    ja = j;
                }
                map[i][j] = arr[i].charAt(j);
            }
        }
        boolean above = getDirections(map[ia - 1][ja])[3];
        boolean below = getDirections(map[ia + 1][ja])[2];
        boolean left = getDirections(map[ia][ja - 1])[1];
        boolean right = getDirections(map[ia][ja + 1])[0];
        System.out.println(above + " " + below + " " + left + " " + right);
        if(above && below){
            map[ia][ja] = '|';
        }
        else if(left && right){
            map[ia][ja] = '-';
        }
        else if(above && right){
            map[ia][ja] = 'L';
        }
        else if(above && left){
            map[ia][ja] = 'J';
        }
        else if(below && right){
            map[ia][ja] = 'F';
        }
        else if(below && left){
            map[ia][ja] = '7';
        }
    }
    public void move(){
        int pi = -1;
        int pj = -1;
        boolean[] dir = getDirections(map[ia][ja]);
        while(map[ia][ja] != '#'){
            //System.out.println("a");
            dir = getDirections(map[ia][ja]);
            draw(map[ia][ja],ia,ja);
            map[ia][ja] = '#';
            //System.out.println(ia + " " + ja + " " + pi + " " + pj);
            if(dir[0] && ja - 1 != pj){
                pj = ja;
                pi = ia;
                ja --;
            }
            else if(dir[1] && ja + 1 != pj){
                pj = ja;
                pi = ia;
                ja ++;
            }
            else if(dir[2] && ia - 1 != pi){
                pj = ja;
                pi = ia;
                ia --;
            }
            else if(dir[3] && ia + 1 != pi){
                pj = ja;
                pi = ia;
                ia ++;
            }
        }
        printMap();
        System.out.println();
        while(fillBool()){}
    }
    public void draw(char c, int i, int j){
        //System.out.println(c);
        int[][] arr = new int[0][0];
        if(c == '|'){
            arr = new int[][]{{0,2,0},
                                {0,2,0},
                                {0,2,0}};
        }
        else if(c == '-'){
            arr = new int[][]{{0,0,0},
                              {2,2,2},
                              {0,0,0}};
        }
        else if(c == 'L'){
            arr = new int[][]{{0,2,0},
                              {0,2,2},
                              {0,0,0}};
        }
        else if(c == 'J'){
            arr = new int[][]{{0,2,0},
                              {2,2,0},
                              {0,0,0}};
        }
        else if(c == 'F'){
            arr = new int[][]{{0,0,0},
                              {0,2,2},
                              {0,2,0}};
        }
        else if(c == '7'){
            arr = new int[][]{{0,0,0},
                              {2,2,0},
                              {0,2,0}};
        }
        for(int i1 = 0; i1 < arr.length; i1++){
            for(int j1 = 0; j1 < arr[0].length; j1++){
                room[3 * i + i1 + 1][3 * j + j1 + 1] = arr[i1][j1];
            }
        }
        //printMap();
    }
    public boolean checkSides(int i, int j){
        try{
            if(room[i + 1][j] == 1){
                return true;
            }
        }catch(Exception e){}
        try{
            if(room[i - 1][j] == 1){
                return true;
            }
        }catch(Exception e){}
        try{
            if(room[i][j + 1] == 1){
                return true;
            }
        }catch(Exception e){}
        try{
            if(room[i][j - 1] == 1){
                return true;
            }
        }catch(Exception e){}
        return false;
    }
    public boolean fillBool(){
        boolean hasChanged = false;
        room[0][0] = 1;
        for(int i = 0; i < room.length; i++){
            for(int j = 0; j < room[i].length; j++){
                if(room[i][j] == 0 && checkSides(i, j)){
                    room[i][j] = 1;
                    hasChanged = true;
                }
            }
        }
        return hasChanged;
    }
    public boolean[] getDirections(char pipeShape){
        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;
        if(pipeShape == '|'){
            up = true;
            down = true;
        }
        else if(pipeShape == '-'){
            left = true;
            right = true;
        }
        else if(pipeShape == 'L'){
            up = true;
            right = true;
        }
        else if(pipeShape == 'J'){
            up = true;
            left = true;
        }
        else if(pipeShape == '7'){
            down = true;
            left = true;
        }
        else if(pipeShape == 'F'){
            down = true;
            right = true;
        }
        //System.out.println(up + " " + down + " " + left + " " + right);
        return new boolean[]{left,right,up,down};
    }
    public int count(){
        int count = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                boolean has = false;
                for(int x = 0; x < 3; x++){
                    for(int y = 0; y < 3; y++){
                        if(room[3 * i + x + 1][3 * j + y + 1] != 0){
                            has = true;
                        }
                    }
                }
                if(!has){
                    count ++;
                }
            }
        }
        return count;
    }
    public void printMap(){
        for(int[] i:room){
            for(int j:i){
                if(j == 0){
                    System.out.print(".");
                }
                else if(j == 1){
                    System.out.print("A");
                }
                else if(j == 2){
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }
}