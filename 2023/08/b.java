import java.io.*;
import java.util.*;
class b{
    public static boolean isFinished(NodeZ[] arr){
        for(int i = 1; i < arr.length; i++){
            if(arr[i].value != arr[i-1].value){
                return false;
            }
        }
        return true;
    }
    public static int getLowIndex(NodeZ[] arr){
        long min = arr[0].value;
        int index = 0;
        for(int i = 1; i < arr.length; i++){
            if(arr[i].value < min){
                min = arr[i].value;
                index = i;
            }
        }
        return index;
    }
    public static void printArr(int[] arr){
        System.out.print("(");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
            if(i != arr.length - 1){
                System.out.print(",");
            }
        }
        System.out.println(")");
    }
    public static boolean isFinished(ArrayList<Node> nodes){
        for(Node i: nodes){
            if(i.name.charAt(2) != 'Z'){
                return false;
            }
        }
        return true;
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
        ArrayList<Node> node = new ArrayList<Node>();
        for(int i = 2; i < lines.length; i++){
            String name = lines[i].split(" ")[0];
            Node tempNode = new Node(name);
            //System.out.println(name);
            if(name.charAt(2) == 'A'){
                node.add(tempNode);
            }
        }
        for(int i = 2; i < lines.length; i++){
            String left = lines[i].split("\\(")[1].substring(0,3);
            String right = lines[i].split(", ")[1].substring(0,3);
            Node.nodes.get(i-2).add(left,right);
        }
        System.out.println(Node.nodes);
        NodeZ[] nums = new NodeZ[node.size()];
        for(int i = 0; i < nums.length; i++){
            nums[i] = node.get(i).getRepeat(lines[0]);
            System.out.println(nums[i]);
        }
        long count = 0;
        while(!isFinished(nums)){
            int index = getLowIndex(nums);
            //System.out.println(nums[index]);
            nums[index].next();
            if(nums[index].value > count + 1000000000000L){
                count = nums[index].value;
                System.out.println(nums[index]);
            }
        }
        System.out.println("Answer: " + nums[0].value);
    }
}
class NodeZ{
    int start;
    int range;
    int[] z;
    long value;
    int index;
    public NodeZ(int s,int e, ArrayList<Integer> z1){
        start = s;
        range = e - start;
        z = new int[z1.size()];
        for(int i = 0; i < z.length; i++){
            z[i] = z1.get(i) - start;
        }
        value = start + z[0];
        index = 0;
    }
    public String toString(){
        String temp = "" + start + " " + range + " z:";
        for(int i:z){
            temp += i + " ";
        }
        return temp + " value: " + value;
    }
    public void next(){
        value -= z[index];
        index ++;
        if(index >= z.length){
            index = 0;
            value += range;
        }
        value += z[index];
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
    public NodeZ getRepeat(String seq){
        ArrayList<Node> repeats = new ArrayList<Node>();
        char[] instructions = seq.toCharArray();
        Node node = this;
        int count = 0;
        while(!repeats.contains(node)){
            repeats.add(node);
            for(int i = 0; i < instructions.length; i++){
                if(instructions[i] == 'L'){
                    node = node.left;
                }
                else if(instructions[i] == 'R'){
                    node = node.right;
                }
            }
            count ++;
        }
        System.out.println("START: " + node + " " + repeats.indexOf(node) + " " + count);
        ArrayList<Integer> z = new ArrayList<Integer>();
        int start = repeats.indexOf(node);
        for(int i = start; i < count; i++){
            for(int j = 0; j < instructions.length; j++){
                if(node.name.charAt(2) == 'Z'){
                    z.add(i * instructions.length + j);
                    System.out.println((i + start) + " " + node);
                }
                if(instructions[j] == 'L'){
                    node = node.left;
                }
                else if(instructions[j] == 'R'){
                    node = node.right;
                }
            }
        }
        System.out.println("END: " + node);
        return new NodeZ(start * instructions.length,count * instructions.length, z);
    }
}