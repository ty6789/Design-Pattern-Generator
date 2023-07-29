package 11package

//Running state
public class RunningState extends State {

    @Override
    public void powerOn() {
        //Can be interpreted as stopping the machine.
        System.out.println("Switch from running state to normal powered state");
        context.setState(Context.poweredState);
    }

    @Override
    public void powerOff() {
        System.out.println("Cannot power off in running state");
    }

    @Override
    public void run() {
        System.out.println("The machine is already running, no need to run again");
    }
}