import java.io.*;
class a{
    public static void printArr(char[][] arr){
        for(char[] i:arr){
            for(char j:i){
                System.out.print(j);
            }
            System.out.println();
        }
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
        char[][] map = new char[lines.length][lines[0].length()];
        for(int i = 0; i < map.length; i++){
            map[i] = lines[i].toCharArray();
        }
        printArr(map);
        System.out.println();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == 'O'){
                    int count = 0;
                    try{
                        while(map[i - count - 1][j] == '.'){
                            map[i - count][j] = '.';
                            map[i - count - 1][j] = 'O';
                            count ++;
                        }
                    }catch(Exception e){}
                }
            }
        }
        printArr(map);
        int sum = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == 'O'){
                    sum += (map.length - i);
                }
            }
        }
        System.out.println(sum);
    }
}