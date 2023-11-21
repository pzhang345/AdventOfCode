import java.io.*;
import java.util.ArrayList;
class b{
    public static String hexTob(String hex){
        String b = "";
        for(int i = 0; i < hex.length();i++){
            switch(hex.charAt(i)){
                case '0':
                    b += "0000";
                    break;
                case '1': 
                    b += "0001";
                    break;
                case '2': 
                    b += "0010";
                    break;
                case '3': 
                    b += "0011";
                    break;
                case '4': 
                    b += "0100";
                    break;
                case '5': 
                    b += "0101";
                    break;
                case '6': 
                    b += "0110";
                    break;
                case '7': 
                    b += "0111";
                    break;
                case '8': 
                    b += "1000";
                    break;
                case '9': 
                    b += "1001";
                    break;
                case 'A': 
                    b += "1010";
                    break;
                case 'B': 
                    b += "1011";
                    break;
                case 'C': 
                    b += "1100";
                    break;
                case 'D': 
                    b += "1101";
                    break;
                case 'E': 
                    b += "1110";
                    break;
                case 'F': 
                    b += "1111";
                    break;
            }
        }
        return b;
    }
    public static int bToInt(String b){
        int num = 0;
        for(int i = 0; i < b.length(); i++){
            if(b.charAt(i) == '1'){
                num += Math.pow(2,b.length() - i - 1);
            }
        }
        return num;
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
        String b = hexTob(lines[0]);
        System.out.println("Answer: " + new Packet(b).findValue());
    }
}
class Packet{
    String b;
    int version;
    int type;
    long value;
    ArrayList<Packet> subpackets;
    public static int versionSum = 0;
    public static int bToInt(String b){
        int num = 0;
        for(int i = 0; i < b.length(); i++){
            if(b.charAt(i) == '1'){
                num += Math.pow(2,b.length() - i - 1);
            }
        }
        //System.out.println(num);
        return num;
    }
    public static long bToLong(String b){
        long num = 0;
        for(int i = 0; i < b.length(); i++){
            if(b.charAt(i) == '1'){
                num += Math.pow(2,b.length() - i - 1);
            }
        }
        //System.out.println(num);
        return num;
    }
    public static int findLength(String str){
        int t = bToInt(str.substring(3,6));
        if(t == 4){
            for(int i = 6; i < str.length(); i += 5){
                if(str.charAt(i) == '0'){
                    return i + 5;
                }
            }
        }
        else{
            if(str.charAt(6) == '0'){
                //System.out.println(str.substring(7,22));
                return 22 + bToInt(str.substring(7,22));
            }
            else if(str.charAt(6) == '1'){
                int num = bToInt(str.substring(7,18));
                int total = 18;
                for(int i = 0; i < num; i++){
                    //System.out.println(total + " " + str.length() + " " + findLength(str.substring(total - 1)) + " " + str.substring(total - 1) + " " + num);
                    total += findLength(str.substring(total));
                }
                return total;
            }
        }
        return str.length();
    }
    public Packet(String bin){
        b = bin;
        version = bToInt(b.substring(0,3));
        versionSum += version;
        type = bToInt(b.substring(3,6));
        if(type == 4){
            String temp = "";
            for(int i = 6; i<bin.length(); i++){
                if((i-6)%5 != 0){
                    temp += "" + bin.charAt(i);
                }
            }
            value = Packet.bToLong(temp);
        }
        subpackets = new ArrayList<Packet>();
        findSubpackets();
    }
    
    public void findSubpackets(){
        if(type == 4){
            //System.out.println("s");
        }
        else{
            if(b.charAt(6) == '0'){
                //System.out.println("a");
                int length = bToInt(b.substring(7,22));
                String str = b.substring(22,22 + length);
                while(str.length() != 0){
                    //System.out.println(str);
                    int subLength = findLength(str);
                    //System.out.println("" + str + " " + subLength + " " + str.substring(0,subLength));
                    subpackets.add(new Packet(str.substring(0,subLength)));
                    str = str.substring(subLength);
                    //System.out.println(str.length());
                }
            }
            else if(b.charAt(6) == '1'){
                //System.out.println("b");
                int num = bToInt(b.substring(7,18));
                String str = b.substring(18,b.length());
                for(int i = 0; i < num; i++){
                    //System.out.println(str);
                    int length = findLength(str);
                    //System.out.println("" + str + " " + length + " " + num);
                    subpackets.add(new Packet(str.substring(0,length)));
                    str = str.substring(length);
                }
            }
        }
    }
    public long findValue(){
        if(type == 4){
            return value;
        }
        else if(type == 0){
            long total = 0;
            for(Packet i: subpackets){
                total += i.findValue();
            }
            return total; 
        }
        else if(type == 1){
            long total = 1;
            for(Packet i: subpackets){
                total *= i.findValue();
            }
            return total; 
        }
        else if(type == 2){
            long min = -1;
            for(Packet i: subpackets){
                long tempValue = i.findValue();
                if(min == -1 || tempValue < min){
                    min = tempValue;
                }
            }
            return min; 
        }
        else if(type == 3){
            long max = -1;
            for(Packet i: subpackets){
                long tempValue = i.findValue();
                if(max == -1 || tempValue > max){
                    max = tempValue;
                }
            }
            return max; 
        }
        else if(type == 5){
            if(subpackets.get(0).findValue() > subpackets.get(1).findValue()){
                return 1;
            }
            return 0;
        }
        else if(type == 6){
            if(subpackets.get(0).findValue() < subpackets.get(1).findValue()){
                return 1;
            }
            return 0;
        }
        else if(type == 7){
            if(subpackets.get(0).findValue() == subpackets.get(1).findValue()){
                return 1;
            }
            return 0;
        }
        return 0;
    }
}

