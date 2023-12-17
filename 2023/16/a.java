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
        map.move(0,0,2);
        int total = 0;
        for(Dirs[] i:map.hasVisited){
            for(Dirs j:i){
                if(j.hasVisited){
                    System.out.print("#");
                    total ++;
                }
                else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println("Answer: " + total);
    }
}
class Dirs{
    boolean up;
    boolean down;
    boolean left;
    boolean right;
    boolean hasVisited;
    public Dirs(){
        up = false;
        down = false;
        left = false;
        right = false;
        hasVisited = false;
    }
}
class Map{
    char[][] space;
    Dirs[][] hasVisited;
    
    public Map(String[] arr){
        space = new char[arr.length][];
        for(int i = 0; i < space.length; i++){
            space[i] = arr[i].toCharArray();
        }
        hasVisited = new Dirs[space.length][space[0].length];
        for(int i = 0; i < hasVisited.length; i++){
            for(int j = 0; j < hasVisited[i].length; j++){
                hasVisited[i][j] = new Dirs();
            }
        }
    }
    public void move(int i, int j, int dir){
        if(0 <= i && i < space.length && 0 <= j && j < space[i].length){
            hasVisited[i][j].hasVisited = true;
            //printArr();
            //System.out.println();
            if(dir == -2 && !hasVisited[i][j].up){
                hasVisited[i][j].up = true;
                if(space[i][j] == '\\'){
                    move(i, j - 1, 1);
                }
                else if(space[i][j] == '/'){
                    move(i, j + 1, 2);
                }
                else if(space[i][j] == '-'){
                    move(i, j - 1, 1);
                    move(i, j + 1, 2);
                }
                else{
                    move(i - 1, j, dir);
                }
            }
            else if(dir == -1 && !hasVisited[i][j].down){
                hasVisited[i][j].down = true;
                if(space[i][j] == '\\'){
                    move(i, j + 1, 2);
                }
                else if(space[i][j] == '/'){
                    move(i, j - 1, 1);
                }
                else if(space[i][j] == '-'){
                    move(i, j - 1, 1);
                    move(i, j + 1, 2);
                }
                else{
                    move(i + 1, j, dir);
                }
            }
            else if(dir == 1 && !hasVisited[i][j].left){
                hasVisited[i][j].left = true;
                if(space[i][j] == '\\'){
                    move(i - 1, j, -2);
                }
                else if(space[i][j] == '/'){
                    move(i + 1, j, -1);
                }
                else if(space[i][j] == '|'){
                    move(i - 1, j, -2);
                    move(i + 1, j, -1);
                }
                else{
                    move(i, j - 1, dir);
                }
            }
            else if(dir == 2 && !hasVisited[i][j].right){
                hasVisited[i][j].right = true;
                if(space[i][j] == '\\'){
                    move(i + 1, j, -1);
                }
                else if(space[i][j] == '/'){
                    move(i - 1, j, -2);
                }
                else if(space[i][j] == '|'){
                    move(i - 1, j, -2);
                    move(i + 1, j, -1);
                }
                else{
                    move(i, j + 1, dir);
                }
            }
        }
    }
    public void printArr(){
         for(Dirs[] i:hasVisited){
            for(Dirs j:i){
                if(j.hasVisited){
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