import java.io.File;
import java.util.*;
class a {
  public static void main(String[] args) {
    String str = " ";
    File file = new File("moves.txt");
    int maxx = 0;
    int y = 0;
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
      board.move();
      System.out.println(board.x + " " + board.y + " " + board.facing);
      System.out.println("Answer: " + ((board.y+1)*1000 + (board.x+1)*4 + board.facing));
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}
class Board{
  int[][] board;
  int facing;
  String moves;
  String tempmoves;
  int x;
  int y;
  public Board(int[][] b, String m){
    board = b;
    facing = 0;
    moves = m;
    tempmoves = moves;
    for(int i = 0; i < board.length;i++){
      for(int j = 0; j < board[0].length;j++){
        if(board[i][j] == 1){
          x = j;
          y = i;
        }
      }
    }
  }
  public void move(){
    int count = 0;
    int right = 0;
    int left = 0;
    while(tempmoves.length() > 0){
      //System.out.println(tempmoves.length());
      if(tempmoves.indexOf("R") == 0){
        facing = (facing + 1) % 4;
        tempmoves = tempmoves.substring(1);
        right ++;
        continue;
      }
      else if (tempmoves.indexOf("L") == 0){
        facing = (facing - 1) % 4;
        if(facing < 0){
          facing += 4;
        }
        tempmoves = tempmoves.substring(1);
        left++;
        continue;
      }
      if(facing < 0 || facing > 3){
        System.out.println("a");
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
      if(facing == 0){
        countloop: for(int i = 0; i<count; i++){
          if(x == board[0].length - 1 || board[y][x+1] == 3){
            for(int j = 0; j<board[0].length;j++){
              if(board[y][j]==0){
                onemove(j,y);
                break;
              }
              if(board[y][j] == 2){
                break countloop;
              }
            }
          }
          else if(board[y][x+1] == 0){
            onemove(x+1,y);
          }
          else if(board[y][x+1] == 2){
            break countloop;
          }
        }
      }
      else if(facing == 1){
        countloop: for(int i = 0; i<count; i++){
          if(x == board.length -1 || board[y+1][x] == 3){
            for(int j = 0; j<board.length;j++){
              if(board[j][x]==0){
                onemove(x,j);
                break;
              }
              if(board[j][x] == 2){
                break countloop;
              }
            }
          }
          else if(board[y+1][x] == 0){
            onemove(x,y+1);
          }
          else if(board[y+1][x] == 2){
            break countloop;
          }
        }
      }
      else if(facing == 2){
        countloop: for(int i = 0; i<count; i++){
          if(x == 0 || board[y][x-1] == 3){
            //System.out.println("c");
            for(int j = board[0].length-1; j>0;j--){
              System.out.println(board[y][j]);
              if(board[y][j]==0){
                System.out.println("a");
                onemove(j,y);
                break;
              }
              if(board[y][j] == 2){
                System.out.println("b");
                break countloop;
              }
            }
          }
          else if(board[y][x-1] == 0){
            onemove(x-1,y);
          }
          else if(board[y][x-1] == 2){
            break countloop;
          }
        }
      }
      else if(facing == 3){
        countloop: for(int i = 0; i<count; i++){
          if(y == 0 || board[y-1][x] == 3){
            for(int j = board.length - 1; j>0;j--){
              if(board[j][x]==0){
                onemove(x,j);
                break;
              }
              if(board[j][x] == 2){
                break countloop;
              }
            }
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
    System.out.println(left +" " + right);
  }
  public void onemove(int x1,int y1){
    board[y][x] = 0;
    board[y1][x1] = 1;
    x = x1;
    y = y1;
  }
}