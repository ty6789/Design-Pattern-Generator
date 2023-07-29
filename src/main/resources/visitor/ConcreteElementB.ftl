package 11package

//Concrete Element B
public class ConcreteElementB implements Element {

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB() {
        return "ConcreteElementB";
    }

}