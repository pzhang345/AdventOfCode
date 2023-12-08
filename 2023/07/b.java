import java.io.*;
import java.util.*;
class a{
    public static void printArr(int[] arr){
        for(int i:arr){
            System.out.print(i + " ");
        }
        System.out.println();
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
        for(int i = 0; i < lines.length; i++){
            new CardValue(Integer.parseInt(lines[i].split(" ")[1]), lines[i].split(" ")[0]);
        }
        System.out.println(CardValue.highest);
        long answer = 0;
        int count = CardValue.highest.size();
        for(CardValue i:CardValue.highest){
            answer += i.value * count;
            count --;
        }
        System.out.println("Answer: " + answer);
    }
}
class CardValue{
    public int value;
    public int type;
    public int[] cards;
    public int[] sortedCards;
    public static ArrayList<CardValue> highest = new ArrayList<CardValue>();
    public CardValue(int v, String c){
        value = v;
        char[] values = c.toCharArray();
        cards = new int[c.length()];
        for(int i = 0; i < values.length; i++){
            if(values[i] == 'T'){
                cards[i] = 10;
            }
            else if(values[i] == 'J'){
                cards[i] = 1;
            }
            else if(values[i] == 'Q'){
                cards[i] = 12;
            }
            else if(values[i] == 'K'){
                cards[i] = 13;
            }
            else if(values[i] == 'A'){
                cards[i] = 14;
            }
            else{
                cards[i] = Integer.parseInt("" + values[i]);
            }
            System.out.println(cards[i]);
        }
        sortedCards = sortCards(cards);
        changeType();
    }
    public void changeType(){
        ArrayList<Integer> inRowArr = new ArrayList<Integer>();
        int previous = 0;
        int inRow = 0;
        int num1s = 0;
        for(int i = 0; i < sortedCards.length; i++){
            try{
                if(sortedCards[i] == 1){
                    num1s ++;
                }
                else if(sortedCards[i] != sortedCards[i-1]){
                    inRowArr.add(inRow);
                    inRow = 0;
                }
            }catch(Exception e){}
            if(sortedCards[i] != 1){
                inRow ++;
            }
        }
        if(inRow != 0){
            inRowArr.add(inRow);
        }
        Collections.sort(inRowArr);
        inRowArr.remove(new Integer(0));
        if(inRowArr.size() != 0){
            int max = inRowArr.get(inRowArr.size() - 1) + num1s;
            inRowArr.remove(inRowArr.size() - 1);
            inRowArr.add(max);
        }
        else{
            inRowArr.add(num1s);
        }
         System.out.println(inRowArr);
        if(inRowArr.get(0) == 5){
            type = 0; //5 of a kind
        }
        else if(inRowArr.get(0) == 1 && inRowArr.get(1) == 4){
            type = 1; // 4 of a kind
        }
        else if(inRowArr.get(0) == 2 && inRowArr.get(1) == 3){
            type = 2; // full house
        }
        else if(inRowArr.get(0) == 1 && inRowArr.get(1) == 1 && inRowArr.get(2) == 3){
            type = 3; // three of a kind
        }
        else if(inRowArr.get(0) == 1 && inRowArr.get(1) == 2 && inRowArr.get(2) == 2){
            type = 4; // two pairs
        }
        else if(inRowArr.get(0) == 1 && inRowArr.get(1) == 1 && inRowArr.get(2) == 1 && inRowArr.get(3) == 2){
            type = 5; // two pairs
        }
        else{
            type = 6;
        }
        boolean hasChanged = false;
        for(int i = 0; i < highest.size(); i++){
            if(compareTo(highest.get(i)) == -1){
                highest.add(i,this);
                hasChanged = true;
                break;
            }
        }
        if(!hasChanged){
            highest.add(this);
        }
    }
    public int[] sortCards(int[] arr){
        ArrayList<Integer> arrS = new ArrayList<Integer>();
        for(int i = 0; i < arr.length; i++){
            arrS.add(arr[i]);
        }
        Collections.sort(arrS);
        int[] arrSorted = new int[arr.length];
        for(int i = 0; i < arrS.size(); i++){
            arrSorted[i] = arrS.get(i);
        }
        return arrSorted;
    }
    public int compareTo(CardValue other){
        if(other.type > type){
            return -1;
        }
        else if(other.type < type){
            return 1;
        }
        else{
            for(int i = 0; i < cards.length; i++){
                if(other.cards[i] < cards[i]){
                    return -1;
                }
                else if(other.cards[i] > cards[i]){
                    return 1;
                }
            }
        }
        return 0;
    }
    public String toString(){
        String temp = "";
        for(int i = 0; i < sortedCards.length; i++){
            temp += cards[i] + " ";
        }
        temp += "|" + value;
        return temp; 
    }
}