package LotteON_Education.subject.chatSubject.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 9000번 포트번호는 마스터 컨트롤러.
 * 방 만들기 명령을 수행한다.
 */
public class Controller {

    public static void main(String[] args) {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        ArrayList<Server> servers = new ArrayList<>();
            try {
                serverSocket = new ServerSocket(9000);
                List<Socket> arr = new ArrayList<>();
                while (true) {
                    System.out.println("==========================접속 대기중==========================");
                    clientSocket = serverSocket.accept();
                    arr.add(clientSocket);
                    System.out.println("==========================" + clientSocket + "clientSocket ==========================");
                    /**
                     * 접속 client 확인
                     */
                    System.out.println("========================== client Ip: " + clientSocket.getInetAddress()
                            + " Port:" + clientSocket.getPort() + "==========================");
                    new ServerThread(clientSocket, arr, servers, 9000).start();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
    }
}
