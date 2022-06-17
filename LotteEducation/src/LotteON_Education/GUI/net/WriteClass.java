package LotteON_Education.GUI.net;

import LotteON_Education.GUI.ClientFrame;
import LotteON_Education.GUI.IdFrame;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class WriteClass {
    Socket socket;
    ClientFrame cf;

    public WriteClass(Socket socket, ClientFrame cf) {
        this.socket = socket;
        this.cf = cf;
    }

    public void sendMessage(){
        String msg = "";
        String id = IdFrame.tf.getText();

        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            //첫 번째 전송
            if(cf.isFirst){
                InetAddress iaddr = socket.getLocalAddress();
                String ip = iaddr.getHostAddress();
                msg = "[" + id + "]님 로그인(" + ip + ")";
                cf.isFirst = false;
            }
            //그 외 전송
            else{
                msg = "[" + id + "]" + cf.textF.getText();
            }
            pw.println(msg);
            pw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
