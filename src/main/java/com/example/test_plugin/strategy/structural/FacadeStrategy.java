package com.example.test_plugin.strategy.structural;

import com.example.test_plugin.strategy.DialogHelper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FacadeStrategy extends DialogHelper {

    private FacadeStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            //java 文件map
            map = new HashMap<>();
            Map<String, String> dataMap = new HashMap<>();
            //生成观察者接口
            Template ObserverTemplate = cfg.getTemplate("Facade.ftl");
            Writer ObserverOut = new StringWriter();
            ObserverTemplate.process(dataMap, ObserverOut);
            map.put("Facade", ObserverOut.toString());
            //生成被观察者接口
            Template ObservableTemplate = cfg.getTemplate("SubsystemA.ftl");
            Writer ObservableOut = new StringWriter();
            ObservableTemplate.process(dataMap, ObservableOut);
            map.put("SubsystemA", ObservableOut.toString());
            //生成具体观察者
            Template ConcreteObserverTemplate = cfg.getTemplate("SubsystemB.ftl");
            Writer ConcreteObserverOut = new StringWriter();
            ConcreteObserverTemplate.process(dataMap, ConcreteObserverOut);
            map.put("SubsystemB", ConcreteObserverOut.toString());
            //生成具体观察者
            Template SubsystemCTemplate = cfg.getTemplate("SubsystemC.ftl");
            Writer SubsystemCOut = new StringWriter();
            SubsystemCTemplate.process(dataMap, SubsystemCOut);
            map.put("SubsystemC", SubsystemCOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final FacadeStrategy INSTANCE = new FacadeStrategy("facade");
    }

    //对外提供静态方法获取该对象
    public static FacadeStrategy getInstance() {
        return FacadeStrategy.SingletonHolder.INSTANCE;
    }
}
