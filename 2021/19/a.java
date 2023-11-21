import java.io.*;
import java.util.*;
class a{
    public static boolean allTrue(boolean[] arr){
        for(boolean i:arr){
            if(!i){
                return false;
            }
        }
        return true;
    }
    public static void printArr(boolean[] arr){
        String str = "[";
        for(boolean i:arr){
            str += i + ",";
        }
        System.out.println(str + "]");
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
        int scannerCount = 0;
        for(String i:lines){
            if(i.contains("scanner")){
                scannerCount ++;
            }
        }
        System.out.println(new Beacon(3,-10,5).getxyz("-z+x-y")[0]);
        Scanner[] scanners = new Scanner[scannerCount];
        int index = -1;
        for(String i:lines){
            if(i.contains("scanner")){
                index ++;
                scanners[index] = new Scanner();
            }
            else if(i.contains(",")){
                scanners[index].addBeacon(i);
            }
        }
        scanners[0].rotation = "+x+y+z";
        scanners[0].scannerPos = new int[3];
        scanners[0].addBeaconToList();
        boolean[] insystem = new boolean[scanners.length];
        boolean[] hasDone = new boolean[scanners.length];
        for(int i = 0; i < insystem.length; i++){
            insystem[i] = false;
            hasDone[i] = false;
        }
        insystem[0] = true;
        Scanner scan = scanners[0];
        
        while(!allTrue(insystem)){
            for(int i = 0; i < scanners.length; i++){
                if(insystem[i] && !hasDone[i]){
                    scan = scanners[i];
                    hasDone[i] = true;
                    break;
                }
            }
            for(int i = 0; i < scanners.length; i++){
                //System.out.println(i);
                if(!insystem[i] && scan.similarBeacons(scanners[i])){
                    insystem[i] = true;
                }
            }
            printArr(insystem);
            
        }
        
        //scan.similarBeacons(scanners[1]);
        //scan = scanners[1];
        //scan.similarBeacons(scanners[4]);
        for(int[] i:Scanner.allBeaconsPos){
            System.out.println("" + i[0] + " " + i[1] + " " + i[2]);
        }
        System.out.println("Answer: " + Scanner.allBeaconsPos.size());
        
    }
}
class Beacon{
    int x;
    int y;
    int z;
    public static String[] possiblePermutation = {
        "+x+y+z","+x-z+y","+x-y-z","+x+z-y",
        "+y+z+x","+y-x+z","+y-z-x","+y+x-z",
        "+z+x+y","+z-y+x","+z-x-y","+z+y-x",
        "-x+z+y","-x-y+z","-x-z-y","-x+y-z",
        "-y+x+z","-y-z+x","-y-x-z","-y+z-x",
        "-z+y+x","-z-x+y","-z-y-x","-z+x-y"
    };
    public Beacon(int a,int b,int c){
        this.x = a;
        this.y = b;
        this.z = c;
    }
    public int[] getxyz(String permutation){
        int[] pos = new int[3];
        for(int i = 0; i < pos.length; i++){
            if(permutation.charAt(2 * i + 1) == 'x'){
                pos[i] = x;
            }
            if(permutation.charAt(2 * i + 1) == 'y'){
                pos[i] = y;
            }
            if(permutation.charAt(2 * i + 1) == 'z'){
                pos[i] = z;
            }
            if(permutation.charAt(2 * i) == '-'){
                pos[i] *= -1;
            }
        }
        return pos;
    }
    public String toString(){
        return x + "," + y + "," + z;
    }
}
class Scanner{
    ArrayList<Beacon> beacons;
    String rotation;
    int[] scannerPos;
    public static ArrayList<int[]> allBeaconsPos = new ArrayList<int[]>();
    public Scanner(){
        beacons = new ArrayList<Beacon>();
    }
    public void addBeacon(String str){
        String[] nums = str.split(",");
        beacons.add(new Beacon(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), Integer.parseInt(nums[2])));
    }
    public boolean similarBeacons(Scanner other){
        for(String i:Beacon.possiblePermutation){
            for(Beacon j:other.beacons){
                int[] jpos = j.getxyz(i);
                for(Beacon k: beacons){
                    int[] kpos = k.getxyz(rotation);
                    int[] offset = new int[3];
                    offset[0] =  (kpos[0] + scannerPos[0]) - jpos[0];
                    offset[1] =  (kpos[1] + scannerPos[1]) - jpos[1];
                    offset[2] =  (kpos[2] + scannerPos[2]) - jpos[2];
                    //if(j == other.beacons.get(0)){
                        //System.out.println("" + offset[0] + " " + offset[1] + " " + offset[2]);
                        //System.out.println("" + (kpos[0] + scannerPos[0]) + " " + (kpos[1] + scannerPos[1]) + " " + (kpos[2] + scannerPos[2]));
                    //}
                    if(checkOffset(offset,other,i)){
                        other.rotation = i;
                        other.scannerPos = offset;
                        System.out.println("" + offset[0] + " " + offset[1] + " " + offset[2]);
                        System.out.println(other.rotation);
                        other.addBeaconToList();
                        System.out.println(allBeaconsPos.size());
                        return true;
                    }
                }
            } 
        }
        return false;
    }
    public boolean checkOffset(int[] offset, Scanner other, String permutation){
        int count = 0;
        for(Beacon k:other.beacons){
            int[] kpos = k.getxyz(permutation);
            for(Beacon l:beacons){
                boolean samePos = true;
                for(int i = 0; i < 3; i++){
                    if(kpos[i] + offset[i] != l.getxyz(rotation)[i] + scannerPos[i]){
                        samePos = false;
                    }
                    //else if(offset[0] == -20 && offset[1] == -1133 && offset[2] == 1061){
                        //System.out.println(i + " " + (kpos[i] + offset[i]));
                    //}
                }
                if(samePos){
                    count++;
                }
            }
        }
        //System.out.println("a: " + count);
        return count >= 12;
    }
    public void addBeaconToList(){
        boolean has = false;
        for(Beacon i:beacons){
            has = false;
            int[] pos = i.getxyz(rotation);
            pos[0] += scannerPos[0];
            pos[1] += scannerPos[1];
            pos[2] += scannerPos[2];
            for(int[] j:allBeaconsPos){
                if(pos[0] == j[0] && pos[1] == j[1] && pos[2] == j[2]){
                    has = true;
                    break;
                }
            }
            if(!has){
                
                allBeaconsPos.add(pos);
            }
        }
    }
    public String toString(){
        return "" + beacons;
    }
}


