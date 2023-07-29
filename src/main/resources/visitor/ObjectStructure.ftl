package 11package

import java.util.ArrayList;
import java.util.List;

//Structure Object
public class ObjectStructure {

    private List<Element> elements = new ArrayList<>();

    public void accept(Visitor visitor) {
        for (Element element : elements) {
            element.accept(visitor);
        }
    }

    public void add(Element element) {
        elements.add(element);
    }

    public void remove(Element element) {
        elements.remove(element);
    }

}
