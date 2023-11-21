import java.io.*;
class b{
    public static boolean allSame(int[][] map){
        for(int[] i:map){
            for(int j:i){
                if(j != map[0][0]){
                    return false;
                }
            }
        }
        return true;
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
        int [][] octopus = new int[lines.length][lines[0].length()];
        for(int i = 0; i < octopus.length; i++){
            for(int j = 0; j < octopus[i].length; j++){
                octopus[i][j] = Integer.parseInt("" + lines[i].charAt(j));
            }
        }
        int count = 0;
        while(!allSame(octopus)){
            count ++;
            for(int j = 0; j < octopus.length; j++){
                for(int k = 0; k < octopus[j].length; k++){
                    octopus[j][k] ++;
                }
            }
            boolean isdone = false;
            while(!isdone){
                isdone = true;
                for(int j = 0; j < octopus.length; j++){
                    for(int k = 0; k < octopus[j].length; k++){
                        if(octopus[j][k] > 9){
                            octopus[j][k] = -1;
                            isdone = false;
                            for(int l = -1; l <= 1; l++){
                                for(int m = -1; m <= 1; m++){
                                    try{
                                        if(octopus[j+l][k+m] != -1){
                                            octopus[j+l][k+m] ++;
                                        }
                                    }
                                    catch(IndexOutOfBoundsException e){}
                                }
                            }
                        }
                    }
                }
            }
            for(int j = 0; j < octopus.length; j++){
                for(int k = 0; k < octopus[j].length; k++){
                    if(octopus[j][k] == -1){
                        octopus[j][k] = 0;
                    }
                }
            }
        }
        System.out.println("Answer: " + count);
    }
}


