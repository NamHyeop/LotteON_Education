package LotteON_Education.subject.chatSubject.server;

import LotteON_Education.subject.chatSubject.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread {
    Socket socket;
    List<Socket> list;
    ArrayList<Server> servers;
    int portNum;

    public ServerThread(Socket socket, List<Socket> list, ArrayList<Server> servers, int portNum) {
        this.socket = socket;
        this.list = list;
        this.servers = servers;
        this.portNum = portNum;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                /**
                 * 수신(recv)
                 */
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = reader.readLine();
                System.out.println("[Port " + portNum + "] client로부터 받은 메시지:" + str);
                if (str == null) {
                    throw new InterruptedException();
                } else if (str.equals("make_room")) {
                    Server server = new Server(++portNum);
                    server.start();
                    portNum = server.getPortNum();
                    servers.add(server);

                    StringBuilder sb = new StringBuilder();
                    for (Server s : servers){
                        sb.append(s.portNum).append("!@#$%");
                    }
                    PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                    pw.println("!@#$%" + sb);
                    pw.flush();
//                    sb.setLength(0);
                } else if (str.equals("all Server")) {
                    for (Server s : servers) {
                        System.out.println("socket = " + s);
                    }
                }else {
                    for (Socket s : list) {
                        if (socket == s)
                            continue;
                        PrintWriter writer = new PrintWriter(s.getOutputStream());
                        writer.println(str);
                        writer.flush();
                    }
                }
                /**
                 * 송신(send)
                 */
                Thread.sleep(300);
            }
        } catch (Exception e) {
            System.out.println("연결이 끊긴 IP = " + socket.getInetAddress());
            list.remove(socket);

            //접속되어 있는 남아있는 클라이언트 출력
            for (Socket s : list) {
                System.out.println("접속되어 있는 IP:" + s.getInetAddress() + " Post:" + s.getPort());
            }
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
