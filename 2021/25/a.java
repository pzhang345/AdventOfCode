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
        //System.out.println(lines[0]);
        Floor floor = new Floor(lines);
        //floor.printMap();
        while(floor.move()){
            //floor.printMap();
            System.out.println(floor.count);
        }
        //floor.move();
        //System.out.println();
        //floor.printMap();
        System.out.println("Answer: " + floor.count);
    }
}
class Floor{
    int count;
    char[][] arr;
    public Floor(String[] strs){
        arr = new char[strs.length][strs[0].length()];
        count = 0;
        for(int i = 0; i< strs.length; i++){
            arr[i] = toCharArr(strs[i]);
        }
    }
    public char[] toCharArr(String str){
        char[] arr = new char[str.length()];
        for(int i = 0; i < str.length(); i++){
            arr[i] = str.charAt(i);
        }
        return arr;
    }
    public char[][] copyArr(){
        char[][] arrCopy = new char[arr.length][arr[0].length];
        for(int i = 0; i< arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                arrCopy[i][j] = arr[i][j];
            }
        }
        return arrCopy;
    }
    public boolean move(){
        char[][] arrCopy = copyArr();
        boolean hasChanged = false;
        for(int i = 0; i< arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(arr[i][j] == '>'){
                    try{
                        if(arr[i][j+1] == '.'){
                            arrCopy[i][j+1] = '>';
                            arrCopy[i][j] = '.';
                            hasChanged = true;
                        }
                    } catch(IndexOutOfBoundsException e){
                        if(arr[i][0] == '.'){
                            arrCopy[i][0] = '>';
                            arrCopy[i][j] = '.';
                            hasChanged = true;
                        }
                    }
                }
            }
        }
        arr = arrCopy;
        arrCopy = copyArr();
        for(int i = 0; i< arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(arr[i][j] == 'v'){
                    try{
                        if(arr[i+1][j] == '.'){
                            arrCopy[i+1][j] = 'v';
                            arrCopy[i][j] = '.';
                            hasChanged = true;
                        }
                    } catch(IndexOutOfBoundsException e){
                        //System.out.println(e);
                        if(arr[0][j] == '.'){
                            arrCopy[0][j] = 'v';
                            arrCopy[i][j] = '.';
                            hasChanged = true;
                        }
                    }
                }
            }
        }
        arr = arrCopy;
        count ++;
        return hasChanged;
    }
    public void printMap(){
        for(char[] i:arr){
            for(char j:i){
                System.out.print(j);
            }
            System.out.println();
        }
    }
}


