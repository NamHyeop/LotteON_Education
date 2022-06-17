package LotteON_Education.tcpserver.WebSocket_Client.src.tcpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class readThread extends Thread {
    Socket socket;

    public readThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();

        // 수신
        try {
            while(true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = reader.readLine();
                System.out.println("server로부터 받은 메시지:" + str);

                Thread.sleep(300);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
