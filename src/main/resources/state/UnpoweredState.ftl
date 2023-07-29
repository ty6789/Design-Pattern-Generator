package 11package

//Unpowered state
public class UnpoweredState extends State {

    @Override
    public void powerOn() {
        System.out.println("The machine is successfully powered on！");
        context.setState(Context.poweredState);
    }

    @Override
    public void powerOff() {
        System.out.println("The machine has already been powered off, no need to power off again");
    }

    @Override
    public void run() {
        System.out.println("Machine can't operate in the power-off state");
    }
}