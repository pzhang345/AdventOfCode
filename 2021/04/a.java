import java.io.*;
class a{
    public static boolean checkBoard(String[][] board){
        boolean col = true;
        boolean row = true;
        //System.out.println(board.length + " " + board[0].length);
        System.out.println("\n\n");
        for(int i = 0; i<board.length;i++){
            col = true;
            row = true;
            for(int j = 0; j<board[i].length; j++){
                //System.out.println("" + i + " " + j);
                System.out.print(board[i][j] + " ");
                if(!board[i][j].equals("a")){
                    row = false;
                }
                if(!board[j][i].equals("a")){
                    col = false;
                }
            }
            System.out.println();
            //System.out.println("" + row + " " + col);
            if(row||col){
                //System.out.println("a");
                return true;
            }
        }
        return false;
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
        String[] choose = lines[0].split(",");
        String[][][] boards = new String[(lines.length-1)/6][5][5];
        for(int i = 2;i<lines.length;i++){
            if(i%6 != 1){
                boards[(i-2)/6][(i-2)%6] = lines[i].replace("  "," ").trim().split(" ");
            }
        }
        String[][] board = new String[0][0];
        int num = 0;
        out:for(String i: choose){
            for(String[][] j: boards){
                for(String[] k: j){
                    for(int l = 0; l < k.length;l++){
                        if(k[l].equals(i)){
                            k[l] = "a";
                            if(checkBoard(j)){
                                board = j;
                                num = Integer.parseInt(i);
                                break out;
                            }
                        }
                    }
                }
            }
        }
        int sum = 0;
        for(int i = 0; i < board.length;i++){
            for(int j = 0;j < board[i].length;j++){
                if(!board[i][j].equals("a")){
                    sum += Integer.parseInt(board[i][j]);
                }
            }
        }
        for(String[] i:board){
            for(String j:i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println(sum + " " + num);
        System.out.println("Answer: " + (sum*num));
    }
}


