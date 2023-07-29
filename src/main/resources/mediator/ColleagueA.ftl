package 11package

//Concrete Colleague A
public class ColleagueA extends Colleague {

    public ColleagueA(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void receive() {
        System.out.println("ColleagueA has received the message.");
    }

    @Override
    public void send() {
        System.out.println("ColleagueA wants to send a message.");
        mediator.notifyColleagueB();
    }
}