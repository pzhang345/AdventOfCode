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
        Map map = new Map(lines);
        map.createNodes();
        System.out.println("Answer: " + map.getLongPath(new NodePath(new boolean[Node.all.size()],map.startNode,0)));
    }
}
class Path{
    int i;
    int j;
    boolean[][] hasGone;
    int count;
    public Path(boolean[][] h, int newi, int newj, int count){
        hasGone = copy(h);
        i = newi;
        j = newj;
        hasGone[i][j] = true;
        count ++;
    }
    public Path(Path p, int newi, int newj){
        hasGone = copy(p.hasGone);
        i = newi;
        j = newj;
        hasGone[i][j] = true;
        count = p.count + 1;
    }
    public boolean[][] copy(boolean[][] arr){
        boolean[][] temp = new boolean[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }
}
class NodeInt{
    int dist;
    Node node;
    public NodeInt(int d,Node n){
        dist = d;
        node = n;
    }
    public String toString(){
        return node + ":" + dist;
    }
}
class Node{
    char name;
    int id;
    ArrayList<NodeInt> connect;
    int i;
    int j;
    public static ArrayList<Node> all; 
    public static int count = 0;
    public Node(int i1, int j1){
        i = i1;
        j = j1;
        connect = new ArrayList<NodeInt>();
        name = (char)('A' + count);
        id = count;
        count++;
    }
    public String toString(){
        return name + "(" + i + " " + j + ")";
    }
    public void addNode(int distance, int index){
        connect.add(new NodeInt(distance,all.get(index - 1)));
    }
}
class NodePath{
    boolean[] hasGone;
    Node node;
    int total;
    public NodePath(boolean[] h, Node n, int t){
        total = t;
        node = n;
        hasGone = copy(h);
        hasGone[node.id] = true;
    }
    public boolean[] copy(boolean[] arr){
        boolean[] temp = new boolean[arr.length];
        for(int i = 0; i < arr.length; i++){
            temp[i] = arr[i];
        }
        return temp;
    }
}
class Map{
    char[][] map;
    public Node startNode;
    public Node endNode;
    public Map(String[] lines){
        map = new char[lines.length][];
        for(int i = 0; i < map.length; i++){
            map[i] = lines[i].toCharArray();
        }
    }
    public void createNodes(){
        ArrayList<Node> all = new ArrayList<Node>();
        startNode = new Node(0,1);
        all.add(startNode);
        for(int i = 1; i < map.length - 1; i++){
            for(int j = 1; j < map[0].length - 1; j++){
                int count = 0;
                if(map[i][j - 1] == '>'){
                    count ++;
                }
                if(map[i][j + 1] == '>'){
                    count ++;
                }
                if(map[i - 1][j] == 'v'){
                    count ++;
                }
                if(map[i + 1][j] == 'v'){
                    count ++;
                }
                if(count >= 3){
                    all.add(new Node(i,j));
                }
            }
        }
        endNode = new Node(map.length - 1, map[0].length - 2);
        all.add(endNode);
        System.out.println(all);
        Node.all = all;
        int[][] hasNode = new int[map.length][map[0].length];
        int count = 1;
        for(Node i:all){
            hasNode[i.i][i.j] = count;
            count ++;
        }
        for(Node i:all){
            move(hasNode,i);
            System.out.println(i);
            System.out.println(i.connect);
            System.out.println();
        }
    }
    public void move(int[][] hasNode, Node test){
        ArrayList<Path> q = new ArrayList<Path>();
        q.add(new Path(new boolean[map.length][map[0].length],test.i,test.j, 0));
        while(q.size() != 0){
            Path p = q.get(0);
            if(hasNode[p.i][p.j] != 0 && Node.all.get(hasNode[p.i][p.j] - 1) != test){
                test.addNode(p.count,hasNode[p.i][p.j]);
            }
            else{
                addPath(p.i - 1,p.j,p,q);
                addPath(p.i + 1,p.j,p,q);
                addPath(p.i,p.j - 1,p,q);
                addPath(p.i,p.j + 1,p,q);
            }
            q.remove(0);
        }
    }
    public void addPath(int i, int j,Path path, ArrayList<Path> q){
        try{
            if(map[i][j] != '#' && !path.hasGone[i][j]){
                q.add(new Path(path,i,j));
            }
        }catch(Exception e){}
    }
    public int getLongPath(NodePath node){
        if(node.node == endNode){
            return node.total;
        }
        //System.out.println(node.node);
        int max = Integer.MIN_VALUE;
        for(NodeInt i:node.node.connect){
            if(!node.hasGone[i.node.id]){
                int temp = getLongPath(new NodePath(node.hasGone,i.node,node.total + i.dist));
                if(temp > max){
                    max = temp;
                }
            }
        }
        return max;
    }
}
