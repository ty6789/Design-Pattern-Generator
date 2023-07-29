package 11package

// Static Proxy Class
public class StaticProxy implements Target {

    private Target target;

    public StaticProxy(Target target) {
        this.target = target;
    }

    public void execute() {
        System.out.println("Before execute");
        target.execute();
        System.out.println("After execute");
    }
}