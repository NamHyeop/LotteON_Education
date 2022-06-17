package LotteON_Education.staticmethod;

public class MyClass {
    public void method(){
        System.out.println("Myclass method()");
    }

    public static void staticMethod(){
        System.out.println("MyClass staticMethod");
    }

    public static MyClass getObject(){
        MyClass myClass = new MyClass();
        myClass.method();
        return myClass;
    }


}
