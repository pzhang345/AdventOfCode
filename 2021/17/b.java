import java.io.*;
import java.util.ArrayList;
class b{
    public static ArrayList<Integer> yworks(int yvel, int min, int max){
        int step = 0;
        int ypos = 0;
        ArrayList<Integer> works = new ArrayList<Integer>();
        if(yvel > 0){
            step = yvel * 2;
            yvel = -1*yvel - 1;
        }
        while(min < ypos){
            step ++;
            ypos += yvel;
            yvel --;
            if(min <= ypos && ypos <= max){
                works.add(step);
            }
        }
        //System.out.println(yvel + " " + works);
        return works;
    }
    public static int xval(int xvel,int step){
        if(xvel < step){
            return (xvel)*(xvel + 1)/2;
        }
        else{
            return (xvel)*(xvel + 1)/2 - (xvel - step)*(xvel - step + 1)/2;
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
        //System.out.println(lines[0].split("=")[1]);
        int minx = Integer.parseInt(lines[0].split("=")[1].split("\\.\\.")[0]);
        int maxx = Integer.parseInt(lines[0].split("=")[1].split("\\.\\.")[1].split(",")[0]);
        int miny = Integer.parseInt(lines[0].split("=")[2].split("\\.\\.")[0]);
        int maxy = Integer.parseInt(lines[0].split("=")[2].split("\\.\\.")[1]);
        int count = 0;
        for(int y = miny; y <= Math.abs(miny); y++){
            ArrayList<Integer> yworks = yworks(y,miny,maxy);
            System.out.println(y + " " + yworks);
            for(int x = 0; x <= maxx; x++){  
                for(int i: yworks){
                    int xpos = xval(x, i);
                    if(minx <= xpos && xpos <= maxx){
                        count ++;
                        System.out.println("" + x + "," + y);
                        break;
                    }
                }

            }
        }
        System.out.println("Answer: " + count);
    }
}


