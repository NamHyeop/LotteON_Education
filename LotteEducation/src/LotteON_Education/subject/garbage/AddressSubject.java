package LotteON_Education.subject.garbage;

import java.io.*;
import java.util.Scanner;

public class AddressSubject {
    /**
     * 주소록
     * column : 이름, 나이, 전화번호, 주소, 메모(비고)
     * 2차원배열 : row = 100;
     * 데이터 파일에서 불러오기
     *
     * menu
     * 1.추가
     * 2.삭제
     * 3.검색
     * 4.수정
     * 5.모든 데이터 출력
     * 6.데이터 저장
     */
    static int MAX = 100;
    static String arr[];
    static int cmd;
    static Scanner sc = new Scanner(System.in);
    static File newFile = new File("/Users/namhyeop/Desktop/testFile.txt");

    static void input(){
        arr = new String[MAX];
    }
    public static void main(String[] args) throws IOException {
        input();
        addCmd();
    }

    private static void addCmd() throws IOException {
        cmd = sc.nextInt();
        if(cmd == 1) addFunction();
        if(cmd == 2) deleteFunction();
        if(cmd == 3) searchFunction();
        if(cmd == 4) updateFunction();
        if(cmd == 7) allSentencePrint();
        if(cmd == 6) saveFile();
        if(cmd == 1) exitProcess();
    }

    private static void exitProcess() {
        return ;
    }

    private static void saveFile() {
        return ;
    }

    private static void updateFunction() {
    }

    private static void allSentencePrint() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(newFile));
        String str = "null";
        while((str= br.readLine()) != null){
            System.out.println(str);
        }
        br.close();
    }

    private static void searchFunction() {
    }

    private static void deleteFunction() throws IOException {
        newFile.delete();
    }

    private static void addFunction() throws IOException {
        String str = sc.nextLine();
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(newFile)));
        printWriter.write(str);
        printWriter.close();
    }
}
