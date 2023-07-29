package com.example.test_plugin.strategy.behavioral;

import com.example.test_plugin.strategy.DialogHelper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class StrategyStrategy extends DialogHelper {

    private StrategyStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            //java 文件map
            map = new HashMap<>();
            //从dialog中获取数据
            Map<String, String> dataMap = new HashMap<>();
            //生成抽象策略类
            Template StrategyTemplate = cfg.getTemplate("Strategy.ftl");
            Writer StrategyOut = new StringWriter();
            StrategyTemplate.process(dataMap, StrategyOut);
            map.put("Strategy", StrategyOut.toString());
            //生成策略类A
            Template ConcreteStrategyATemplate = cfg.getTemplate("ConcreteStrategyA.ftl");
            Writer ConcreteStrategyAOut = new StringWriter();
            ConcreteStrategyATemplate.process(dataMap, ConcreteStrategyAOut);
            map.put("ConcreteStrategyA", ConcreteStrategyAOut.toString());
            //生成策略类B
            Template ConcreteStrategyBTemplate = cfg.getTemplate("ConcreteStrategyB.ftl");
            Writer ConcreteStrategyBOut = new StringWriter();
            ConcreteStrategyBTemplate.process(dataMap, ConcreteStrategyBOut);
            map.put("ConcreteStrategyB", ConcreteStrategyBOut.toString());
            //生成环境类
            Template ContextTemplate = cfg.getTemplate("Context.ftl");
            Writer ContextOut = new StringWriter();
            ContextTemplate.process(dataMap, ContextOut);
            map.put("Context", ContextOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final StrategyStrategy INSTANCE = new StrategyStrategy("strategy");
    }

    //对外提供静态方法获取该对象
    public static StrategyStrategy getInstance() {
        return StrategyStrategy.SingletonHolder.INSTANCE;
    }
}
