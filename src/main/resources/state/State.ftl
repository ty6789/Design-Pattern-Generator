package 11package

// State Interface
public abstract class State {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void powerOn();

    public abstract void powerOff();

    public abstract void run();

}