package com.example.test_plugin.strategy.behavioral;

import com.example.test_plugin.strategy.DialogHelper;
import com.intellij.openapi.ui.DialogWrapper;
import freemarker.template.Template;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class MediatorStrategy extends DialogHelper {

    private MediatorStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            //java 文件map
            map = new HashMap<>();
            Map<String, String> dataMap = new HashMap<>();
            //生成抽象中介者
            Template abstractMediatorTemplate = cfg.getTemplate("Mediator.ftl");
            Writer abstractMediatorOut = new StringWriter();
            abstractMediatorTemplate.process(dataMap, abstractMediatorOut);
            map.put("Mediator", abstractMediatorOut.toString());
            //生成具体中介者
            Template concreteMediatorTemplate = cfg.getTemplate("ConcreteMediator.ftl");
            Writer concreteMediatorOut = new StringWriter();
            concreteMediatorTemplate.process(dataMap, concreteMediatorOut);
            map.put("ConcreteMediator", concreteMediatorOut.toString());
            //生成抽象同事类
            Template colleagueTemplate = cfg.getTemplate("Colleague.ftl");
            Writer colleagueOut = new StringWriter();
            colleagueTemplate.process(dataMap, colleagueOut);
            map.put("Colleague", colleagueOut.toString());
            //生成同事类A
            Template colleagueATemplate = cfg.getTemplate("ColleagueA.ftl");
            Writer colleagueAOut = new StringWriter();
            colleagueATemplate.process(dataMap, colleagueAOut);
            map.put("ColleagueA", colleagueAOut.toString());
            //生成同事类B
            Template colleagueBTemplate = cfg.getTemplate("ColleagueB.ftl");
            Writer colleagueBOut = new StringWriter();
            colleagueBTemplate.process(dataMap, colleagueBOut);
            map.put("ColleagueB", colleagueBOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final MediatorStrategy INSTANCE = new MediatorStrategy("mediator");
    }

    //对外提供静态方法获取该对象
    public static MediatorStrategy getInstance() {
        return MediatorStrategy.SingletonHolder.INSTANCE;
    }
}
