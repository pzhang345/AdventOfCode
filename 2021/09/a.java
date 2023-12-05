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
        int[][] map = new int[lines.length][lines[0].length()];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                map[i][j] = Integer.parseInt("" + lines[i].charAt(j));
            }
        }
        int sum = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if((i == 0 || map[i][j] < map[i-1][j]) && (i == map.length - 1 || map[i][j] < map[i+1][j])
                && (j == 0 || map[i][j] < map[i][j-1]) && (j == map[0].length - 1|| map[i][j] < map[i][j+1])){
                    sum += 1 + map[i][j];
                }
            }
        }
        System.out.println("Answer: " + sum);
    }
}


