import java.io.*;
class b{
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
        long p1Score = 0;
        int p1Pos = Integer.parseInt("" + lines[0].charAt(lines[0].length() - 1));
        long p2Score = 0;
        int p2Pos = Integer.parseInt("" + lines[1].charAt(lines[1].length() - 1));
        long[][][][][] scores = new long[21][21][11][11][2];
        scores[0][0][p1Pos][p2Pos][0] = 1;
        long num;
        for(int i = 0; i < scores.length; i++){
            for(int j = 0; j < scores[i].length;j++){
                for(int k = 0; k < scores[i][j].length;k++){
                    for(int l = 0; l < scores[i][j][k].length; l++){
                        for(int m = 0; m < scores[i][j][k][l].length; m++){
                            if(scores[i][j][k][l][m] != 0){
                                num = scores[i][j][k][l][m];
                                scores[i][j][k][l][m] = 0;
                                for(int n = 1; n <= 3;n++){
                                    for(int o = 1; o <= 3; o++){
                                        for(int p = 1; p <= 3; p++){
                                            if(m == 0){
                                                if((i + (k + n + o + p - 1)%10 + 1 >= 21)){
                                                    p1Score += num;
                                                }
                                                else{
                                                    scores[i + (k + n + o + p - 1)%10 + 1][j][(k + n + o + p - 1)%10 + 1][l][1] += num;
                                                }
                                            }
                                            else{
                                                if((j + (l + n + o + p - 1)%10 + 1 >= 21)){
                                                    p2Score += num;
                                                }
                                                else{
                                                    scores[i][j + (l + n + o + p - 1)%10 + 1][k][(l + n + o + p - 1)%10 + 1][0] += num;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        long answer = p1Score;
        if(p1Score < p2Score){
            answer = p2Score;
        }
        System.out.println("" + p1Score + " " + p2Score);
        System.out.println("Answer: " + answer);
    }
}


