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
        /*
        Nums test1 = new Nums("[[[[4,3],4],4],[7,[[8,4],9]]]", null);
        Nums test2 = new Nums("[1,1]", null);
        Nums test3 = test1.add(test2);
        //Nums.top = test1;
        //test1.format(0,false);
        System.out.println(test3);
        //System.out.println(test1.findNums(false,false));
        */
        Nums total = new Nums(lines[0],null);
        for(String i:lines){
            if(i != lines[0]){
                total = total.add(new Nums(i,null));
                System.out.println(total);
            }
        }
        System.out.println("Answer: " + total.findSum());
    }
}
class NumsInt{
    public Nums num;
    public int pos;
    public NumsInt(Nums n, int p){
        num = n;
        pos = p;
    }
    public String toString(){
        return num + " " + pos;
    }
}
class Nums{
    Nums[] subArr;
    int [] values;
    Nums parent;
    public static Nums top;
    public Nums(String str, Nums p){
        int count = 0;
        int index = -1;
        for(int i = 1; i<str.length() - 1; i++){
            if(str.charAt(i) == ',' && count == 0){
                index = i;
                break;
            }
            else if(str.charAt(i) == '['){
                count ++;
            }
            else if(str.charAt(i) == ']'){
                count --;
            }
        }
        subArr = new Nums[2];
        values = new int[2];
        if(str.charAt(1) == '['){
            subArr[0] = new Nums(str.substring(1,index),this);
            values[0] = -1;
        }
        else{
            values[0] = Integer.parseInt(str.substring(1,index));
        }
        if(str.charAt(index + 1) == '['){
            subArr[1] = new Nums(str.substring(index + 1,str.length()-1),this);
            values[1] = -1;
        }
        else{
            values[1] = Integer.parseInt(str.substring(index + 1,str.length()-1));
        }
        parent = p;
    }
    public Nums(Nums num1,Nums num2){
        subArr = new Nums[2];
        values = new int[2];
        subArr[0] = num1;
        subArr[1] = num2;
        num1.parent = this;
        num2.parent = this;
    }
    public Nums(int num1,int num2){
        subArr = new Nums[2];
        values = new int[2];
        values[0] = num1;
        values[1] = num2;
    }
    public NumsInt findNums(boolean foundParent,boolean isLeft){
        int direction = 0;
        if(!isLeft){
            direction = 1;
        } 
        //System.out.println(this);
        if(!foundParent){
            if(parent == null){
                return null;
            }
            if(parent.subArr[1 - direction] == this){
                if(parent.subArr[direction] != null){
                    //System.out.println("a");
                    return parent.subArr[direction].findNums(true,isLeft);
                }
                else{
                    //System.out.println("b");
                    return new NumsInt(parent,direction);
                }
            }
            else{
                return parent.findNums(false,isLeft);
            }
        }
        else{
            if(subArr[1 - direction] != null){
                return subArr[1 - direction].findNums(foundParent,isLeft);
            }
            else{
                return new NumsInt(this, 1 - direction);
            }
        }
    }
    public Nums add(Nums other){
        Nums n = new Nums(this,other);
        Nums.top = n;
        //System.out.println(n);
        while(parent.explode(0) || parent.split()){
            System.out.println("======================================");
        }
        return n;
    }
    public boolean explode(int depth){
        depth ++;
        boolean hasChanged = false;
        for(int i = 0; i < subArr.length; i++){
            if(subArr[i] != null){
                hasChanged = hasChanged || subArr[i].explode(depth);
            }
            else if(depth > 4  && !hasChanged){
                System.out.println(this + " " + "a");
                System.out.println(top);
                NumsInt left = findNums(false, true);
                NumsInt right = findNums(false, false);
                //System.out.println("" + depth + " " + this);
                //System.out.println(left + " " + right);
                if(left != null){
                    left.num.values[left.pos] += values[0];
                }
                if(right != null){
                    right.num.values[right.pos] += values[1];
                }
                if(this == parent.subArr[0]){
                    parent.subArr[0] = null;
                    parent.values[0] = 0;
                }
                else{
                    parent.subArr[1] = null;
                    parent.values[1] = 0;
                }
                System.out.println(top);
                System.out.println();
                return true;
            }
        }
        return hasChanged;
    }
    public boolean split(){
        boolean hasChanged = false;
        for(int i = 0; i < subArr.length; i++){
            if(subArr[i] != null){
                hasChanged = hasChanged || subArr[i].split();
            }
            else if(values[i] >= 10  && !hasChanged){
                System.out.println("b " + this);
                System.out.println(this.parent);
                subArr[i] = new Nums(values[i]/2, values[i]/2 + values[i]%2);
                subArr[i].parent = this;
                values[i] = -1;
                System.out.println(this.parent);
                System.out.println();
                return true;
            }
        }
        return hasChanged;
    }
    public int findSum(){
        if(subArr[0] == null && subArr[1] == null){
            return 3 * values[0] + 2 * values[1];
        }
        else if(subArr[0] == null && subArr[1] != null){
            return 3 * values[0] + 2 * subArr[1].findSum();
        }
        else if(subArr[0] != null && subArr[1] == null){
            return 3 * subArr[0].findSum() + 2 * values[1];
        }
        return 3 * subArr[0].findSum() + 2 * subArr[1].findSum();
    }
    public String toString(){
        String str = "[";
        if(subArr[0] != null){
        str += subArr[0];
        }
        else{
            str += values[0];
        }
        str += ",";
        if(subArr[1] != null){
        str += subArr[1];
        }
        else{
            str += values[1];
        }
        str += "]";
        return str;
    }
}


