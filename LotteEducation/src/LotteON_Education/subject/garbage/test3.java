package LotteON_Education.subject.garbage;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class test3 {
    static ArrayList<Integer> sol_arr;
    static ArrayList<Integer> user_arr;
    static Scanner sc = new Scanner(System.in);
    static boolean visited[];
    static int lifeTime;

    static int make_rand() {
        Random rd = new Random();
        while (true) {
            int makeNum = (int) (rd.nextInt(10) + 1);
            if (!visited[makeNum]) {
                System.out.println("makeNum = " + makeNum);
                visited[makeNum] = true;
                return makeNum;
            }
        }
    }

    static void input(){
        lifeTime = 10;
        sol_arr = new ArrayList<>();
        user_arr = new ArrayList<>();
        visited = new boolean[11];
        for(int i = 0; i < 3; i++){
            sol_arr.add(make_rand());
        }
        System.out.println("sol_arr = " + sol_arr);
    }

    private static void userInput() {
        user_arr.clear();
        for(int i = 0; i < 3; i++){
            int num = sc.nextInt();
            user_arr.add(num);
        }
    }

    static boolean pro(){
        int strikeNum = 0, ballNum = 0;
        for(int i = 0; i < 3; i++){
            if(sol_arr.get(i) == user_arr.get(i)){
                strikeNum++;
            }else{
                if(sol_arr.contains(user_arr.get(i))){
                    ballNum++;
                }
            }
        }
        if(strikeNum == 3){
            System.out.println("정답입니다.");
            return true;
        }else{
            if(strikeNum != 0){
                System.out.println(strikeNum + " strike");
            }
            if(ballNum != 0){
                System.out.println(ballNum + " ball");
            }
        }
        return false;
    }

    public static void main(String[] args) {
        input();
        while(lifeTime-- > 0){
            userInput();
            boolean flag = pro();
            if(flag){
                break;
            }
        }
    }
}
