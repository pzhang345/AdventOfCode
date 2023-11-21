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
        int p1Score = 0;
        int p1Pos = Integer.parseInt("" + lines[0].charAt(lines[0].length() - 1));
        int p2Score = 0;
        int p2Pos = Integer.parseInt("" + lines[1].charAt(lines[1].length() - 1));
        int numRoll = 0;
        boolean p1turn = true;
        while(p1Score < 1000 && p2Score < 1000){
            int sum = numRoll%100 * 3 + 6;
            System.out.println(sum);
            if(p1turn){
                p1Pos = (p1Pos + sum%10 -1)%10 + 1;
                p1Score += p1Pos;
                //System.out.println("p1Pos: " + p1Pos); 
                p1turn = false;
            }
            else{
                p2Pos = (p2Pos + sum%10 - 1)%10 + 1;
                p2Score += p2Pos;
                p1turn = true;
            }
            numRoll += 3;
        }
        System.out.println("" + p1Score + " " + p2Score + " " + numRoll);
        int answer = p1Score;
        if(p1Score > p2Score){
            answer = p2Score;
        }
        System.out.println("Answer: " + (numRoll * answer));
        
    }
}


