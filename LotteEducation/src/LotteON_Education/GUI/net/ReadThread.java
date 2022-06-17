package LotteON_Education.GUI.net;

import LotteON_Education.GUI.ClientFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {
    Socket socket;
    ClientFrame cf;

    public ReadThread(Socket socket, ClientFrame cf) {
        this.socket = socket;
        this.cf = cf;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = br.readLine();
                if(str == null){
                    System.out.println("접속끊김");
                }
                cf.textA.append(str + "\n");
                Thread.sleep(300);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
