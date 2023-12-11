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
        System.out.println("Answer: " + new Map(lines).move());
    }
}
class Map{
    char[][] map;
    int ia;
    int ja;
    int istart;
    int jstart;
    int count;
    public Map(String[] arr){
        map = new char[arr.length][arr[0].length()];
        count = 0;
        for(int i = 0; i < arr.length; i ++){
            for(int j = 0; j < arr[0].length(); j++){
                if(arr[i].charAt(j) == 'S'){
                    ia = i;
                    ja = j;
                    istart = i;
                    jstart = j;
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
    public int move(){
        int pi = -1;
        int pj = -1;
        boolean[] dir = getDirections(map[ia][ja]);
        while(count == 0 || (ia != istart || ja != jstart)){
            dir = getDirections(map[ia][ja]);
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
            count ++;
        }
        return (int)(Math.ceil(count/2.0));
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
    public void printMap(){
        for(char[] i:map){
            for(char j:i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}