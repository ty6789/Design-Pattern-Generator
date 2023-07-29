package 11package

import java.util.ArrayList;
import java.util.List;

//Concrete Container
public class ${element}AggregateImpl implements ${element}Aggregate {

    private List<${element}> list = new ArrayList<${element}>();  // Element List

    @Override
    public void add${element}(${element} element) {
        this.list.add(element);
    }

    @Override
    public void remove${element}(${element} element) {
        this.list.remove(element);
    }

    @Override
    public ${element}Iterator get${element}Iterator() {
        return new ${element}IteratorImpl(list);
    }
}