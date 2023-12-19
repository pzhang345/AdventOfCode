import java.io.*;
import java.util.*;
class b{
    public static int hexToInt(String hex){
        char[] convert = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        int total = 0;
        int count = 0;
        for(int i = hex.length() - 1; i >= 0; i--){
            for(int j = 0; j < convert.length; j++){
                if(hex.charAt(i) == convert[j]){
                    total += j * (Math.pow(16,count));
                    count++;
                }
            }
        }
        return total;
    }
    public static long[][] sort(long[][] arr){
        ArrayList<long[]> temp = new ArrayList<long[]>();
        for(int i = 0; i < arr.length; i++){
            temp.add(arr[0]);
            for(int j = 0; j < temp.size(); j++){
                if(arr[i][2] < temp.get(j)[2]){
                    temp.add(j,arr[i]);
                    break;
                }
            }
            if(temp.size() != i + 1){
                temp.add(arr[i]);
            }
        }
        long[][] temp2 = new long[arr.length][arr[0].length];
        for(int i = 0; i < temp2.length; i++){
            for(int j = 0; j < temp2[i].length; j++){
                temp2[i][j] = temp.get(i)[j];
                System.out.print(temp.get(i)[j] + "\t");
            }
            System.out.println();
        }
        return temp2;
    }
    public static ArrayList<Long> getInterval(long x, long[][] arr,long[][] arrx){
        ArrayList<Long> intervals = new ArrayList<Long>();
        for(long[] i: arr){
            if(i[0]<=x && x <= i[1]){
                intervals.add(i[2]);
            }
        }
        return intervals;
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
        HashMap<Character,Character> c = new HashMap<Character,Character>();
        c.put('0','R');
        c.put('1', 'D');
        c.put('2','L');
        c.put('3', 'U');
        String[] temp = new String[lines.length];
        for(int i = 0; i < lines.length; i++){
            String tempLine = lines[i].split(" ")[2];
            temp[i] = c.get(tempLine.charAt(7)) + " " + hexToInt(tempLine.substring(1,7));
            System.out.println(temp[i]);
        }
        long xpos = 0;
        long ypos = 0;
        long[][] points = new long[lines.length + 1][2];
        points[0] = new long[]{0,0};
        int count = 1;
        long total = 0;
        for(String i:temp){
            //System.out.println(i);
            char dir = i.charAt(0);
            int num = Integer.parseInt(i.split(" ")[1]);
            if(dir == 'L'){
                xpos -= num;
            }
            else if(dir == 'R'){
                xpos += num;
            }
            else if(dir == 'D'){
                ypos -= num;
            }
            else if(dir == 'U'){
                ypos += num;
            }
            points[count][0] = xpos;
            points[count][1] = ypos;
            count ++;
        }
        for(int i = 0; i < points.length - 1; i++){
            total += points[i][0] * points[i + 1][1] - (points[i][1] * points[i + 1][0]); 
        }
        total = Math.abs(total)/2;
        long perimeter = 0;
        for(int i = 0; i < points.length - 1; i++){
            if(points[i][0] == points [i+1][0]){
                perimeter += Math.abs(points[i][1] - points[i + 1][1]);
            }
            else{
                perimeter += Math.abs(points[i][0] - points[i + 1][0]);
            }
        }
        total += perimeter/2 + 1;
        System.out.println(total);
        // linex = sort(linex);
        // System.out.println();
        // liney = sort(liney);
        // //System.out.println(minx + " " + maxx + " " + miny + " " + maxy);
        // for(long x = minx; x <= maxx; x++){
        //         ArrayList<Long> intervals = getInterval(x, liney,linex);
        //         System.out.println(intervals);
        //         for(int i = 0; i < intervals.size(); i+=2){
        //             total += intervals.get(i+1) - intervals.get(i) + 1;
        //         }
        //     }
        // System.out.println(total);
    }
}