package LotteON_Education.fileexample;

import java.io.File;
import java.io.IOException;

public class FileSystem {
    /*
      저장매체 : 파일, Database
      1. *.java
      2. *.cpp
      3. *.dll
      4. *.lib
      5. *.jar
      6. *.xml
      7. *.json

      dll = Dynamick Link Library -> 동적파일 -> lib와 다르게 프로그램임 동작중에 생성 -> 그래서 동적이라는 의미가 붙음
      장점 : 메모리 절약이 가능
      단점 : 보안이 취약

      lib = Library -> 정적파일, dll과 다르게 프로그램이 동작하기전에 생성
      장점 :
      단점 :
     */

    public static void main(String[] args) throws IOException {
        /**
         * 경로 파일 리스트 조회
         */
//        File file = new File("/Users/namhyeop/Desktop");
//    String fileList[] = file.list();
//    for(int i = 0; i < fileList.length; i++){
//        System.out.println("fileList = " + fileList[i]);
//    }

//        File fileList[] = file.listFiles();
//        for(int i = 0; i < fileList.length; i++){
//            if(fileList[i].isFile()){
//                System.out.println("[파일]" + fileList[i].getName());
//            }else if(fileList[i].isDirectory()){
//                System.out.println("[폴더]" + fileList[i].getName());
//            }else{
//                System.out.println("[?]" + fileList[i].getName());
//            }
//        }

        File newfile = new File("/Users/namhyeop/Desktop/testFile55.txt");
        /**
         * 파일 생성
         */
//        try {
//            newfile.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        boolean newFile = newfile.createNewFile();
        System.out.println("newFile = " + newFile);
        if(newFile){
            System.out.println("파일 생성 성공!");
        }else{
            System.out.println("파일 생성 실패!");
        }

        /**
         * 여러 폴더 생성
         */
        File newDir = new File("/Users/namhyeop/Desktop/testDir/test2Dir");
        if(newDir.mkdirs()){
            System.out.println("폴더 생성 성공!");
        }else{
            System.out.println("폴더 생성 실패!");
        }

        /**
         * 파일 존재 여부
         */
        if(newfile.exists()){
            System.out.println("파일이 존재합니다");
        }

        /**
         * 읽기 전용 파일로 설정하기
         */
//        newfile.setReadOnly();

        /**
         * 쓰기 전용 파일로 만들기
         */
        newfile.setWritable(true);
        /**
         * 삭제
         */
        newfile.delete();
    }

}
