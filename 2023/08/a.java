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
        char[] instructions = lines[0].toCharArray();
        Node node = null;
        for(int i = 2; i < lines.length; i++){
            String name = lines[i].split(" ")[0];
            Node tempNode = new Node(name);
            //System.out.println(name);
            if(name.equals("AAA")){
                node = tempNode;
            }
        }
        for(int i = 2; i < lines.length; i++){
            String left = lines[i].split("\\(")[1].substring(0,3);
            String right = lines[i].split(", ")[1].substring(0,3);
            Node.nodes.get(i-2).add(left,right);
        }
        System.out.println(Node.nodes);
        int count = 0;
        int index = 0;
        while(!node.name.equals("ZZZ")){
            if(instructions[index] == 'L'){
                node = node.left;
            }
            else if(instructions[index] == 'R'){
                node = node.right;
            }
            index ++;
            if(index == instructions.length){
                index = 0;
            }
            count ++;
        }
        System.out.println("Answer: " + count);
    }
}
class Node{
    String name;
    Node left;
    Node right;
    public static ArrayList<Node> nodes = new ArrayList<Node>();
    public Node(String n){
        name = n;
        nodes.add(this);
    }
    public void add(String l, String r){
        for(Node i: nodes){
            if(i.name.equals(l)){
                left = i;
            }
            if(i.name.equals(r)){
                right = i;
            }
        }
    }
    public String toString(){
        String l = "";
        String r = "";
        if(left != null){
            l = left.name;
        }
        if(right != null){
            r = right.name;
        }
        return name + "(" + l + "," + r + ")";
    }
}