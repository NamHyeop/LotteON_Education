package LotteON_Education.subject.chatSubject.Lobby.testclient.Client1;

import java.io.IOException;
import java.net.Socket;

public class MainClientFrame1 {
    public static Socket socket;
    public static ReadThread1 readThread1;
    public static void main(String[] args) {
        try {
            socket = new Socket("127.0.0.1", 9000);
            System.out.println("connection success!!");
            ClientFrame1 cf = new ClientFrame1(socket);
            readThread1 = new ReadThread1(socket, cf);
            readThread1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        MainClientFrame1.socket = socket;
    }
}
