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
        Reactor reactor = new Reactor(50);
        for(String i:lines){
            reactor.doInstruct(i);
        }
        System.out.println("Answer: " + reactor.countOn());
    }
}
class Reactor{
    boolean[][][] cubes;
    int low;
    public Reactor(int size){
        cubes = new boolean[size*2+1][size*2+1][size*2+1];
        low = size;
    }
    public void doInstruct(String str){
        String[] range = str.split(",");
        int[] start = new int[3];
        int[] end = new int[3];
        for(int i = 0; i < range.length; i++){
            start[i] = Integer.parseInt(range[i].split("=")[1].split("\\.\\.")[0]);
            end[i] = Integer.parseInt(range[i].split("=")[1].split("\\.\\.")[1]);
            if(Math.abs(start[i]) > low || Math.abs(end[i]) > low){
                return;
            }
        }
        
        if(str.substring(0,2).equals("on")){
            switchState(start,end,true);
        }
        if(str.substring(0,3).equals("off")){
            switchState(start, end, false);
        }
    }
    public void switchState(int[] start,int[] end,boolean turnOn){
        for(int i = start[0]; i <= end[0]; i++){
            for(int j = start[1]; j <= end[1]; j++){
                for(int k = start[2]; k <= end[2]; k++){
                    cubes[i + low][j + low][k + low] = turnOn;
                }
            }
        }
    }
    public int countOn(){
        int count = 0;
        for(int i = 0; i < cubes.length; i++){
            for(int j = 0; j < cubes[i].length; j++){
                for(int k = 0; k < cubes[i][j].length; k++){
                    if(cubes[i][j][k]){
                        count ++;
                    }
                }
            }
        }
        return count;
    }
}


