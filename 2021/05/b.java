import java.io.*;
class b{
    public static int[] parseIntArr(String[] arr){
        int[] intArr = new int[arr.length];
        for(int i = 0; i<arr.length;i++){
            intArr[i] = Integer.parseInt(arr[i]);
        }
        return intArr;
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
        int[][][] points = new int[lines.length][2][2];
        int maxx = Integer.parseInt(lines[0].split(" -> ")[0].split(",")[1]);
        int minx = Integer.parseInt(lines[0].split(" -> ")[0].split(",")[1]);
        int maxy = Integer.parseInt(lines[0].split(" -> ")[0].split(",")[0]);
        int miny = Integer.parseInt(lines[0].split(" -> ")[0].split(",")[0]);
        for(int i = 0;i<lines.length;i++){
            String[] strarr = lines[i].split(" -> ");
            for(int j = 0; j<points[j].length;j++){
                points[i][j] = parseIntArr(strarr[j].split(","));
                int x = points[i][j][1];
                int y = points[i][j][0];
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
                System.out.println("" + x + " " + y);
            }
        }
        //System.out.println("" + minx + " " + maxx + " " + miny + " " + maxy);
        int[][] grid = new int[maxy - miny + 1][maxx - minx + 1];
        for(int[][] i: points){
            if(i[0][0] == i[1][0]){
                int big = i[1][1];
                int small = i[0][1];
                if(i[0][1] > i[1][1]){
                    big = i[0][1];
                    small = i[1][1];
                }
                for(int j = small;j <= big;j++){
                    grid[j - miny][i[0][0] - minx] ++;
                }
            }
            else if(i[0][1] == i[1][1]){
                int big = i[1][0];
                int small = i[0][0];
                if(i[0][0] > i[1][0]){
                    big = i[0][0];
                    small = i[1][0];
                }
                for(int j = small;j <= big;j++){
                    grid[i[0][1] - miny][j - minx] ++;
                }
            }
            else{
                int x = 0;
                int y = 0;
                if(i[0][1] < i[1][1]){
                    y = 1;
                }
                else{
                    y = -1;
                }
                if(i[0][0] < i[1][0]){
                    x = 1;
                }
                else{
                    x = -1;
                }
                for(int j = 0;j <= Math.abs(i[0][0] - i[1][0]);j++){
                    grid[(j*y) + i[0][1] +  - miny][(j*x) + i[0][0] - minx] ++;
                }
            }
        }
        int count = 0;
        for(int[] i: grid){
            for(int j:i){
                /*
                if(j == 0){
                    System.out.print(".");
                }
                else{
                    System.out.print(j);
                }
                */
                if(j >= 2){
                    count ++;
                }
            }
            //System.out.println();
        }
        System.out.println("Answer: " + count);
    }
}


