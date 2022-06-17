package LotteON_Education.subject.garbage;

import java.util.Scanner;

public class test2 {

    static String num;
    static Scanner sc = new Scanner(System.in);

    static void input(){
        num = sc.nextLine();
    }

    static boolean pro(){
        for(int i = 0; i < num.length(); i++){
            if(!('0' <= num.charAt(i) && num.charAt(i) <= '9')){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        input();
        boolean ret = pro();
        if(ret) {
            System.out.println("숫자로 되어 있습니다.");
        }else{
            System.out.println("숫자가 아닙니다.");
        }
    }
}
