package com.example.test_plugin.strategy.structural;

import com.example.test_plugin.strategy.DialogHelper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FlyweightStrategy extends DialogHelper {

    private FlyweightStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            //java 文件map
            map = new HashMap<>();
            Map<String, String> dataMap = new HashMap<>();
            //生成观察者接口
            Template ObserverTemplate = cfg.getTemplate("ConcreteFlyweight.ftl");
            Writer ObserverOut = new StringWriter();
            ObserverTemplate.process(dataMap, ObserverOut);
            map.put("ConcreteFlyweight", ObserverOut.toString());
            //生成被观察者接口
            Template ObservableTemplate = cfg.getTemplate("Flyweight.ftl");
            Writer ObservableOut = new StringWriter();
            ObservableTemplate.process(dataMap, ObservableOut);
            map.put("Flyweight", ObservableOut.toString());
            //生成具体观察者
            Template ConcreteObserverTemplate = cfg.getTemplate("FlyweightFactory.ftl");
            Writer ConcreteObserverOut = new StringWriter();
            ConcreteObserverTemplate.process(dataMap, ConcreteObserverOut);
            map.put("FlyweightFactory", ConcreteObserverOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final FlyweightStrategy INSTANCE = new FlyweightStrategy("flyweight");
    }

    //对外提供静态方法获取该对象
    public static FlyweightStrategy getInstance() {
        return FlyweightStrategy.SingletonHolder.INSTANCE;
    }
}
