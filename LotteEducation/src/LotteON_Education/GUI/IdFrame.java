package LotteON_Education.GUI;

import LotteON_Education.GUI.net.WriteClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ID 입력받기 위한 Form
public class IdFrame extends JFrame implements ActionListener {
    public static TextField tf = new TextField(8);

    JButton btn = new JButton("등록");
    ClientFrame cf;
    //writeClass 추가
    WriteClass wc;

    public IdFrame(WriteClass wc, ClientFrame cf){
        this.cf = cf;
        this.wc = wc;

        setTitle("ID input");
        setLayout(null);

        JLabel label = new JLabel("id:");
        label.setBounds(50, 60, 30,30);
        add(label);

        tf.setBounds(80, 60, 100, 30);
        add(tf);
        btn.setBounds(80, 110, 100, 30);
        btn.addActionListener(this);
        add(btn);

        setBounds(300, 300, 250, 200);
        setBackground(Color.green);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //id 전송
        wc.sendMessage();
        //첫 번째 전송
        cf.isFirst = false;
        //ClientFrame을 시각화
        cf.setVisible(true);
        //현재 창 닫기
        this.dispose();
    }
}
