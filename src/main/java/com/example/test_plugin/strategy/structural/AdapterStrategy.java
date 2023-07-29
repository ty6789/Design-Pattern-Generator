package com.example.test_plugin.strategy.structural;

import com.example.test_plugin.strategy.DialogHelper;
import com.example.test_plugin.strategy.creational.SingletonStrategy;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class AdapterStrategy extends DialogHelper {

    private AdapterStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            //java 文件map
            map = new HashMap<>();
            Map<String, String> dataMap = new HashMap<>();
            //生成观察者接口
            Template ObserverTemplate = cfg.getTemplate("Adapter.ftl");
            Writer ObserverOut = new StringWriter();
            ObserverTemplate.process(dataMap, ObserverOut);
            map.put("Adapter", ObserverOut.toString());
            //生成被观察者接口
            Template ObservableTemplate = cfg.getTemplate("NewInterface.ftl");
            Writer ObservableOut = new StringWriter();
            ObservableTemplate.process(dataMap, ObservableOut);
            map.put("NewInterface", ObservableOut.toString());
            //生成具体观察者
            Template ConcreteObserverTemplate = cfg.getTemplate("OldInterface.ftl");
            Writer ConcreteObserverOut = new StringWriter();
            ConcreteObserverTemplate.process(dataMap, ConcreteObserverOut);
            map.put("OldInterface", ConcreteObserverOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final AdapterStrategy INSTANCE = new AdapterStrategy("adapter");
    }

    //对外提供静态方法获取该对象
    public static AdapterStrategy getInstance() {
        return AdapterStrategy.SingletonHolder.INSTANCE;
    }
}
