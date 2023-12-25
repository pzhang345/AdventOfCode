import java.io.*;
import java.util.*;
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
        for(String i: lines){
            new Hail(i);
        }

        System.out.println("Answer: " + Hail.getSum(200000000000000L,400000000000000L));
        //System.out.println(Hail.getSum(7,27));
    }
}
class Hail{
    long sx;
    long sy;
    long sz;
    long vx;
    long vy;
    long vz;
    ArrayList<Double> ytox;
    double m;
    double b;
    public static ArrayList<Hail> all = new ArrayList<Hail>();
    public Hail(String str){
        while(str.contains("  ")){
            str = str.replaceAll("  "," ");
        }
        String[] pos = str.split(" @ ")[0].split(", ");
        sx = Long.parseLong(pos[0]);
        sy = Long.parseLong(pos[1]);
        sz = Long.parseLong(pos[2]);
        String[] vel = str.split(" @ ")[1].split(", ");
        vx = Long.parseLong(vel[0]);
        vy = Long.parseLong(vel[1]);
        vz = Long.parseLong(vel[2]);
        ytox = new ArrayList<Double>();
        all.add(this);
        m = (double)(vy)/vx;
        b = sy - sx * m;
        System.out.println(m + " " + b);
    }
    public static long min(long num1, long num2){
        if(num1 > num2){
            return num2;
        }
        return num1;
    }
    public static long max(long num1, long num2){
        if(num1 < num2){
            return num2;
        }
        return num1;
    }
    public static int getSum(long min, long max){
        int count = 0;
        for(int i = 0; i < all.size(); i++){
            for(int j = i + 1; j < all.size(); j ++){
                if(getIntercept(all.get(i), all.get(j), min, max)){
                    count ++;
                }
            }
        }
        return count;
    }
    public static boolean getIntercept(Hail h1, Hail h2, long min, long max){
        if(h1.m == h2.m && h1.b != h2.b){
            return false;
        }
        double x = (h1.b - h2.b)/(h2.m - h1.m);
        double y = (h1.b + h1.m * x);
        System.out.println(x + " " + y);
        if(min <= x && x < max && min <= y && y < max){
            if(((x <= h1.sx && h1.vx < 0) ||(x >= h1.sx && h1.vx > 0)) &&
               ((x <= h2.sx && h2.vx < 0) ||(x >= h2.sx && h2.vx > 0))){
                return true;
            }
            
        }
        return false;
    }
    public double getx(double y){
        return ((double)(y - sy)/vy) * vx + sx;
    }
    public double gety(double x){
        return ((double)(x - sx)/vx) * vy + sy;
    }
    public String toString(){
        return sx + " " + sy + " " + sz;
    }
}
//mx - mx = b - b;