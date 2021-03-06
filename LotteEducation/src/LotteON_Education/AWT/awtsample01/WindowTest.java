package LotteON_Education.AWT.awtsample01;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowTest extends Frame implements WindowListener {
    public WindowTest(){
        setSize(640, 480);
        setLocation(0, 0);
        setVisible(true);
        addWindowListener(this);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("windowClosing");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("windowClosing");
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("windowClosed");

    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("windowIconified");

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("windowDeiconified");

    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("windowActivated");

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("windowDeactivated");
    }
}
