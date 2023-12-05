import java.io.File;
import java.util.*;
class a {
  public static String adjustT(int Hx,int Hy,int Tx,int Ty){
    int tempx = 0;
    int tempy = 0;
    if (Math.abs(Hx - Tx) == 2 && Hy == Ty){
      tempx = Math.abs(Hx-Tx)/(Hx-Tx) + Tx;
      return tempx + "#" + Ty;
    }
    if (Math.abs(Hx - Tx) == 2 && Math.abs(Hy-Ty) == 1){
      tempx = Math.abs(Hx-Tx)/(Hx-Tx) + Tx;
      tempy = Hy;
      return tempx + "#" + tempy;
    }
    if (Math.abs(Hy - Ty) == 2 && Hx == Tx){
      tempy = Math.abs(Hy-Ty)/(Hy-Ty) + Ty;
      return Tx + "#" + tempy;
    }
    if (Math.abs(Hy - Ty) == 2 && Math.abs(Hx-Tx) == 1){
      tempx = Hx;
      tempy = Math.abs(Hy-Ty)/(Hy-Ty) + Ty;
      return tempx + "#" + tempy;
    }
    return " ";
    //return Tx + "#" + Ty;
  }
  public static void main(String[] args) {
    String str = "";
    int Hx=0;
    int Hy=0;
    int Tx=0;
    int Ty=0;
    ArrayList<String> Tpos = new ArrayList<String>();
    Tpos.add("0#0");
    File file = new File("moves.txt");
    int count = 0;
    try{
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()){
        count ++;
        System.out.println(count);
        str = sc.nextLine();
        for(int i = 0; i<Integer.parseInt(str.substring(2));i++){
          if(str.charAt(0) == 'U'){
            Hy+=1;
          }
          else if(str.charAt(0) == 'D'){
            Hy-=1;
          }
          else if(str.charAt(0) == 'L'){
            Hx-=1;
          }
          else if(str.charAt(0) == 'R'){
            Hx+=1;
          }
          if(Math.abs(Hx-Tx)>=2||Math.abs(Hy-Ty)>=2){
            String temp =adjustT(Hx,Hy,Tx,Ty);
            Tx = Integer.parseInt(temp.split("#")[0]);
            Ty = Integer.parseInt(temp.split("#")[1]);
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
        System.out.println(Hx + " " + Hy + " "+ Tx + " " + Ty);
      }
      System.out.println("Answer: " + Tpos.size());
      sc.close();
    }catch(Exception e){System.out.println(e);}
  }
}