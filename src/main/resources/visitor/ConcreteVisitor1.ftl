package 11package

//Concrete Visitor 1
public class ConcreteVisitor1 implements Visitor {

    public void visit(ConcreteElementA element) {
        System.out.println("ConcreteVisitor1 visit " + element.operationA());
    }

    public void visit(ConcreteElementB element) {
        System.out.println("ConcreteVisitor1 visit " + element.operationB());
    }

}