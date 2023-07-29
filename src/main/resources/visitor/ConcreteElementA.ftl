package 11package

//Concrete Element A
public class ConcreteElementA implements Element {

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationA() {
        return "ConcreteElementA";
    }

}