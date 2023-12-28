import java.io.File;
import java.util.*;
class b {
  public static void main(String[] args) {
    String str = " ";
    File file = new File("text.txt");
    int maxx = 0;
    int y = -1;
    int[][] tempboard;
    boolean added = false;
    Board board;
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine() && !str.equals("")){
        str = sc.nextLine();
        y++;
        System.out.println(y);
        if(str.length()>maxx){
          maxx = str.length();
        }
      }
      tempboard = new int[y][maxx];
      System.out.println(tempboard.length + " " + tempboard[0].length);
      y = 0;
      sc.close();
      sc = new Scanner(file);
      while (sc.hasNextLine()){
        str = sc.nextLine();
        if(y < tempboard.length){ 
          for(int i = 0; i<tempboard[0].length;i++){
            if(i>=str.length() || str.charAt(i) == ' '){
              tempboard[y][i] = 3;
            }
            else if(str.charAt(i) == '.'){
              tempboard[y][i] = 0;
              if(!added){
                added = true;
                tempboard[y][i] = 1;
              }
            }
            else if(str.charAt(i) == '#'){
              tempboard[y][i] = 2;
            }
            //System.out.print(tempboard[y][i]);
          }
          y++;
        }
      }
      /*for(int[] i:tempboard){
        for(int j: i){
          System.out.print(j + " ");
        }
        System.out.println();
      }*/
      board = new Board(tempboard,str);
      //board = new Board(tempboard,"L2");
      board.move();
      //board.printboard();
      System.out.println(board.x + " " + board.y + " " + board.facing);
      System.out.println("Answer: " + ((board.y+1)*1000 + (board.x+1)*4 + board.facing));
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Board{
  int[][] board;
  int[][] board2;
  int facing;
  String moves;
  String tempmoves;
  int x;
  int y;
  public static int sidesize = 50;
  public static Side[] sides = new Side[6];
  public Board(int[][] b, String m){
    board = b;
    facing = 0;
    moves = m;
    tempmoves = moves;
    board2 = new int[200][150];
    for(int i = 0; i < board.length;i++){
      for(int j = 0; j < board[0].length;j++){
        board2[i][j] = board[i][j];
        if(board[i][j] == 1){
          x = j;
          y = i;
        }
      }
    }
   
    Side.size = sidesize;
    Side.board = this;
    sides[0] = new Side(sidesize*2,0,1);
    sides[1] = new Side(sidesize*1,0,2);
    sides[2] = new Side(sidesize*1,sidesize*1,3);
    sides[3] = new Side(sidesize*1,sidesize*2,4);
    sides[4] = new Side(0,sidesize*2,5);
    sides[5] = new Side(0,sidesize*3,6);
    //udlr
    sides[0].setsides(sides[5],3,sides[2],2,sides[1],2,sides[3],2);
    sides[1].setsides(sides[5],0,sides[2],1,sides[4],0,sides[0],0);
    sides[2].setsides(sides[1],3,sides[3],1,sides[4],1,sides[0],3);
    sides[3].setsides(sides[2],3,sides[5],2,sides[4],2,sides[0],2);
    sides[4].setsides(sides[2],0,sides[5],1,sides[1],0,sides[3],0);
    sides[5].setsides(sides[4],3,sides[0],1,sides[1],1,sides[3],3);
  }
  public void move(){
    int count = 0;
    //int right = 0;
    //int left = 0;
    while(tempmoves.length() > 0){
      //System.out.println(tempmoves.length());
      if(tempmoves.indexOf("R") == 0){
        facing = (facing + 1) % 4;
        tempmoves = tempmoves.substring(1);
        //right ++;
        continue;
      }
      else if (tempmoves.indexOf("L") == 0){
        facing = (facing - 1) % 4;
        if(facing < 0){
          facing += 4;
        }
        tempmoves = tempmoves.substring(1);
        //left++;
        continue;
      }
      if(facing < 0 || facing > 3){
        //System.out.println("a");
      }
      if((tempmoves.indexOf("R")<tempmoves.indexOf("L") && tempmoves.indexOf("R") != -1)||
         (tempmoves.indexOf("L") == -1 && tempmoves.indexOf("R") != -1)){
        count = Integer.parseInt(tempmoves.substring(0,tempmoves.indexOf("R")));
        tempmoves = tempmoves.substring(tempmoves.indexOf("R"));
      }
      else if((tempmoves.indexOf("L") < tempmoves.indexOf("R") && tempmoves.indexOf("L") != -1)||
         (tempmoves.indexOf("R") == -1 && tempmoves.indexOf("L") != -1)){
        count = Integer.parseInt(tempmoves.substring(0,tempmoves.indexOf("L")));
        tempmoves = tempmoves.substring(tempmoves.indexOf("L"));
      }
      else if(tempmoves.indexOf("R") == tempmoves.indexOf("L")){
        count = Integer.parseInt(tempmoves);
        tempmoves = "";
      }
      countloop: for(int i = 0; i<count; i++){
        if(facing == 0){
          if(x == board[0].length - 1 || board[y][x+1] == 3){
            Side.move();
          }
          else if(board[y][x+1] == 0){
            onemove(x+1,y);
          }
          else if(board[y][x+1] == 2){
            break countloop;
          }
        }
        else if(facing == 1){
          if(y == board.length - 1 || board[y+1][x] == 3){
            Side.move();
          }
          else if(board[y+1][x] == 0){
            onemove(x,y+1);
          }
          else if(board[y+1][x] == 2){
            break countloop;
          }
        }
        else if(facing == 2){
          if(x == 0 || board[y][x-1] == 3){
            Side.move();
          }
          else if(board[y][x-1] == 0){
            onemove(x-1,y);
          }
          else if(board[y][x-1] == 2){
            break countloop;
          }
        }
        else if(facing == 3){
          if(y == 0 || board[y-1][x] == 3){
            Side.move();
          }
          else if(board[y-1][x] == 0){
            onemove(x,y-1);
          }
          else if(board[y-1][x] == 2){
            break countloop;
          }
        }
      }
    }
    //System.out.println(left +" " + right);
  }
  public void onemove(int x1,int y1){
    if (board[y1][x1] != 2){
      board[y][x] = 0;
      board[y1][x1] = 1;
      board2[y1][x1] = 1;
      x = x1;
      y = y1;
    }
  }
  public void printboard(){
    for(int[] i:board){
      for(int  j: i){
        if(j == 0){
          System.out.print(".");
        }
        else if(j==1){
          System.out.print("A");
        }
        else if(j == 2){
          System.out.print("#");
        }
      }
    System.out.println();
    }
    System.out.println();
  }
}
class Side{
  int minx;
  int miny;
  Side up;
  int rotup;
  Side down;
  int rotdown;
  Side left;
  int rotleft;
  Side right;
  int rotright;
  int sidenum;
  int[][] contains;
  public static int size = 0;
  public static Board board;
  public Side(int mix,int miy,int id){
    minx = mix;
    miny = miy;
    contains = new int[size][size];
    for(int i = 0; i<size; i++){
      for(int j = 0; j<size; j++){
          contains[i][j] = board.board[i + miny][j + minx];
      }
    }
    sidenum = id;
  }
  public void setsides(Side u,int ru, Side d, int rd, Side l, int rl, Side r, int rr){
    up = u;
    rotup = ru;
    down = d;
    rotdown = rd;
    left = l;
    rotleft = rl;
    right = r;
    rotright = rr;
  }
  public static Side getside(int x, int y){
    for(int i = 0; i<Board.sides.length; i++){
      if(Board.sides[i].minx<=x && x<Board.sides[i].minx + size && Board.sides[i].miny<=y && y<Board.sides[i].miny + size){
        return Board.sides[i];
      }
    }
    return null;
  }
  public static void move(){
    //board.printboard();
    int x = board.x;
    int y = board.y;
    Side current = Side.getside(x,y);
    System.out.print(current.sidenum + " " + x + " " + y + " " + board.facing + "###");
    //System.out.println(board.facing);
    //System.out.println(current.sidenum);
    // 0-right,1-down,2-left,3-up
    if(board.facing == 0){
      if(current.rotright == 0){
        board.onemove(current.right.minx,y%size + current.right.miny);
      }
      else if(current.rotright == 1){
        board.onemove(size-y%size - 1 + current.right.minx,current.right.miny);
      }
      else if(current.rotright == 2){
        board.onemove(current.right.minx + size - 1,size - y%size - 1 + current.right.miny);
      }
      else if(current.rotright == 3){
        board.onemove(y%size + current.right.minx, current.right.miny + size - 1);
      }
      if(current.sidenum != Side.getside(board.x, board.y).sidenum){
        board.facing = current.rotright;
      }
    }
    else if(board.facing == 1){
      if(current.rotdown == 0){
        board.onemove(current.down.minx,size - x%size - 1 + current.down.miny);
      }
      else if(current.rotdown == 1){
        board.onemove(x%size + current.down.minx,current.down.miny);
      }
      else if(current.rotdown == 2){
        board.onemove(current.down.minx + size - 1,x%size + current.down.miny);
      }
      else if(current.rotdown == 3){
        board.onemove(size - x%size - 1 + current.down.minx, current.down.miny + size - 1);
      }
      if(current.sidenum != Side.getside(board.x, board.y).sidenum){
        board.facing = current.rotdown;
      }
    }
    else if(board.facing == 2){
      if(current.rotleft == 0){
        board.onemove(current.left.minx,size  - y%size - 1 + current.left.miny);
      }
      else if(current.rotleft == 1){
        board.onemove(y%size + current.left.minx,current.left.miny);
      }
      else if(current.rotleft == 2){
        board.onemove(current.left.minx + size - 1,y%size + current.left.miny);
      }
      else if(current.rotleft == 3){
        board.onemove(size - y%size - 1 + current.left.minx, current.left.miny + size - 1);
      }
      if(current.sidenum != Side.getside(board.x, board.y).sidenum){
        board.facing = current.rotleft;
      }
    }
    else if(board.facing == 3){
      //System.out.println(current.rotup);
      if(current.rotup == 0){
        board.onemove(current.up.minx,x%size + current.up.miny);
      }
      else if(current.rotup == 1){
        board.onemove(size - x%size - 1 + current.up.minx,current.up.miny);
      }
      else if(current.rotup == 2){
        board.onemove(current.up.minx + size - 1,size - x%size - 1 + current.up.miny);
      }
      else if(current.rotup == 3){
        //System.out.println(current.sidenum + " " + (x%size + current.up.minx)+ " " + (current.up.miny + size - 1));
        board.onemove(x%size + current.up.minx, current.up.miny + size - 1);
      }
      if(current.sidenum != Side.getside(board.x, board.y).sidenum){
        board.facing = current.rotup;
      }
    }
    System.out.println(Side.getside(board.x,board.y).sidenum + " " + board.x + " " + board.y + " " + board.facing);
  }
}