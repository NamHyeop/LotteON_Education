package LotteON_Education.GUI;

import LotteON_Education.GUI.net.ReadThread;

import java.io.IOException;
import java.net.Socket;

public class UserClass2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.0.101", 9000);
            System.out.println("connection success!!");
            ClientFrame cf = new ClientFrame(socket);
            new ReadThread(socket, cf).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
