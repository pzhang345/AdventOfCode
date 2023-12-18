import java.io.*;
import java.util.*;
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
        Map map = new Map(lines);
        map.move();
        for(BestScore i: map.scores[map.scores.length - 1][map.scores[0].length - 1].scores){
            int num = i.dist;
            if(4 <= num && num <= 10){
                System.out.println("Answer: " + i.score);
                break;
            }
        }
        //System.out.println("Answer: " + map.scores[map.scores.length - 1][map.scores[0].length - 1].scores.get(0));
        //Map.printArr(map.scores);
    }
}
class BestScore{
    int score;
    char dir;
    int dist;
    public BestScore(int s, char d, int di){
        score = s;
        dir = d;
        dist = di;
    }
    public String toString(){
        //return score + " " + dir + " " + dist;
        return "" + score;
    }
    public boolean equals(BestScore other){
        return score == other.score && dir == other.dir && dist == other.dist;
    }
}
class BestScores{
    ArrayList<BestScore> scores;
    public BestScores(){
        scores = new ArrayList<BestScore>();
    }
    public boolean add(BestScore a){
        for(int i = 0; i < scores.size();i++){
            if(a.equals(scores.get(i))){
                //System.out.println("a");
                return false;
            }
            if(a.score < scores.get(i).score){
                scores.add(i,a); 
                break;
            }
        }
        if(!scores.contains(a)){
            scores.add(a);
        }
        boolean[] left = new boolean[11];
        boolean[] right = new boolean[11];
        boolean[] up = new boolean[11];
        boolean[] down = new boolean[11];
        for(int i = 0; i < scores.size(); i++){
            if((scores.get(i).dir == 'l' && left[scores.get(i).dist]) || 
               (scores.get(i).dir == 'r' && right[scores.get(i).dist]) || 
               (scores.get(i).dir == 'u' && up[scores.get(i).dist]) || 
               (scores.get(i).dir == 'd' && down[scores.get(i).dist])){
                scores.remove(i);
                i --;
            }
            else{
                if(scores.get(i).dir == 'l'){
                    left[scores.get(i).dist] = true;
                }
                else if(scores.get(i).dir == 'r'){
                    right[scores.get(i).dist] = true;
                }
                else if(scores.get(i).dir == 'u'){
                    up[scores.get(i).dist] = true;
                }
                else if(scores.get(i).dir == 'd'){
                    down[scores.get(i).dist] = true;
                }
            }
        }
        return scores.contains(a);
    }
    public String toString(){
        return "" + scores;
    }
}
class Map{
    int[][] vals;
    BestScores[][] scores;
    public static HashMap<Character,Character> oppisite = new HashMap<Character,Character>();
    public Map(String[] lines){
        vals = new int[lines.length][lines[0].length()];
        scores = new BestScores[lines.length][lines[0].length()];
        for(int i = 0; i < vals.length; i++){
            char[] temp = lines[i].toCharArray();
            for(int j = 0; j < vals[i].length; j++){
                vals[i][j] = temp[j] - '0';
                scores[i][j] = new BestScores();
            }
        }
        printArr(vals);
        oppisite.put('l','r');
        oppisite.put('u','d');
        oppisite.put('r','l');
        oppisite.put('d','u');
        oppisite.put(' ',' ');

    }
    public void move(int i, int j, BestScore score,String path){
        if(scores[i][j].add(score)){
            //System.out.println(scores[i][j]);
            //moveSpace(i - 1, j, 'u',score, path + "u");
            //moveSpace(i + 1, j, 'd',score, path + "d");
            //moveSpace(i, j - 1, 'l',score, path + "l");
            //moveSpace(i, j + 1, 'r',score, path + "r");
        }
        if(i == scores.length - 1 && j == scores[0].length - 1)
        {
            //System.out.println(path);
        }
    }
    public boolean moveSpace(int i, int j, char d, BestScore score){
        try{
            int dist = 0;
            if(score.dir == d){
                dist = score.dist;
            }
            if(!(dist >= 10 || oppisite.get(d) == score.dir)){
                //System.out.print(scores[i][j] + " ");
                if(scores[i][j].add(new BestScore(score.score +  vals[i][j], d, dist + 1))){
                    //System.out.println(scores[i][j]);
                    return true;
                }
            }
        }catch(Exception e){}
        //System.out.println();
        return false;
    }
    public void move(){
        scores[0][0].add(new BestScore(0, ' ', 0));
        boolean hasChanged = true;
        int count = 0;
        while(hasChanged){
            hasChanged = false;
            System.out.println(count);
            for(int i = 0; i < scores.length; i++){
                for(int j = 0; j < scores[i].length; j++){
                    for(BestScore score:scores[i][j].scores){
                        if(score.dist >= 4 || score.dir == ' '){
                            if(moveSpace(i - 1, j, 'u',score)){
                                hasChanged = true;
                            }
                            if(moveSpace(i + 1, j, 'd',score)){
                                hasChanged = true;
                            }
                            if(moveSpace(i, j - 1, 'l',score)){
                                hasChanged = true;
                            }
                            if(moveSpace(i, j + 1, 'r',score)){
                                hasChanged = true;
                            }
                        }
                        else{
                            char c = score.dir;
                            int i1 = 0;
                            int j1 = 0;
                            if(c == 'u'){
                                i1 = -1;
                            }
                            else if(c == 'd'){
                                i1 = 1;
                            }
                            else if(c == 'l'){
                                j1 = -1;
                            }
                            else if(c == 'r'){
                                j1 = 1;
                            }
                            if(moveSpace(i + i1,j + j1,c,score)){
                                hasChanged = true;
                            }
                        }
                        //System.out.println(scores[i][j].scores);
                    }
                }
            }
            count ++;
        }
    
    }
    public static void printArr(int[][] arr){
        for(int[] i: arr){
            for(int j: i){
                System.out.print(j);
            }
            System.out.println();
        }
    }
    public static <T> void printArr(T[][] arr){
        for(T[] i: arr){
            for(T j: i){
                System.out.print(j + "\t");
                if(("" + j).length() < 8){
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
}