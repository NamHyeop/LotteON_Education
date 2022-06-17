package LotteON_Education.subject.chatSubject.Lobby.testclient.Client1;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Write Class
 */
public class WriteClass1 {
    Socket socket;
    ClientFrame1 cf;

    public WriteClass1(Socket socket, ClientFrame1 cf) {
        this.socket = socket;
        this.cf = cf;
    }

    public void sendMessage(){
        String msg = "";
        String id = IdFrame1.tf.getText();

        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            //첫 번째 전송
            if(cf.isFirst){
                InetAddress iaddr = socket.getLocalAddress();
                String ip = iaddr.getHostAddress();
                msg = "[" + id + "]님 로그인(" + ip + ")";
            }
            //그 외 전송
            else{
                msg = "[" + id + "]" + cf.textF.getText();
                System.out.println("msg = " + msg);
            }
            pw.println(msg);
            pw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
