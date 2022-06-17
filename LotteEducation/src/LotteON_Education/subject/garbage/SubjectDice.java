package LotteON_Education.subject.garbage;

import java.util.Random;
import java.util.Scanner;

class Dice{
    Random rd = new Random();
    public int makeDiceNum(){
        int num = rd.nextInt(6) + 1;
        return num;
    }
}

public class SubjectDice {
    static int coin = 20;
    static Scanner sc = new Scanner(System.in);

    static void playGame(){
        Dice firstDice = new Dice();
        Dice secondDice = new Dice();
        int firstDiceNum = firstDice.makeDiceNum();
        int secondDiceNum = secondDice.makeDiceNum();
        System.out.println("firstDiceNum = " + firstDiceNum);
        System.out.println("secondDiceNum = " + secondDiceNum);

        boolean solFlag = false;
        int solutionNum = firstDiceNum + secondDiceNum;
        while(true){
            int userInput = sc.nextInt();
            if(userInput == 2 || userInput == 12) continue;
            if(userInput == solutionNum){
                if(solutionNum == 3 || solutionNum == 11) coin *= 18;
                if(solutionNum == 4 || solutionNum == 10) coin *= 12;
                if(solutionNum == 5 || solutionNum == 9) coin *= 9;
                if(solutionNum == 6 || solutionNum == 8) coin *= 7;
                if(solutionNum == 7) coin *= 6;
                solFlag = true;
            }
            break;
        }

        if (solFlag) {
            System.out.println("정답을 맞췄습니다");
        } else {
            System.out.println("정답이 아닙니다");
        }
        System.out.println("coin = " + coin);
    }

    public static void main(String[] args) {
        playGame();
    }
}
