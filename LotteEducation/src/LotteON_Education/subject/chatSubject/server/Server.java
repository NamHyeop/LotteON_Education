package LotteON_Education.subject.chatSubject.server;

import LotteON_Education.subject.chatSubject.server.thread.readThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 대화방 서버를 구현하였다.
 */
public class Server extends Thread{
    int portNum;
    Socket clientSocket;
    ServerSocket serverSocket;
    public Server(int portNum) {
        this.portNum = portNum;
    }

    @Override
    public void run() {
        super.run();
        try {
            while(true) {
                try {
                    serverSocket = new ServerSocket(portNum);
                    break;
                } catch (Exception e) {
                    portNum++;
                }
            }
            System.out.println("==========================Server가 생성되었습니다.==========================");
            System.out.println("==========================server 포트번호 : " + portNum + "==========================");
            List<Socket> arr = new ArrayList<>();
            while (true) {
                System.out.println("==========================접속 대기중 ...==========================");
                clientSocket = serverSocket.accept();
                arr.add(clientSocket);
                System.out.println("==========================client Ip: " + clientSocket.getInetAddress() + " Port:" + clientSocket.getPort() + "==========================");
                 new readThread(clientSocket, arr).start();
            }
        } catch (IOException e) {
            portNum++;
            e.printStackTrace();
        }
    }

    public int getPortNum() {
        return portNum;
    }

    public void makeServer(int num) {
        run();
    }

}
