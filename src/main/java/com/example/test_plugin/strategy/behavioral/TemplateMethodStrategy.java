package com.example.test_plugin.strategy.behavioral;

import com.example.test_plugin.strategy.DialogHelper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TemplateMethodStrategy extends DialogHelper {

    private TemplateMethodStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            //java 文件map
            map = new HashMap<>();
            //从dialog中获取数据
            Map<String, String> dataMap = new HashMap<>();
            //生成抽象类
            Template AbstractClassTemplate = cfg.getTemplate("AbstractClass.ftl");
            Writer AbstractClassOut = new StringWriter();
            AbstractClassTemplate.process(dataMap, AbstractClassOut);
            map.put("AbstractClass", AbstractClassOut.toString());
            //生成具体类1
            Template ConcreteClass1Template = cfg.getTemplate("ConcreteClass1.ftl");
            Writer ConcreteClass1Out = new StringWriter();
            ConcreteClass1Template.process(dataMap, ConcreteClass1Out);
            map.put("ConcreteClass1", ConcreteClass1Out.toString());
            //生成具体类2
            Template ConcreteClass2Template = cfg.getTemplate("ConcreteClass2.ftl");
            Writer ConcreteClass2Out = new StringWriter();
            ConcreteClass2Template.process(dataMap, ConcreteClass2Out);
            map.put("ConcreteClass2", ConcreteClass2Out.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final TemplateMethodStrategy INSTANCE = new TemplateMethodStrategy("templatemethod");
    }

    //对外提供静态方法获取该对象
    public static TemplateMethodStrategy getInstance() {
        return TemplateMethodStrategy.SingletonHolder.INSTANCE;
    }
}
