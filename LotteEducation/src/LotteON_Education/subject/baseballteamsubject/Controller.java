package LotteON_Education.subject.baseballteamsubject;
import java.io.IOException;
import java.util.Scanner;


public class Controller {
    static Scanner sc = new Scanner(System.in);
    static Service service = new Service();

    public static void main(String[] args) throws IOException {
        while(true) {
            descriptInfo();
            int cmd = sc.nextInt();
            if (cmd == 1) {
                addInput();
            } else if (cmd == 2) {
                delteInput();
            } else if (cmd == 3) {
                searchInput();
            } else if (cmd == 4) {
                updateInput();
            } else if (cmd == 5) {
                service.fileSave();
            } else if (cmd == 6) {

            }
        }
    }

    private static void descriptInfo() {
        System.out.println("명령을 입력하세요");
        System.out.println("1.추가");
        System.out.println("2.삭제");
        System.out.println("3.검색");
        System.out.println("4.수정");
        System.out.println("5.파일저장");
        System.out.println("6.파일읽기");
    }

    private static void addInput(){
        int cmd;
        System.out.println("투수는 1번, 포수는 2번을 입력하세요");
        cmd = sc.nextInt();
        if (cmd == 1) {
            service.addPitcher();
        } else if(cmd == 2){
            service.addBatter();
        }
    }

    private static void delteInput() {
        try {
            deleteFunction();
        } catch (RuntimeException e) {
            deleteFunction();
        }
    }

    private static void searchInput() {
        int backNumber;
        String name;
        System.out.println("등 번호와 선수 이름을 입력하세요");
        System.out.println("등 번호 : ");
        backNumber = sc.nextInt();
        System.out.println("이름 : ");
        name = sc.next();
        service.findByUser(backNumber, name);
    }

    private static void updateInput() {
        int backNumber, updateNumber;
        String name, updateName;
        System.out.println("등 번호와 선수 이름을 입력하세요");
        System.out.println("등 번호 : ");
        backNumber = sc.nextInt();
        System.out.println("이름 : ");
        name = sc.next();

        System.out.println("변경할 등 번호을 입력하세요");
        updateNumber = sc.nextInt();
        System.out.println("변경할 이름을 입력하세요");
        updateName = sc.next();
        service.updatePlayer(backNumber, name, updateName, updateNumber);
    }

    private static void deleteFunction() {
        int backNumber;
        String name;
        System.out.println("삭제할 등 번호와 선수 이름을 입력하세요");
        System.out.println("삭제할 등 번호 : ");
        backNumber = sc.nextInt();
        System.out.println("삭제할 이름 : ");
        name = sc.next();
        service.delete(backNumber, name);
    }


}
