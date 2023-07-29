package com.example.test_plugin.strategy.behavioral;

import com.example.test_plugin.MyAction;
import com.example.test_plugin.dialog.behavioral.CommandDialog;
import com.example.test_plugin.dialog.behavioral.IteratorDialog;
import com.example.test_plugin.strategy.DialogHelper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class IteratorStrategy extends DialogHelper {

    private IteratorStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            IteratorDialog dialog = new IteratorDialog(MyAction.project);
            dialog.pack();
            dialog.show();
            //java 文件map
            map = new HashMap<>();
            //从dialog中获取数据
            Map<String, String> dataMap = new HashMap<>();
            String element = dialog.getElement();
            dataMap.put("element", element);
            //生成抽象迭代器
            Template abstractIteratorTemplate = cfg.getTemplate("AbstractIterator.ftl");
            Writer abstractIteratorOut = new StringWriter();
            abstractIteratorTemplate.process(dataMap, abstractIteratorOut);
            map.put(element + "Iterator", abstractIteratorOut.toString());
            //生成具体迭代器
            Template iteratorTemplate = cfg.getTemplate("Iterator.ftl");
            Writer iteratorOut = new StringWriter();
            iteratorTemplate.process(dataMap, iteratorOut);
            map.put(element + "IteratorImpl", iteratorOut.toString());
            //生成抽象容器类
            Template abstractContainerTemplate = cfg.getTemplate("AbstractContainer.ftl");
            Writer abstractContainerOut = new StringWriter();
            abstractContainerTemplate.process(dataMap, abstractContainerOut);
            map.put(element + "Aggregate", abstractContainerOut.toString());
            //生成具体容器类
            Template contextTemplate = cfg.getTemplate("Container.ftl");
            Writer contextOut = new StringWriter();
            contextTemplate.process(dataMap, contextOut);
            map.put(element + "AggregateImpl", contextOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final IteratorStrategy INSTANCE = new IteratorStrategy("iterator");
    }

    //对外提供静态方法获取该对象
    public static IteratorStrategy getInstance() {
        return IteratorStrategy.SingletonHolder.INSTANCE;
    }
}
