package 11package

//Abstract Class
public abstract class AbstractClass {

    public void templateMethod() {
        specificMethod();
        abstractMethod1();
        abstractMethod2();
    }

    public void specificMethod() {
        System.out.println("Abstract class defined method is called.");
    }

    public abstract void abstractMethod1();

    public abstract void abstractMethod2();

}
