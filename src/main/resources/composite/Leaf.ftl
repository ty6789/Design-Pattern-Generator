package 11package

// Leaf Component
public class Leaf implements Component {

    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    public void operation() {
        System.out.println("Leaf " + name + ": operation()");
    }
}