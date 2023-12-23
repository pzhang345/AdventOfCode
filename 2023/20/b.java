import java.io.*;
import java.util.*;
class b{
    public static void printArr(int[] arr){
        for(int i: arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static long LCM(int[] arr){
        long total = 1;
        long mult = 1;
        for(int i = arr[0]; i > 0; i--){
            boolean isMult = true;
            for(int j = 0; j < arr.length; j++){
                if(arr[j] % i != 0){
                    isMult = false;
                    break;
                }
            }
            if(isMult){
                mult = i;
                break;
            }
        }
        for(int i = 0; i < arr.length; i++){
            total *= arr[i]/mult;
        }

        return total * mult;
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
        for(String i:lines){
            new Node(i);
        }
        new Node("button -> broadcaster");
        for(int i = 0; i < Node.nodes.size(); i++){
            Node.nodes.get(i).findNode();
            System.out.println(Node.nodes.get(i));
        }
        for(Node i: Node.nodes){
           System.out.println(i);
           System.out.println(i.prev);
           System.out.println();
        }
        while(true){
            Process.count ++;
            Node.pressButton();
            if(Process.b){
                break;
            }
            //System.out.println();
        }
        printArr(Process.first);
        printArr(Process.loop);
        System.out.println("Answer: " + LCM(Process.loop));
    } 
}
class Process{
    Node sentNode;
    Node recNode;
    boolean value;
    public static long high = 0;
    public static long low = 0;
    public static boolean b = false;
    public static int[] first;
    public static int[] loop;
    public static int count;
    public Process(Node s, Node r, boolean v){
        sentNode = s;
        recNode = r;
        value = v;
        if(v){
            high ++;
        }
        else{
            low ++;
        }
        if(recNode.name.equals("rx") && !v){
            b = true;
        }
        //System.out.println(this);
        if(recNode == Node.rx.prev.get(0) && v){
            ArrayList<Node> prev = Node.rx.prev.get(0).prev;
            for(int i = 0; i < prev.size(); i++){
                if(sentNode == prev.get(i)){
                    if(first[i] == 0){
                        first[i] = count;
                    }
                    else if(loop[i] == 0){
                        loop[i] = count - first[i];
                    }
                    System.out.println(count + " " + sentNode);
                    break;
                }
            }
            boolean done = true;
            for(int i = 0; i < loop.length; i++){
                if(loop[i] == 0){
                    done = false;
                    break;
                }
            }
            if(done){
                b = true;
            }
        }
    }
    public String toString(){
        return sentNode.name + " -" + value + "-> " + recNode.name;
    }
    public static void setUp(){
        Node node = Node.rx.prev.get(0);
        loop = new int[node.prev.size()];
        first = new int[loop.length];
    }
}
class Node{
    String name;
    Node[] connect;
    String[] connectN;
    boolean isFilpFlop;
    boolean isOn;
    boolean lastSent;
    ArrayList<Node> prev;
    public static ArrayList<Node> nodes = new ArrayList<Node>();
    public static Node button;
    public static ArrayList<Process> q = new ArrayList<Process>();
    public static Node rx;
    public Node(String line){
        if(line.charAt(0) == '%'){
            name = line.split(" -")[0];
            name = name.substring(1);
            isFilpFlop = true;
            isOn = false;
        }
        else if(line.charAt(0) == '&'){
            name = line.split(" -")[0];
            name = name.substring(1);
            isFilpFlop = false;
        }
        else{
            name = line.split(" -")[0];
            if(name.equals("button")){
                button = this;
            }
        }
        connectN = line.split("> ")[1].split(", ");
        lastSent = false;
        prev = new ArrayList<Node>();
        nodes.add(this);
    }
    public Node(String n,Node p){
        name = n;
        prev = new ArrayList<Node>();
        prev.add(p);
        connectN = new String[0];
        nodes.add(this);
        if(name.equals("rx")){
            rx = this;
            Process.setUp();
        }
    }
    public void findNode(){
        connect = new Node[connectN.length];
        for(int i = 0; i < connectN.length; i++){
            boolean has = false;
            for(Node j:nodes){
                if(connectN[i].equals(j.name)){
                    connect[i] = j;
                    j.prev.add(this);
                    has = true;
                    break;
                }
            }
            if(!has){
                connect[i] = new Node(connectN[i],this);
            }
        }
    }
    public static void pressButton(){
        q.add(new Process(button,button.connect[0],false));
        while(q.size() != 0){
            Node currNode = q.get(0).recNode;
            if(currNode.name.equals("broadcaster")){
                for(Node i:currNode.connect){
                    q.add(new Process(currNode,i,q.get(0).value));
                }
                currNode.lastSent = q.get(0).value;
            }
            else if(currNode.isFilpFlop && !q.get(0).value){
                currNode.isOn = !currNode.isOn;
                for(Node i:currNode.connect){
                    q.add(new Process(currNode,i,currNode.isOn));
                }
                currNode.lastSent = currNode.isOn;
            }
            else if(!currNode.isFilpFlop){
                boolean hasLow = false;
                for(Node i:currNode.prev){
                    if(!i.lastSent){
                        hasLow = true;
                        break;
                    }
                }
                if(hasLow){
                    for(Node i: currNode.connect){
                        q.add(new Process(currNode,i,true));
                    }
                    currNode.lastSent = true;
                }
                else{
                    for(Node i: currNode.connect){
                        q.add(new Process(currNode,i,false));
                    }
                    currNode.lastSent = false;
                }
            }
            q.remove(0);
        }
    }
    public String toString(){
        String temp = name + ": ";
        for(Node i:connect){
            temp += i.name + " ";
        }
        return temp;
    }
}