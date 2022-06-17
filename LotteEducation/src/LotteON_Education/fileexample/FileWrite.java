package LotteON_Education.fileexample;

import java.io.*;

public class FileWrite {
    public static void main(String[] args) throws IOException {

        File newFile = new File("/Users/namhyeop/Desktop/testFile.txt");
        FileWriter fWriter;

        /**
         * 파일 write 예제.
         * 같은 이름일 경우 덮어쓰기 되므로 주의
         */
//        fWriter = new FileWriter(newFile);
//        fWriter.write("woo woo woo hoo woo" + "\n");
//        fWriter.write("yap yap yap" + "\n");
//        fWriter.close();

        /**
         * 추가로 write하기 예제
         */
//        fWriter = new FileWriter(newFile, true);
//        fWriter.write("woo woo woo hoo woo" + "\n");
//        fWriter.write("yap yap yap" + "\n");
//        fWriter.close();

        /**
         * 성능이 더 좋은 PrintWrite 사용하기
         */
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(newFile)));
        pw.println("hi");
        pw.println("hello");
        pw.println("what the");
        pw.close();

    }
}
