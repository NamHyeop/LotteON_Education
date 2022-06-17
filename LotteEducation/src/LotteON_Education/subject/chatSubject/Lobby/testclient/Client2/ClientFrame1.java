package LotteON_Education.subject.chatSubject.Lobby.testclient.Client2;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//채팅 Form
public class ClientFrame1 extends JFrame implements WindowListener, ActionListener, ListSelectionListener {

    public Socket socket;
    WriteClass1 wc;

    public JTextField textF = new JTextField(1000);
    public JTextArea textA = new JTextArea();

    JButton btnTransfer = new JButton("send");
    JButton btnMakeRoom = new JButton("makeRoom");
    JButton btnExit = new JButton("exit");

    //첫번째 전송
    public boolean isFirst = true;

    //채팅방 리스트
    public JList<String> jlist;
    JLabel label;
    public DefaultListModel model;
    private ListSelectionEvent test;

    public ClientFrame1(Socket socket) {
        super("chatting");
        this.socket = socket;
        setLayout(null);

        wc = new WriteClass1(this.socket, this);
        new IdFrame1(wc, this);

        JScrollPane scrPane = new JScrollPane(textA);
        scrPane.setBounds(0,50,400,500);
        add(scrPane);

        textF.setBounds(0, 600, 300, 50);
        add(textF);

        btnTransfer.setBounds(300, 600, 100, 50);
        add(btnTransfer);

        btnMakeRoom.setBounds(400, 600, 100, 50);
        add(btnMakeRoom);

        btnExit.setBounds(500, 600, 100, 50);
        add(btnExit);

        btnMakeRoom.addActionListener(this);
        btnTransfer.addActionListener(this);
        btnExit.addActionListener(this);

        model = new DefaultListModel();

        jlist = new JList<String>(model);
        label = new JLabel("");
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.addListSelectionListener(this);
        jlist.setBounds(400, 50, 200, 500);
        this.add(jlist, BorderLayout.EAST);
        this.add(label, BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        setBounds(200,200,620,700);
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == btnTransfer || obj == textF){
            if(textF.getText().trim().equals("")) return;
            String id = IdFrame1.tf.getText();
            textA.append("[" + id + "] " + textF.getText() + "\n");
            wc.sendMessage();
            textF.setText("");
        }
        if(obj == btnMakeRoom){
            System.out.println("=====================방 만들기 명령 수행=====================");
            String msg = "make_room";
            try {
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                pw.println(msg);
                pw.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(obj == btnExit){
            System.out.println("=====================btn exit : 로비 이동=====================");
            try {
                makeNewLoginSocket();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void makeNewLoginSocket() throws IOException {
        socket = MainClientFrame1.getSocket();
        socket = new Socket("127.0.0.1", 9000);
        MainClientFrame1.readThread1.stop();
        MainClientFrame1.socket = socket;
        MainClientFrame1.readThread1.setSocket(socket);
        MainClientFrame1.readThread1 = new ReadThread1(socket, this);
        MainClientFrame1.readThread1.start();
        wc.setSocket(socket);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        test = e;
        String s = test.toString();
        String selectedValue = jlist.getSelectedValue();
        if (selectedValue != null) {
            System.out.println("selectedValue = " + selectedValue);
            List<String> strings = makePortNum(selectedValue);
            int portNum = Integer.valueOf(strings.get(0));

            try {
                System.out.println("새로운 연결 실행");

                socket = new Socket("127.0.0.1", portNum);
                MainClientFrame1.socket = socket;
                MainClientFrame1.readThread1.setSocket(socket);
                MainClientFrame1.readThread1 = new ReadThread1(socket, this);
                MainClientFrame1.readThread1.start();
                wc.setSocket(socket);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<String> makePortNum(String str){
        List<String> arr = new ArrayList<>();
        String portNum = "";
        for(int i = 0; i < str.length(); i++){
            if('0' <= str.charAt(i) && str.charAt(i) <= '9')
                portNum += str.charAt(i);
            else{
                if(portNum.length() > 0)
                    arr.add(portNum);
                portNum = "";
            }
        }
        return arr;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
