import java.io.*;
class b{
    public static boolean[][] fold(boolean[][] originalArr,String instruction){
        int pos = Integer.parseInt(instruction.split("=")[1]);
        boolean isX = instruction.contains("x");
        boolean[][] newArr;
        if(isX){
            int offset;
            if(pos >= originalArr[0].length/2){
                newArr = new boolean[originalArr.length][pos];
                offset = 0;
            }
            else{
                newArr = new boolean[originalArr.length][originalArr[0].length - pos - 1];
                offset = originalArr[0].length - 2*pos - 1;
            }
            for(int i = 0; i < newArr.length; i++){
                for(int j = 0; j < newArr[i].length;j++){
                    newArr[i][j] = false;
                }
            }
            for(int i = 0; i < originalArr.length; i++){
                for(int j = 0; j < originalArr[i].length;j++){
                    if(j < offset + pos){
                        newArr[i][j] = originalArr[i][j + offset];
                    }
                    else if(j == pos + offset){}
                    else{
                        if(originalArr[i][j]){
                            newArr[i][originalArr[0].length-j-1] = true;
                        }
                    }
                }
            }
        }
        else{
            int offset;
            if(pos >= originalArr.length/2){
                newArr = new boolean[pos][originalArr[0].length];
                offset = 0;
            }
            else{
                newArr = new boolean[originalArr.length - pos - 1][originalArr[0].length];
                offset = originalArr.length - 2*pos - 1;
            }
            //System.out.println(offset);
            System.out.println("" + newArr.length + " " + newArr[0].length);
            for(int i = 0; i < newArr.length; i++){
                for(int j = 0; j < newArr[i].length;j++){
                    newArr[i][j] = false;
                }
            }
            for(int i = 0; i < originalArr.length; i++){
                for(int j = 0; j < originalArr[i].length;j++){
                    if(i < offset + pos){
                        newArr[i][j] = originalArr[i + offset][j];
                    }
                    else if(i == pos + offset){}
                    else{
                        if(originalArr[i][j]){
                            newArr[originalArr.length - 1 - i][j] = true;
                        }
                    }
                }
            }
        }
        return newArr;
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
        int maxx = Integer.parseInt(lines[0].split(",")[0]);
        int minx = Integer.parseInt(lines[0].split(",")[0]);
        int maxy = Integer.parseInt(lines[0].split(",")[1]);
        int miny = Integer.parseInt(lines[0].split(",")[1]);
        int instructions = 0;
        for(String i:lines){
            instructions ++;
            if(i.equals("")){
                break;
            }
            int x = Integer.parseInt(i.split(",")[0]);
            int y = Integer.parseInt(i.split(",")[1]);
            if(maxx < x){
                maxx = x;
            }
            if(minx > x){
                minx = x;
            }
            if(maxy < y){
                maxy = y;
            }
            if(miny > y){
                miny = y;
            }
        }
        System.out.println(instructions);
        boolean[][] points = new boolean[maxy - miny + 1][maxx - minx + 1];
        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < points[i].length;j++){
                points[i][j] = false;
            }
        }
        for(String i:lines){
            if(i.equals("")){
                break;
            }
            int x = Integer.parseInt(i.split(",")[0]);
            int y = Integer.parseInt(i.split(",")[1]);
            points[y][x] = true;
        }
        for(int i = instructions; i<lines.length; i++){
            points = fold(points,lines[i]);
        }
        System.out.println("Answer:");
        for(boolean[] i:points){
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


