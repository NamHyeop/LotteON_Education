package LotteON_Education.tcpserver.WebSocket_Client.src.tcpclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class MainClass2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        /**
         1.접속해야 할 Server IP(주소)를 설정 -> Socket
         */
        InetSocketAddress sockAddr = new InetSocketAddress("127.0.0.1", 9000);
         /*
         2.Socket 생성
         */
        Socket socket = new Socket();
        /*
         3.Connect
         */
        /**
         * 전파 감지 스레드
         */
        try {
            socket.connect(sockAddr, 10000);
            InetAddress inetAddr;
            if ((inetAddr = socket.getInetAddress()) != null) {
                System.out.println("서버 접속 성공!");
            } else {
                System.out.println("서버 접속 실패!");
            }
            new readThread(socket).start();
            while (true) {
                System.out.println("[보낼메시지]:");
                String str = sc.nextLine();
                /**
                 * 송신(send)
                 */
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println(str);
                writer.flush();
//            /**
//             * 수신(recv)
//             */
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            str = reader.readLine();
//            System.out.println(str);
//            System.out.println("server로부터 받은 메시지:" + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         4.Paccet 송 수신
         */

    }
}


