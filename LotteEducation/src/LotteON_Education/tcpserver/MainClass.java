package LotteON_Education.tcpserver;

/**
 * 이 클래스 에서는 통신 client, server를 구현한다.
 * (전송 제어 규약 두 가지)
 * TCP : Transmission Control Protocol(동기 통신)
 * UPD : User Datagram Protocol(비동기 통신)
 * Server : Web(tomcat), Database -> 모두 TCP 기반
 * 종단시스템 : host를 의미 ex:) -> pc, smartphone, saccner, printer
 * Router : hardware(host간에 상호 데이터를 교환할 수 있는 장비)
 * Inter Network : 포괄적인 통신망
 * <p>
 * Port Number
 * -> IP 주소는 Internet에 존재하는 host(PC)를 식별할 수 있으나
 * 최종주체인 프로세스(프로그램)를 식별하지 못하기 때문에
 * 프로세스를 구별하기 위해 지정된 수치(0~1024)외의 숫자를 지정한다.
 * <p>
 * Socket
 * 통신의 주체, 틍신을 하기위한 오브젝트
 * IP, TCP/UDP
 * <p>
 * Port Number
 * -> IP 주소는 Internet에 존재하는 host(PC)를 식별할 수 있으나
 * 최종주체인 프로세스(프로그램)를 식별하지 못하기 때문에
 * 프로세스를 구별하기 위해 지정된 수치(0~1024)외의 숫자를 지정한다.
 * <p>
 * Socket
 * 통신의 주체, 틍신을 하기위한 오브젝트
 * IP, TCP/UDP
 * <p>
 * Port Number
 * -> IP 주소는 Internet에 존재하는 host(PC)를 식별할 수 있으나
 * 최종주체인 프로세스(프로그램)를 식별하지 못하기 때문에
 * 프로세스를 구별하기 위해 지정된 수치(0~1024)외의 숫자를 지정한다.
 * <p>
 * Socket
 * 통신의 주체, 틍신을 하기위한 오브젝트
 * IP, TCP/UDP
 */

/**
 * Port Number
 * -> IP 주소는 Internet에 존재하는 host(PC)를 식별할 수 있으나
 * 최종주체인 프로세스(프로그램)를 식별하지 못하기 때문에
 * 프로세스를 구별하기 위해 지정된 수치(0~1024)외의 숫자를 지정한다.
 */

/**
 * Socket
 * 통신의 주체, 틍신을 하기위한 오브젝트
 * IP, TCP/UDP
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 통신 순서
 *  Server                              Client
 *  1.Socket 버전 확인                      1.Socket 버전 확인
 *  2.Binding ->Ip, Port                 2.
 *  3.Listener (리스너가 서버의 동작확인을 위해)
 *  4.Accept <---------------------------- 2.Connect
 *  1) recv가 항상 먼저 서버에 이뤄진다.(Server_Rcv, Clinet_Send_Write)
 *  2) 이후 클라이언트에게 보낸다.(Server_Send, client_RcV_read)
 */

public class MainClass {
    public static void main(String[] args) throws IOException {
        /**
         * 클라이언트의 정보를 감는 소켓
         */
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            /**
             * 문지기 소캐 clientSocket과 다름
             * ServerSocket에 버전확인, binding, listener까지 동작함
             */
            serverSocket = new ServerSocket(9000);
            List<Socket> arr = new ArrayList<>();
            while (true) {
                System.out.println("접속 대기중 ...");
                clientSocket = serverSocket.accept();
                arr.add(clientSocket);
                /**
                 * 접속 client 확인
                 */
                System.out.println("client Ip: " + clientSocket.getInetAddress()
                        + " Port:" + clientSocket.getPort());

//                /**
//                 * 수신(recv)
//                 */
//                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                String str = reader.readLine();
//                System.out.println("client로부터 받은 메시지:" + str);
//                str = "반갑습니다";
//                /**
//                 * 송신(send)
//                 */
//                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
//                writer.println(str);
//                writer.flush();

                new ServerThread(clientSocket, arr).start();
            }
//            clientSocket.close();
//            serSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
