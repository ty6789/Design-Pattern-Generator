package 11package

import java.util.ArrayList;
import java.util.List;

//Concrete Iterator
public class ${element}IteratorImpl implements ${element}Iterator {

    private List<${element}> list;
    private int position = 0;

    public ${element}IteratorImpl(List<${element}> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return position < list.size();
    }

    @Override
    public ${element} next() {
        ${element} current${element} = list.get(position);
        position++;
        return current${element};
    }
}