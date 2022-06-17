package LotteON_Education.subject.chatSubject.Lobby.testclient.Client2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Server의 소켓 요청 정보를 읽는 스레드
 */
public class ReadThread1 extends Thread {
    Socket socket;
    ClientFrame1 cf;
    static int roomNumber = 0;
    public ReadThread1(Socket socket, ClientFrame1 cf) {
        this.socket = socket;
        this.cf = cf;
    }

    /**
     * 암호화 명령어 문자열 파싱
     */
    public List<String> makePortNum(String str){
        List<String> arr = new ArrayList<>();
        String portNum = "";
        for(int i = 0; i < str.length(); i++){
            if('0' <= str.charAt(i) && str.charAt(i) <= '9')
                portNum += str.charAt(i);
            else{
                if(portNum.length() > 0)
                arr.add(portNum);
                portNum = "";
            }
        }
        return arr;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = br.readLine();
                System.out.println("================== 응답 서버 포트번호 = " + socket.getPort() + "==================");
                String subStr="";
                if(str.length() > 5)
                    subStr = str.substring(0, 5);
                System.out.println("===================암호화 명령어 출력 : " + str + " ===================");
                if(str == null){
                    System.out.println("접속끊김");
                }
                /**
                 * !@#$%로 암호화된 문자열 입력시 방 만들기의 추가 명령어라 판단하여 방만들기 수행
                 */
                else if(subStr.equals("!@#$%")){
                   List<String> portInfoArr = makePortNum(str);
                    for (String s : portInfoArr) {
                        System.out.println("s = " + s);
                    }
                    if(portInfoArr.size() != 0 && portInfoArr.size() != 1)
                     portInfoArr.remove(portInfoArr.size() -1);
                    cf.model.clear();
                    for(String s : portInfoArr){
                        String roomName = "Port Num: " + s + " 번 방";
                        cf.model.add(0, roomName);
                    }
                }
                else {
                    cf.textA.append(str + "\n");
                }
                Thread.sleep(300);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//         catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
