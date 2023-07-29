package 11package

//Context
public class Context {

    //Define all states of a machine.

    //Powered on state, at this moment it can be powered off or run.
    public final static PoweredState poweredState = new PoweredState();

    //In the state of no power, you can only power on.
    public final static UnpoweredState unpoweredState = new UnpoweredState();

    //In the running state, you can only stop.
    public final static RunningState runningState = new RunningState();

    //Define the current state of the machine.
    private State state;

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        //Change in current state.
        this.state = state;
        //Notify the implementation class about the current environment.
        this.state.setContext(this);
    }

    public void powerOn() {
        this.state.powerOn();
    }

    public void powerOff() {
        this.state.powerOff();
    }

    public void run() {
        this.state.run();
    }

}