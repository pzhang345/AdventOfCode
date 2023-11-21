import java.io.*;
import java.util.*;
class a{
    public static ArrayList<Pair> stack = new ArrayList<Pair>();
    public static int solve(int w,int z, int a, int b, int c){
        /*
        int x = 0;
        x += z;
        x = x%26;
        z /= 26;
        x += b;
        if(x == w){
            x = 0;
        }
        else{
            x = 1;
        }
        int y = 25;
        y *= x;
        y += 1;
        z *= y;
        y = (w + c) * x;
        z += y;
        return z;
        */

        
        z /= a;
        int x;
        if(z%26 + b == w){
            x = 0;
        }
        else{
            x = 1;
        }
        z = (z * (25*x + 1)) + (w + c) * x;
        return z;
    }
    public static void addToList(int i,int b, int c){
        if(b > 0){
            stack.add(new Pair(i,c));
        }
        else{
            //System.out.println(stack.size());
            for(int j = stack.size() - 1; j >= 0; j--){
                if(stack.get(j).addPair(i, b)){
                    break;
                }
            }
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
        /*
        for(int i = 0; i < 14; i++){
            System.out.println("" + (i + 1) + ".\t" + lines[i * 18 + 4].split(" ")[2] + "\t" + lines[i * 18 + 5].split(" ")[2] + "\t" + lines[i * 18 + 15].split(" ")[2]);
        }
        */
        //System.out.println(solve(9,0,1,12,6));
    
        for(int i = 0; i < 14; i++){  
            int b = Integer.parseInt(lines[i * 18 + 5].split(" ")[2]);
            int c = Integer.parseInt(lines[i * 18 + 15].split(" ")[2]);
            addToList(i,b,c);
        }  
        System.out.println(stack);
        int[] answer = new int[14];
        for(Pair i: stack){
            int[][] numsarr = i.returnnums();
            for(int[] j:numsarr){
                answer[j[0]] = j[1];
            }
        }
        System.out.print("Answer: ");
        for(int i: answer){
            System.out.print(i);
        }
        System.out.println();
    }
}
class Pair{
    int index1;
    int c1;
    int index2;
    int b2;
    boolean hasadded;

    public Pair(int i1, int i2, int c, int b){
        index2 = i2;
        b2 = b;
        hasadded = true;
    }
    public Pair(int i1, int c){
        index1 = i1;
        c1 = c;
        hasadded = false;
    }
    public boolean addPair(int i2, int b){
        if(!hasadded){
            index2 = i2;
            b2 = b;
            hasadded = true;
            return true;
        }
        return false;
    }
    public String toString(){
        return "" + index1 +" "+ index2 + " " +  c1 +" "+ b2;
    }
    public int[][] returnnums(){
            for(int i = 9; i >= 1; i--){
                if(c1 + b2 + i <= 9){
                    return new int[][]{{index1,i},{index2,c1 + b2 + i}};
                }
            }
        return null;
    }
}
/*
inp w       w = input
mul x 0     x = 0
add x z     x = z
mod x 26    x = z%26
div z a     z = z/a
add x b     x = z%26 + b
eql x w     x = (z%26 + b == w)(0(f) or 1(t))
eql x 0     x = (z%26 + b == w)(0(t) or 1(f)) 
mul y 0     y = 0
add y 25    y = 25
mul y x     y = 25*x
add y 1     y = 25*x + 1
mul z y     z = z/1 * (25*x + 1)
mul y 0     y = 0
add y w     y = w
add y c     y = w + c
mul y x     y = (w + c) * x
add z y     z = (z/a * (25*x + 1)) + (w + c) * x


        a       b       c
1.      1       12      6
2.      1       10      6
3.      1       13      3
4.      26      -11     11
5.      1       13      9
6.      26      -1      3
7.      1       10      13
8.      1       11      6
9.      26      0       14
10.     1       10      10
11.     26      -5      12
12.     26      -16     10
13.     26      -7      11
14.     26      -11     15

when b =< 0, a = 26 and when b >= 0, a = 1

        b       c
1.      12      6
2.      10      6
3.      13      3
4.      -11     11
5.      13      9
6.      -1      3
7.      10      13
8.      11      6
9.      0       14
10.     10      10
11      -5      12
12.     -16     10
13.     -7      11
14.     -11     15
    base 26 num, every b >= 0 grow number and 1 greater power, every b < 0, move down one power if x = 0
    for every b < 0, b + num = top of stack c + num at top stack, so x = 0, when b < 0

 */


