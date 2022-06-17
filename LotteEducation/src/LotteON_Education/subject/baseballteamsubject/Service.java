package LotteON_Education.subject.baseballteamsubject;

import LotteON_Education.subject.baseballteamsubject.DTO.Human;
import LotteON_Education.subject.baseballteamsubject.DTO.Pitcher;
import LotteON_Education.subject.baseballteamsubject.Exception.NotExistUser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Service {
    static MemoryRepository memoryRepository = new MemoryRepository();

    public static void addPitcher() {
        Scanner sc = new Scanner(System.in);
        Pitcher player = new Pitcher();
        System.out.println("등 번호를 입력하세요 : ");
        player.backNumber = sc.nextInt();
        System.out.println("선수 이름을 입력세요 : ");
        player.name = sc.next();
        System.out.println("선수 나이를 입력하세요: ");
        player.age = sc.nextInt();
        System.out.println("선수 키를 입력하세요: ");
        player.key = sc.nextInt();
        System.out.println("선수 승리률 입력하세요: ");
        player.winRate = sc.nextInt();
        System.out.println("선수 패배률 입력하세요: ");
        player.loseRate = sc.nextInt();
        System.out.println("선수 방어율을 입력하세요: ");
        player.defenseRate = sc.nextInt();
        memoryRepository.save(player);
    }

    public static void addBatter() {
        Scanner sc = new Scanner(System.in);
        Pitcher player = new Pitcher();
        System.out.println("등 번호를 입력하세요 : ");
        player.backNumber = sc.nextInt();
        System.out.println("선수 이름을 입력세요 : ");
        player.name = sc.next();
        System.out.println("선수 나이를 입력하세요");
        player.age = sc.nextInt();
        System.out.println("선수 키를 입력하세요");
        player.key = sc.nextInt();
        System.out.println("선수 타수 입력하세요");
        player.winRate = sc.nextInt();
        System.out.println("선수 안타율 입력하세요");
        player.loseRate = sc.nextInt();
        System.out.println("선수 타을 입력하세요");
        player.defenseRate = sc.nextInt();
        memoryRepository.save(player);
    }

    public void delete(int backNumber, String name) {
        try {
            memoryRepository.delete(backNumber, name);
        } catch (RuntimeException e) {
            throw new NotExistUser(e);
        }
    }

    public void findByUser(int backNumber, String name) {
        Human user = memoryRepository.findUser(backNumber, name);
    }

    public void updatePlayer(int backNumber, String name, String updateName, int updateNumber) {
        memoryRepository.updatePlayer(backNumber, name, updateName, updateNumber);
    }

    public void fileSave() throws IOException {
        File newFile = new File("/Users/namhyeop/Desktop/test123.txt");
        FileWriter fwriter;
        fwriter = new FileWriter(newFile);
        Map<Long, Human> store = memoryRepository.getStore();
        store.entrySet().stream().forEach(tmp -> {
            try {
                fwriter.write(tmp.toString());
                fwriter.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fwriter.close();
    }
}
