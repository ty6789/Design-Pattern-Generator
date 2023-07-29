package com.example.test_plugin.strategy.behavioral;

import com.example.test_plugin.strategy.DialogHelper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ObserverStrategy extends DialogHelper {

    private ObserverStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            //java 文件map
            map = new HashMap<>();
            Map<String, String> dataMap = new HashMap<>();
            //生成观察者接口
            Template ObserverTemplate = cfg.getTemplate("Observer.ftl");
            Writer ObserverOut = new StringWriter();
            ObserverTemplate.process(dataMap, ObserverOut);
            map.put("Observer", ObserverOut.toString());
            //生成被观察者接口
            Template ObservableTemplate = cfg.getTemplate("Observable.ftl");
            Writer ObservableOut = new StringWriter();
            ObservableTemplate.process(dataMap, ObservableOut);
            map.put("Observable", ObservableOut.toString());
            //生成具体观察者
            Template ConcreteObserverTemplate = cfg.getTemplate("ConcreteObserver.ftl");
            Writer ConcreteObserverOut = new StringWriter();
            ConcreteObserverTemplate.process(dataMap, ConcreteObserverOut);
            map.put("ConcreteObserver", ConcreteObserverOut.toString());
            //生成具体被观察者
            Template ConcreteObservableTemplate = cfg.getTemplate("ConcreteObservable.ftl");
            Writer ConcreteObservableOut = new StringWriter();
            ConcreteObservableTemplate.process(dataMap, ConcreteObservableOut);
            map.put("ConcreteObservable", ConcreteObservableOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final ObserverStrategy INSTANCE = new ObserverStrategy("observer");
    }

    //对外提供静态方法获取该对象
    public static ObserverStrategy getInstance() {
        return ObserverStrategy.SingletonHolder.INSTANCE;
    }
}
