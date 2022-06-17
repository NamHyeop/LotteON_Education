package LotteON_Education.subject.garbage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class test {
    static FastReader sc = new FastReader();
    static StringBuffer sb = new StringBuffer();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static void pro(Integer changeCri){
//        if(changeCri <= calPay){
//            int value = calPay / changeCri;
//            calPay %= changeCri;
//            System.out.println("value = " + value);
//        }
//    }
//
//    /**
//     3210
//     10000
//     */
//    static int calPay;
//    public static void main(String[] args) {
//        int inputPay = sc.nextInt();
//        int currentPay = sc.nextInt();
//        calPay = currentPay - inputPay;
//        System.out.println("calPay = " + calPay);
//        ArrayList<Integer> arr = new ArrayList<>();
//        arr.add(5000); arr.add(1000); arr.add(500); arr.add(100); arr.add(50); arr.add(10);
//        for(Integer i : arr) {
//            pro(i);
//        }
//        System.out.println(" ================================================================ ");
////        int oneValue = 0, twoValue = 0, thirdValue = 0, fourValue = 0, fiveValue = 0, sixValue = 0;
////        if(calPay >= 5000){
////            oneValue = calPay / 5000;
////            calPay %= 5000;
////        }
////        if(calPay >= 1000){
////            twoValue = calPay / 1000;
////            calPay %= 1000;
////        }
////        if(calPay >= 500){
////            thirdValue = calPay / 500;
////            calPay %= 500;
////        }
////        if(calPay >= 100){
////            fourValue = calPay / 100;
////            calPay %= 100;
////        }
////        if(calPay >= 50){
////            fiveValue = calPay / 50;
////            calPay %= 50;
////        }
////        if(calPay >= 10){
////            sixValue = calPay / 10;
////            calPay %= 10;
////        }
////        System.out.println("5000 = " + oneValue);
////        System.out.println("1000 = " + twoValue);
////        System.out.println("500 = " + thirdValue);
////        System.out.println("100 = " + fourValue);
////        System.out.println("50 = " + fiveValue);
////        System.out.println("10 = " + sixValue);
//    }

    static String pro1(int num) {
        String str = "";
        while (num != 0) {
            str += num % 2;
            num /= 2;
        }
        StringBuffer sb = new StringBuffer(str);
        String ret = sb.reverse().toString();
        return ret;
    }

    static int pro2(int num) {
        String str = "";
        int numLenth = 0;
        while (num != 0) {
            num /= 10;
            num %= 10;
            numLenth++;
        }
        int twoUpValue = 2;
        for (int i = 0; i < numLenth; i++) {
            twoUpValue *= 2;
        }

        int posValue = numLenth - 1;
        int ret = 0;
        for (int i = 0; i < numLenth; i++) {
            ret += posValue * twoUpValue;
            twoUpValue >>= 1;
        }
        return ret;
    }

    static String pro3(int num) {
        String str = "";
        String strBase = "Ox";
        while (num != 0) {
            int value = num % 16;
            if (10 <= value) {
                str += (char) ('A' + value - 10);
            } else {
                str += value;
            }
            num /= 16;
        }
//        StringBuffer sb = new StringBuffer(str);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.reverse();
        return strBase + sb;
    }

//    BC
//    16^1 + 12
//    16^0 + 13
    static int calculatePower(int num, int power){
        int answer = 1;
        if(num > 0 && power==0){
            return answer;
        }
        else if(num == 0 && power>=1){
            return 0;
        }
        else{
            for(int i = 1; i<= power; i++){
                answer = answer*num;
            }
            return answer;
        }
    }

    static int make16(int num, int pos){
        return calculatePower(num , pos);
    }

    static int pro4(String str) {
        str = str.substring(2);
        StringBuffer sb = new StringBuffer(str);
        str = sb.reverse().toString();

        int len = str.length();
        int ret = 0;
        for(int i = 0; i < len; i++){
            int first = make16(16, i);
            int posValue;
            if ('A' <= str.charAt(len - i - 1) && str.charAt(len - i -1 ) <= 'Z') {
                posValue = 10 + (str.charAt(i) - 'A');
            } else {
                posValue = str.charAt(i) - '0';
            }
            ret += first * posValue;
        }
        return ret;
    }

    /**
     12
     1100
     12
     0xC
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("번호를 고르세요");
        System.out.println("1번 10진수 입력 => 2진수");
        System.out.println("2번 2진수 입력 => 10진수");
        System.out.println("3번 10진수 입력 => 16진수");
        System.out.println("4번 16진수 입력 => 10진수");
        int cmd; cmd = sc.nextInt();
        if(cmd == 1){
            int num1; num1 = sc.nextInt();
            String answer1 = pro1(num1);
            System.out.println("answer1 = " + answer1);
        }else if(cmd == 2){
            int num; num = sc.nextInt();
            int answer2 = pro2(num);
            System.out.println("answer2 = " + answer2);
        }
        else if(cmd == 3){
            int num; num = sc.nextInt();
            String answer3 = pro3(num);
            System.out.println("answer3 = " + answer3);
        }else if(cmd == 4){
            String num; num = br.readLine();
            int answer4 = pro4(num);
            System.out.println("answer4 = " + answer4);
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }
    }
}
