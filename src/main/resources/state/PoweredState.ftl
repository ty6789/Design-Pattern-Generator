package 11package

//Powered state.
public class PoweredState extends State {

    @Override
    public void powerOn() {
        System.out.println("The machine is already powered on, no need to power on again.");
    }

    @Override
    public void powerOff() {
        System.out.println("Machine successfully powered off！");
        context.setState(Context.unpoweredState);
    }

    @Override
    public void run() {
        System.out.println("Machine started successfully！");
        context.setState(Context.runningState);
    }
}