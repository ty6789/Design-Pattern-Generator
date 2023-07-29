package com.example.test_plugin.strategy.behavioral;

import com.example.test_plugin.strategy.DialogHelper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class VisitorStrategy extends DialogHelper {

    private VisitorStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            //java 文件map
            map = new HashMap<>();
            //从dialog中获取数据
            Map<String, String> dataMap = new HashMap<>();
            //生成环境角色类
            Template ContextTemplate = cfg.getTemplate("ConcreteElementA.ftl");
            Writer ContextOut = new StringWriter();
            ContextTemplate.process(dataMap, ContextOut);
            map.put("ConcreteElementA", ContextOut.toString());
            //生成抽象状态类
            Template StateTemplate = cfg.getTemplate("ConcreteElementB.ftl");
            Writer StateOut = new StringWriter();
            StateTemplate.process(dataMap, StateOut);
            map.put("ConcreteElementB", StateOut.toString());
            //生成通电状态
            Template PoweredStateTemplate = cfg.getTemplate("ConcreteVisitor1.ftl");
            Writer PoweredStateOut = new StringWriter();
            PoweredStateTemplate.process(dataMap, PoweredStateOut);
            map.put("ConcreteVisitor1", PoweredStateOut.toString());
            //生成未通电状态
            Template UnpoweredStateTemplate = cfg.getTemplate("ConcreteVisitor2.ftl");
            Writer UnpoweredStateOut = new StringWriter();
            UnpoweredStateTemplate.process(dataMap, UnpoweredStateOut);
            map.put("ConcreteVisitor2", UnpoweredStateOut.toString());
            //生成运行状态
            Template RunningStateTemplate = cfg.getTemplate("Element.ftl");
            Writer RunningStateOut = new StringWriter();
            RunningStateTemplate.process(dataMap, RunningStateOut);
            map.put("Element", RunningStateOut.toString());
            //生成运行状态
            Template ObjectStructureTemplate = cfg.getTemplate("ObjectStructure.ftl");
            Writer ObjectStructureOut = new StringWriter();
            ObjectStructureTemplate.process(dataMap, ObjectStructureOut);
            map.put("ObjectStructure", ObjectStructureOut.toString());
            //生成运行状态
            Template VisitorTemplate = cfg.getTemplate("Visitor.ftl");
            Writer VisitorOut = new StringWriter();
            VisitorTemplate.process(dataMap, VisitorOut);
            map.put("Visitor", VisitorOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final VisitorStrategy INSTANCE = new VisitorStrategy("visitor");
    }

    //对外提供静态方法获取该对象
    public static VisitorStrategy getInstance() {
        return VisitorStrategy.SingletonHolder.INSTANCE;
    }
}
