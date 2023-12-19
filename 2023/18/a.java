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
        map.fill();
        map.printArr();
        int count = 0;
        for(int[] i:map.dig){
            for(int j: i){
                if(j == 1 || j == 0){
                    count ++;
                }
            }
        }
        System.out.println("Answer: " + count);
    }
}
class Map{
    int[][] dig;
    int ipos;
    int jpos;
    public Map(String[] lines){
        dig = new int[1][1];
        dig[0][0] = 1;
        ipos = 0;
        jpos = 0;
        for(String i:lines){
            String[] dir = i.split(" ");
            draw(dir[0].charAt(0),Integer.parseInt(dir[1]));
        }
        int[][] temp = new int[dig.length + 2][dig[0].length + 2];
        for(int i = 0; i < dig.length; i++){
            for(int j = 0; j < dig[i].length; j++){
                temp[i + 1][j + 1] = dig[i][j];
            }
        }
        dig = temp;
        dig[0][0] = 2;
    }
    public void draw(char dir, int num){
        if(dir == 'R'){
            int joffset = (num + jpos - dig[0].length + 1);
            if(joffset > 0){
                int[][] temp = new int[dig.length][dig[0].length + joffset];
                for(int i = 0; i < dig.length; i++){
                    for(int j = 0; j < dig[0].length; j++){
                        temp[i][j] = dig[i][j];
                    }
                }
                dig = temp;
            }
            for(int j = 1; j <= num; j++){
                jpos ++;
                dig[ipos][jpos] = 1;
            }
        }
        else if(dir == 'L'){
            int joffset = num - jpos;
            if(joffset > 0){
                int[][] temp = new int[dig.length][dig[0].length + joffset];
                for(int i = 0; i < dig.length; i++){
                    for(int j = 0; j < dig[0].length; j++){
                        temp[i][j  + joffset] = dig[i][j];
                    }
                }
                jpos += joffset;
                dig = temp;
            }
            for(int j = 1; j <= num; j++){
                jpos --;
                dig[ipos][jpos] = 1;
            }
        }
        if(dir == 'D'){
            int ioffset = (num + ipos - dig.length + 1);
            if(ioffset > 0){
                int[][] temp = new int[dig.length + ioffset][dig[0].length];
                for(int i = 0; i < dig.length; i++){
                    for(int j = 0; j < dig[0].length; j++){
                        temp[i][j] = dig[i][j];
                    }
                }
                dig = temp;
            }
            for(int i = 1; i <= num; i++){
                ipos ++;
                dig[ipos][jpos] = 1;
            }
        }
        else if(dir == 'U'){
            int ioffset = num - ipos;
            if(ioffset > 0){
                int[][] temp = new int[dig.length + ioffset][dig[0].length];
                for(int i = 0; i < dig.length; i++){
                    for(int j = 0; j < dig[0].length; j++){
                        temp[i + ioffset][j] = dig[i][j];
                    }
                }
                ipos += ioffset;
                dig = temp;
            }
            for(int i = 1; i <= num; i++){
                ipos --;
                dig[ipos][jpos] = 1;
            }
        }
    }
    public void printArr(){
        for(int[] i:dig){
            for(int j:i){
                if(j == 1 || j == 0){
                    System.out.print(".");
                }
                else{
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }
    public void fill(){
        boolean hasChanged = true;
        while(hasChanged){
            hasChanged = false;
            for(int i = 0; i < dig.length; i++){
                for(int j = 0; j < dig[i].length; j++){
                    if(checkSides(i, j) && dig[i][j] == 0){
                        dig[i][j] = 2;
                        hasChanged = true;
                    }
                }
            }
        }
    }
    public boolean checkSides(int i, int j){
        try{
            if(dig[i + 1][j] == 2){
                return true;
            }
        }catch(Exception e){}
        try{
            if(dig[i - 1][j] == 2){
                return true;
            }
        }catch(Exception e){}
        try{
            if(dig[i][j + 1] == 2){
                return true;
            }
        }catch(Exception e){}
        try{
            if(dig[i][j - 1] == 2){
                return true;
            }
        }catch(Exception e){}
        return false;
    }
}