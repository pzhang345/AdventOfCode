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
        for(int i = 0; i <  1000; i++){
            Node.pressButton();
            //System.out.println();
        }
        System.out.println("Answer:" + (long)(Process.high*Process.low));
    } 
}
class Process{
    Node sentNode;
    Node recNode;
    boolean value;
    public static long high = 0;
    public static long low = 0;
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
        //System.out.println(this);
    }
    public String toString(){
        return sentNode.name + " -" + value + "-> " + recNode.name;
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