package LotteON_Education.fileexample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {
    public static void main(String[] args) throws IOException {
        File newFile = new File("/Users/namhyeop/Desktop/testFile.txt");

        /**
         * 한 글자씩 읽기
         */
        FileReader fr = new FileReader(newFile);
        int ch;
        while((ch = fr.read())!= -1){
            System.out.println((char)ch);
        }
        fr.close();
        /**
         * 한 문장으로 읽기
         */
        BufferedReader br = new BufferedReader(new FileReader(newFile));
        String str = "";
        while((str = br.readLine()) != null){
            System.out.println("str = " + str);
        }
        br.close();
    }
}
