package 11package

import java.util.ArrayList;
import java.util.List;

//Composite Component
public class Composite implements Component {

    private List<Component> childComponents = new ArrayList<Component>();
    private String name;

    public Composite(String name) {
        this.name = name;
    }

    public void add(Component component){
        childComponents.add(component);
    }

    public void remove(Component component){
        childComponents.remove(component);
    }

    public void operation() {
        System.out.println("Composite " + name + ": operation()");
        for (Component component : childComponents) {
            component.operation();
        }
    }
}