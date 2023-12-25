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
            new Node(i);
        }
        for(int i = 0; i < Node.all.size(); i++){
            Node.all.get(i).connect();
            System.out.println(Node.all.get(i));
        }
        Node.countc = new int[Node.all.size()][Node.all.size()]; 
        for(Node i:Node.all){
            i.BFS();
        }
        System.out.println(Node.connections.size());
        Node.orderConnections();
        System.out.println("Answer: " + Node.getAnswer());
    }
}
class Node{
    String name;
    int id;
    String[] connectN;
    ArrayList<Node> connect;
    public static ArrayList<Node> all = new ArrayList<Node>();
    public static ArrayList<Node[]> connections = new ArrayList<Node[]>();
    public static int[][] countc;
    public static int count = 0;
    public Node(String line){
        name = line.split(": ")[0];
        connect = new ArrayList<Node>();
        connectN = line.split(": ")[1].split(" ");
        id = count;
        count ++;
        all.add(this);
    }
    public Node(String n, Node c){
        name = n;
        connectN = new String[0];
        connect = new ArrayList<Node>();
        connect.add(c);
        id = count;
        count ++;
        all.add(this);
    }
    public void connect(){
        for(String i: connectN){
            boolean hasChanged = false;
            for(Node n: all){
                if(i.equals(n.name)){
                    hasChanged = true;
                    connect.add(n);
                    n.connect.add(this);
                    break;
                }
            }
            if(!hasChanged){
                Node n = new Node(i,this);
                connect.add(n);
            }
        }
    }
    public String toString(){
        String temp = name + ": ";
        for(Node i:connect){
            temp += i.name + " ";
        }
        return temp;
    }
    public void BFS(){
        Node curr;
        ArrayList<Node> q = new ArrayList<Node>();
        q.add(this);
        boolean[] hasGone = new boolean[all.size()];
        hasGone[id] = true;
        while(q.size() != 0){
            curr = q.get(0);
            for(Node i:curr.connect){
                if(!hasGone[i.id]){
                    countc[curr.id][i.id] ++;
                    //System.out.println(countc[id][i.id]);
                    hasGone[i.id] = true;
                    q.add(i);
                }
            }
            q.remove(0);
        }
    }
    public static void orderConnections(){
        ArrayList<int[]> best = new ArrayList<int[]>();
        ArrayList<Integer> score = new ArrayList<Integer>();
        for(int i = 0; i < countc.length; i++){
            for(int j = i + 1; j < countc.length; j++){
                boolean hasAdded = false;
                int count = countc[i][j] + countc[j][i];
                for(int k = 0; k < score.size(); k++){
                    if(score.get(k) < count){
                        hasAdded = true;
                        score.add(k,count);
                        best.add(k,new int[]{i,j});
                        break;
                    }
                }
                if(count > 0 && !hasAdded){
                    score.add(count);
                    best.add(new int[]{i,j});
                }
            }
        }
        for(int[] i:best){
            //System.out.println(i.length);
            connections.add(new Node[]{all.get(i[0]),all.get(i[1])});
        }
    }
    public static int getAnswer(){
        for(int i = 0; i < connections.size(); i++){
            for(int j = i + 1; j < connections.size(); j++){
                for(int k = j + 1; k < connections.size(); k++){
                    //System.out.println(i + " " + j + " " + k);
                    int answer = removeConnections(new Node[][]{connections.get(i),connections.get(j),connections.get(k)});
                    if(answer != -1){
                        return answer;
                    }
                }
                System.out.println(i + " " + j);
            }
        }
        return -1;
    }
    public static int removeConnections(Node[][] cons){
        boolean[][] inCon = new boolean[all.size()][all.size()];
        for(Node[] i: cons){
            inCon[i[0].id][i[1].id] = true;
            inCon[i[1].id][i[0].id] = true;
        }
        int[] hasGone = new int[all.size()];
        ArrayList<Node> q = new ArrayList<Node>();
        q.add(cons[0][0]);
        hasGone[cons[0][0].id] = 1;
        int count1 = 1;
        Node curr;
        while(q.size() != 0){
            curr = q.get(0);
            for(Node i:curr.connect){
                if(!inCon[curr.id][i.id] && hasGone[i.id] == 0){
                    hasGone[i.id] = 1;
                    q.add(i);
                    count1 ++;
                }
            }
            q.remove(0);
        }
        int count2 = 1;
        q.add(cons[0][1]);
        if(hasGone[cons[0][1].id] == 1){
            return -1;
        }
        hasGone[cons[0][1].id] = 2;
        while(q.size() != 0){
            curr = q.get(0);
            for(Node i:curr.connect){
                if(!inCon[curr.id][i.id] && hasGone[i.id] == 0){
                    hasGone[i.id] = 2;
                    q.add(i);
                    count2 ++;
                }
                else if(!inCon[curr.id][i.id] && hasGone[i.id] == 1){
                    return -1;
                }
            }
            q.remove(0);
        }
        System.out.println(count1 + " " + count2);
        if(count1 + count2 == all.size()){
            return count1 * count2;
        }
        else{
            return -1;
        }
    }
}