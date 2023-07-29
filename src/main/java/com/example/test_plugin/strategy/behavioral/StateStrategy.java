package com.example.test_plugin.strategy.behavioral;

import com.example.test_plugin.MyAction;
import com.example.test_plugin.dialog.behavioral.IteratorDialog;
import com.example.test_plugin.strategy.DialogHelper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class StateStrategy extends DialogHelper {

    private StateStrategy(String basePackagePath) {
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
            Template ContextTemplate = cfg.getTemplate("Context.ftl");
            Writer ContextOut = new StringWriter();
            ContextTemplate.process(dataMap, ContextOut);
            map.put("Context", ContextOut.toString());
            //生成抽象状态类
            Template StateTemplate = cfg.getTemplate("State.ftl");
            Writer StateOut = new StringWriter();
            StateTemplate.process(dataMap, StateOut);
            map.put("State", StateOut.toString());
            //生成通电状态
            Template PoweredStateTemplate = cfg.getTemplate("PoweredState.ftl");
            Writer PoweredStateOut = new StringWriter();
            PoweredStateTemplate.process(dataMap, PoweredStateOut);
            map.put("PoweredState", PoweredStateOut.toString());
            //生成未通电状态
            Template UnpoweredStateTemplate = cfg.getTemplate("UnpoweredState.ftl");
            Writer UnpoweredStateOut = new StringWriter();
            UnpoweredStateTemplate.process(dataMap, UnpoweredStateOut);
            map.put("UnpoweredState", UnpoweredStateOut.toString());
            //生成运行状态
            Template RunningStateTemplate = cfg.getTemplate("RunningState.ftl");
            Writer RunningStateOut = new StringWriter();
            RunningStateTemplate.process(dataMap, RunningStateOut);
            map.put("RunningState", RunningStateOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final StateStrategy INSTANCE = new StateStrategy("state");
    }

    //对外提供静态方法获取该对象
    public static StateStrategy getInstance() {
        return StateStrategy.SingletonHolder.INSTANCE;
    }
}
