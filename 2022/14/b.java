import java.io.File;
import java.util.*;
class b {
  public static void main(String[] args) {
    String str = "";
    int[][] positions;
    int lowx = 500;
    //int lowy = 500;
    int highx = 500;
    int highy = 0;
    int count = 0;
    Cave cave;
    File file = new File("text.txt");
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        positions = new int[str.split(" -> ").length][2];
        for(int i = 0; i<str.split(" -> ").length;i++){
          for(int j = 0; j<2;j++){
            positions[i][j] = Integer.parseInt(str.split(" -> ")[i].split(",")[j]);
          }
        }
        for (int[] i:positions){
          if(highx<i[0]){
            highx = i[0];
          }
          else if(lowx>i[0]){
            lowx = i[0];
          }
          else if(highy<i[1]){
            highy = i[1];
          }
          //else if(lowy>i[1]){
            //lowy = i[1];
          //}
        }
        System.out.println(highx + " " + lowx + " " + highy + " "/* + lowy*/);
      }
      cave = new Cave(lowx,highx,highy);
      sc.close();
      sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        positions = new int[str.split(" -> ").length][2];
        for(int i = 0; i<str.split(" -> ").length;i++){
          for(int j = 0; j<2;j++){
            positions[i][j] = Integer.parseInt(str.split(" -> ")[i].split(",")[j]);
          }
        }
        System.out.println("a");
        cave.setcave(positions);
        System.out.println("b");
        boolean[][] isoccupied = cave.getisoccupied();
        for(boolean[] i:isoccupied){
          for(boolean j:i){
            if(j){
              System.out.print("#");
            }
            else{
              System.out.print(".");
            }
          }
          System.out.println();
        }
      }
      while(!cave.getisoccupied()[0][cave.getstartx()]){
        cave.dropsand();
        //boolean[][] isoccupied = cave.getisoccupied();
        /*for(boolean[] i:isoccupied){
          for(boolean j:i){
            if(j){
              System.out.print("#");
            }
            else{
              System.out.print(".");
            }
          }
          System.out.println();
        }*/
        count ++;
        //System.out.println(count);
      }
      System.out.println("Answer: " + count);
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Cave{
  boolean[][] isoccupied;
  int startx;
  int conversion;
  int x;
  int y;
  public Cave(int x1, int x2 , int y){
    isoccupied = new boolean[y+1+2][x2-x1+1];
    for(int i = 0; i<isoccupied.length; i++){
      for(int j = 0; j<isoccupied[0].length; j++){
        if(i == isoccupied.length-1){
          isoccupied[i][j] = true;
        }
        else{
          isoccupied[i][j] = false;
        }
      }
    }
    conversion = x1;
    startx = 500-x1;
  }
  public void setcave(int[][] pos){
    for(int i = 0; i<pos.length-1; i++){
      if(pos[i][0] == pos[i+1][0]){
        for(int y1 = 0; y1<(Math.abs(pos[i][1]-pos[i+1][1])+1);y1++){
          if(pos[i][1]<pos[i+1][1]){
            isoccupied[y1+pos[i][1]][pos[i][0]-conversion] = true;
          }
          else if(pos[i][1]>pos[i+1][1]){
            isoccupied[y1+pos[i+1][1]][pos[i][0]-conversion] = true;
          }
        }
      }
      else{
        for(int x1 = 0; x1<(Math.abs(pos[i][0]-pos[i+1][0])+1);x1++){
          if(pos[i][0]<pos[i+1][0]){
            isoccupied[pos[i][1]][x1 + pos[i][0]-conversion] = true;
          }
          else if(pos[i][0]>pos[i+1][0]){
            isoccupied[pos[i][1]][x1+pos[i+1][0]-conversion] = true;
          }
        }
      }
    }
  }
  public void dropsand(){
    x = startx;
    y = 0;
    while(true){
      if(x == 0 && isoccupied[y+1][x]){
        boolean[][] temp = new boolean[isoccupied.length][isoccupied[0].length+1];
        for(int i = 0; i< temp.length; i++){
          for(int j = 0; j<temp[0].length;j++){
            if(j == 0){
              if(i == temp.length-1){
                temp[i][j] = true;
              }
              else{
                temp[i][j] = false;
              }
            }
            else{
              temp[i][j] = isoccupied[i][j-1];
            }
          }
        }
        isoccupied = temp;
        x++;
        startx ++;
        conversion --;
      }
      else if(x == isoccupied[0].length-1 && isoccupied[y+1][x] && isoccupied[y+1][x-1]){
        boolean[][] temp = new boolean[isoccupied.length][isoccupied[0].length+1];
        for(int i = 0; i< temp.length; i++){
          for(int j = 0; j<temp[0].length;j++){
            if(j == temp[0].length-1){
              if(i == temp.length-1){
                temp[i][j] = true;
              }
              else{
                temp[i][j] = false;
              }
            }
            else{
              temp[i][j] = isoccupied[i][j];
            }
          }
        }
        isoccupied = temp;
      }
      if(!isoccupied[y+1][x]){
        y++;
      }
      else if(!isoccupied[y+1][x-1]){
        x--;
        y++;
      }
      else if(!isoccupied[y+1][x+1]){
        x++;
        y++;
      }
      else{
        isoccupied[y][x] = true;
        break;
      }
    }
  }
  public boolean[][] getisoccupied(){
    return isoccupied;
  }
  public int getstartx(){
    return startx;
  }
}