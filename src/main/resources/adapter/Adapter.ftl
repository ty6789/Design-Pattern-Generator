package 11package

// Adapter
public class Adapter implements NewInterface {

    private OldInterface oldObject;

    public Adapter(OldInterface oldObject) {
        this.oldObject = oldObject;
    }

    @Override
    public void newMethod() {
        oldObject.oldMethod();
    }
}
