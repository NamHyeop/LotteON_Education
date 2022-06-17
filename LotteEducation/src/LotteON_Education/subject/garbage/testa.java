package LotteON_Education.subject.garbage;

public class testa {
    public static void main(String[] args) {
        String a = "!9000!9002";
        String[] split = a.split("!");
        for (String s : split) {
            System.out.println("s = " + s);
        }
    }
}
