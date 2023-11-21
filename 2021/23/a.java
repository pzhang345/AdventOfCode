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
        Map map = new Map(lines,0);
        map.move();
        map.printMap();
        map.printPos();
        System.out.println("Answer: " + Map.lowcost);
        //System.out.println(map.checkDone());
        
        
    }
}
class Map{
    String[][] map;
    int[][] pos;
    int cost;
    String [][][] path;
    public static int[] holes = {3,5,7,9};
    public static char[] chars = {'A','B','C','D'};
    public static int[] costPerMove = {1,10,100,1000};
    public static int lowcost = -1;
    public static Map lowMap = null;
    public static int count = 0;
    public Map(String[] str,int c){
        map = new String[str.length][str[0].length()];
        pos = new int[8][2];
        cost = c;
        path = new String[16][map.length][map[0].length];
        path[0] = map;
        for(int i = 0; i < pos.length; i++){
            for(int j = 0; j < pos[i].length; j++){
                pos[i][j] = -1;
            }
        }
        for(int i = 0; i < str.length; i++){
            for(int j = 0; j < str[0].length(); j++){
                if(str[i].length() > j){
                    map[i][j] = "" + str[i].charAt(j);
                    if(map[i][j].equals(" ")){
                        map[i][j] = "#";
                    }
                    if(!map[i][j].equals("#") && !map[i][j].equals(".")){
                        for(int k = 0; k < chars.length; k++){
                            if(map[i][j].charAt(0) == chars[k]){
                                if(pos[2*k][0] != -1){
                                    pos[2*k + 1] = new int[]{i,j};
                                }
                                else{
                                    pos[2*k] = new int[]{i,j};
                                }
                                map[i][j] += 2;
                            }
                        }
                        if(map[i - 1][j].equals(".")){
                            map[i-1][j] = ":";
                        }
                    }
                }
                else{
                    map[i][j] = "#";
                }
            }
        }
        if(checkDone()){
            if(cost < lowcost){
                lowcost = cost;
            }
        }
        else if(canFinish()){
             //move();
        }
    }
    public Map(String[][] m, int[][] p, int c){
        map = m;
        pos = p;
        cost = c;
        if(checkDone()){
            if(lowcost == -1 || cost < lowcost){
                lowcost = cost;
            }
        }
        else if(canFinish()){
             move();
        }
    }
    public Map(String[][] m, int[][] p, String [][][] pa, int c, int startx, int starty, int x, int y){
        m[y][x] = newMapValue(m[starty][startx]);
        //System.out.println(newMapValue(m[starty][startx]));
        m[starty][startx] = ".";
        int index = findIndex(p,starty,startx);
        p[index] = new int[]{y,x};
        map = m;
        pos = p;
        path = pa;
        cost = c;
        for(int i = 0; i < path.length; i++){
            if(path[i][0][0] == null){
                path[i] = map;
                break;
            }
        }
        //System.out.println(cost);
        if(checkDone()){
            
            //System.out.println("a");
            if(lowcost == -1 || cost < lowcost){
                lowcost = cost;
                lowMap = this;
                System.out.println(lowcost);
                printPath();
            }
        }
        else if(lowcost == -1 || minFinish() < lowcost){
            move();
        }
        //else{
            // if(map[3][5].equals("B0")){
            //     printMap();
            //     System.out.println();
            // }
        //}
    }
    public int findIndex(int[][] p, int num1, int num2){
        for(int i = 0; i < p.length; i++){
            if(num1 == p[i][0] && num2 == p[i][1]){
                return i;
            }
        }
        return -1;
    }
    public void move(){
        for(int i = 0; i < pos.length; i ++){
            int starty = pos[i][0];
            int startx = pos[i][1];
            int y = starty;
            int x = startx;
            int moveCost = costPerMove[i/2];
            int tempCost = cost;
            int startCost = cost;
            if(map[y][x].charAt(1) == '2'){
                while(map[y-1][x].equals(".") || map[y-1][x].equals(":")){
                    y--;
                    startCost += moveCost;
                }
                tempCost = startCost;
                while(map[y][x-1].equals(".") || map[y][x-1].equals(":")){
                    x--;
                    tempCost += moveCost;
                    if(map[y][x].equals(".")){
                        new Map(copyMap(),copyPos(),copyPath(),tempCost,startx,starty,x,y);
                    }
                }
                tempCost = startCost;
                x = startx;
                while(map[y][x+1].equals(".") || map[y][x+1].equals(":")){
                    x++;
                    tempCost += moveCost;
                    if(map[y][x].equals(".")){
                        new Map(copyMap(),copyPos(),copyPath(),tempCost,startx,starty,x,y);
                    }
                }
            }
            else if(map[y][x].charAt(1) == '1'){
                if(x < holes[i/2]){
                    for(int j = x + 1; j <= holes[i/2]; j++){
                        if(map[y][j].equals(":") || map[y][j].equals(".")){
                            tempCost += moveCost;
                            x = j;
                        }
                        else{
                            break;
                        }
                    }
                }
                else if(x > holes[i/2]){
                    for(int j = x - 1; j >= holes[i/2]; j--){
                        if(map[y][j].equals(":") || map[y][j].equals(".")){
                            tempCost += moveCost;
                            x = j;
                        }
                        else{
                            break;
                        }
                    }
                }
                if(x == holes[i/2]){
                    while(map[y+1][x].equals(".")){
                        //System.out.println(y);
                        y++;
                        tempCost += moveCost;
                    }
                    if(i/2 == 1 && !map[y+1][x].equals("#") && !map[y+1][x].equals("A2") && !map[y+1][x].equals("B2")){
                        //System.out.println(map[y+1][x].charAt(0));
                    }
                    //System.out.println(y);
                    if((map[y+1][x].equals("#") || map[y+1][x].charAt(0) == chars[i/2]) &&(y == 2 || y == 3)){
                        //System.out.println("a");
                        //System.out.println("" + x + " " + y);
                        //System.out.println(chars[i/2]);
                        new Map(copyMap(),copyPos(),copyPath(),tempCost,startx,starty,x,y);
                    }
                }
            }
        }
    }
    public String newMapValue(String value){
        return "" + value.charAt(0) + (Integer.parseInt(value.substring(1)) - 1);
    }
    public boolean checkDone(){
        for(int i = 0; i < holes.length; i++){
            if(map[2][holes[i]].charAt(0) != chars[i] || map[3][holes[i]].charAt(0) != chars[i]){
                return false;
            }
        }
        return true;
    }
    public boolean canFinish(){
        for(int i = 0; i < pos.length; i++){
            //System.out.println(map[i[0]][i[1]]);
            if(map[pos[i][0]][pos[i][1]].charAt(1) == '0'){
                if(map[pos[i][0]][pos[i][1]].charAt(0) == chars[i/2] && ((pos[i][0] != holes[i/2]))){
                    return false;
                }
            }
        }
        return true;
    }
    public int minFinish(){
        int count = cost;
        for(int i = 0; i < pos.length; i++){
            if(pos[i][0] != 3){           
                count += (costPerMove[i/2])*(Math.abs(holes[i/2] - pos[i][1]) + (2 - pos[i][0]));
            }
            else{
                count += (costPerMove[i/2])*Math.abs(holes[i/2] - pos[i][1]);
            }
        }
        return count;
    }
    public String[][] copyMap(){
        String[][] mapCopy = new String[map.length][map[0].length];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length;j++){
                mapCopy[i][j] = map[i][j];
            }
        }
        return mapCopy;
    }
    public int[][] copyPos(){
        int[][] posCopy = new int[pos.length][pos[0].length];
        for(int i = 0; i < pos.length; i++){
            for(int j = 0; j < pos[i].length;j++){
                posCopy[i][j] = pos[i][j];
            }
        }
        return posCopy;
    }
    public String[][][] copyPath(){
        String[][][] pathCopy = new String[path.length][path[0].length][path[0][0].length];
        for(int i = 0; i < path.length; i++){
            for(int j = 0; j < path[i].length;j++){
                for(int k = 0; k < path[i][j].length; k++){
                    pathCopy[i][j][k] = path[i][j][k];
                }
            }
        }
        return pathCopy;
    }
    public void printMap(){
        for(String[] i: map){
            for(String j:i){
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }
    public void printPos(){
        for(int[] i: pos){
            for(int j: i){
                System.out.print("" + j + " ");
            }
            System.out.println();
        }
    }
    public void printPath(){
        for(String[][] i: path){
            for(String[] j: i){
                for(String k: j){
                    if(k == null){
                       return;
                    }
                    System.out.print(k + "\t");
                    
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}


