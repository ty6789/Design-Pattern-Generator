package 11package

//Concrete Visitor 2
public class ConcreteVisitor2 implements Visitor {

    public void visit(ConcreteElementA element) {
        System.out.println("ConcreteVisitor2 visit " + element.operationA());
    }

    public void visit(ConcreteElementB element) {
        System.out.println("ConcreteVisitor2 visit " + element.operationB());
    }

}