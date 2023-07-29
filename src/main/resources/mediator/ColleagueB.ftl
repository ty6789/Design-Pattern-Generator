package 11package

//Concrete Colleague B
public class ColleagueB extends Colleague {

    public ColleagueB(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void receive() {
        System.out.println("ColleagueB has received the message.");
    }

    @Override
    public void send() {
        System.out.println("ColleagueB wants to send a message.");
        mediator.notifyColleagueA();
    }
}