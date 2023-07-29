package com.example.test_plugin.strategy.structural;

import com.example.test_plugin.strategy.DialogHelper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class CompositeStrategy extends DialogHelper {

    private CompositeStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            //java 文件map
            map = new HashMap<>();
            Map<String, String> dataMap = new HashMap<>();
            //生成观察者接口
            Template ObserverTemplate = cfg.getTemplate("Component.ftl");
            Writer ObserverOut = new StringWriter();
            ObserverTemplate.process(dataMap, ObserverOut);
            map.put("Component", ObserverOut.toString());
            //生成被观察者接口
            Template ObservableTemplate = cfg.getTemplate("Composite.ftl");
            Writer ObservableOut = new StringWriter();
            ObservableTemplate.process(dataMap, ObservableOut);
            map.put("Composite", ObservableOut.toString());
            //生成具体观察者
            Template ConcreteObserverTemplate = cfg.getTemplate("Leaf.ftl");
            Writer ConcreteObserverOut = new StringWriter();
            ConcreteObserverTemplate.process(dataMap, ConcreteObserverOut);
            map.put("Leaf", ConcreteObserverOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final CompositeStrategy INSTANCE = new CompositeStrategy("composite");
    }

    //对外提供静态方法获取该对象
    public static CompositeStrategy getInstance() {
        return CompositeStrategy.SingletonHolder.INSTANCE;
    }
}
