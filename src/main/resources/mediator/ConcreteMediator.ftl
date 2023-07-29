package 11package

//Concrete Mediator
public class ConcreteMediator implements Mediator {

    private ColleagueA colleagueA;

    private ColleagueB colleagueB;

    void registerColleagueA(ColleagueA colleagueA) {
        this.colleagueA = colleagueA;
    }

    void registerColleagueB(ColleagueB colleagueB) {
        this.colleagueB = colleagueB;
    }

    @Override
    public void notifyColleagueA() {
        colleagueA.receive();
    }

    @Override
    public void notifyColleagueB() {
        colleagueB.receive();
    }

}
