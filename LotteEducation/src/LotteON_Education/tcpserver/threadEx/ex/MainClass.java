package LotteON_Education.tcpserver.threadEx.ex;

public class MainClass {
    public static void main(String[] args) {
        Thread thread1 = new ThreadEx("퐁");
        Thread thread2 = new ThreadEx("당");
        Thread thread3 = new ThreadEx("!!!");

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
