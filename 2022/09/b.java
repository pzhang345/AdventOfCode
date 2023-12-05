import java.io.File;
import java.util.*;
class b {
  public static String adjustT(int Hx,int Hy,int Tx,int Ty){
    int tempx = 0;
    int tempy = 0;
    if (Math.abs(Hx - Tx) == 2 && Hy == Ty){
      tempx = Math.abs(Hx-Tx)/(Hx-Tx) + Tx;
      return tempx + "#" + Ty;
    }
    else if (Math.abs(Hx - Tx) == 2 && Math.abs(Hy-Ty) == 1){
      tempx = Math.abs(Hx-Tx)/(Hx-Tx) + Tx;
      tempy = Hy;
      return tempx + "#" + tempy;
    }
    else if (Math.abs(Hy - Ty) == 2 && Hx == Tx){
      tempy = Math.abs(Hy-Ty)/(Hy-Ty) + Ty;
      return Tx + "#" + tempy;
    }
    else if (Math.abs(Hy - Ty) == 2 && Math.abs(Hx-Tx) == 1){
      tempx = Hx;
      tempy = Math.abs(Hy-Ty)/(Hy-Ty) + Ty;
      return tempx + "#" + tempy;
    }
    else{
      tempx = Math.abs(Hx-Tx)/(Hx-Tx) + Tx;
      tempy = Math.abs(Hy-Ty)/(Hy-Ty) + Ty;
      return tempx + "#"+ tempy;
    }
    //System.out.println(Hx+" "+Hy+" "+Tx+" "+Ty);
    //return " ";
    //return Tx + "#" + Ty;
  }
  public static void main(String[] args) {
    String str = "";
    int[][] snake = new int[10][2];
    for(int i = 0; i<snake.length;i++){
      for(int j = 0; j<snake[0].length;j++){
        snake[i][j] = 0;
      }
    }
    ArrayList<String> Tpos = new ArrayList<String>();
    Tpos.add("0#0");
    File file = new File("moves.txt");
    int count = 0;
    String temp = "";
    boolean change = false;
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        count ++;
        System.out.println(count);
        str = sc.nextLine();
        for(int i = 0; i<Integer.parseInt(str.substring(2));i++){
          if(str.charAt(0) == 'U'){
            snake[0][1]+=1;
          }
          else if(str.charAt(0) == 'D'){
            snake[0][1]-=1;
          }
          else if(str.charAt(0) == 'L'){
            snake[0][0]-=1;
          }
          else if(str.charAt(0) == 'R'){
            snake[0][0]+=1;
          }
          change = false;
          for(int x = 0; x<snake.length-1;x++){
            if(Math.abs(snake[x][0]-snake[x+1][0])>=2||Math.abs(snake[x][1]-snake[x+1][1])>=2){
              temp =adjustT(snake[x][0],snake[x][1],snake[x+1][0],snake[x+1][1]);
              snake[x+1][0] = Integer.parseInt(temp.split("#")[0]);
              snake[x+1][1] = Integer.parseInt(temp.split("#")[1]);
              if(x==8){
                change = true;
              }
            }
          }
          if (change){
            boolean isin = false;
            for(int j = 0; j<Tpos.size();j++){
              if(Tpos.get(j).equals(temp)){
                isin = true;
              }
            }
            if(!isin){
              Tpos.add(temp);
            }
          }
        }
        //if(count == 200){
         //   break;
         // }
      }
      System.out.println("Answer: " + Tpos.size());
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}