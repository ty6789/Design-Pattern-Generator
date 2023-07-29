package com.example.test_plugin.strategy.behavioral;

import com.example.test_plugin.MyAction;
import com.example.test_plugin.dialog.behavioral.CommandDialog;
import com.example.test_plugin.strategy.DialogHelper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class InterpreterStrategy extends DialogHelper {

    private InterpreterStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            //java 文件map
            map = new HashMap<>();
            Map<String, String> dataMap = new HashMap<>();
            //生成抽象表达式
            Template abstractExpressionTemplate = cfg.getTemplate("AbstractExpression.ftl");
            Writer abstractExpressionOut = new StringWriter();
            abstractExpressionTemplate.process(dataMap, abstractExpressionOut);
            map.put("AbstractExpression", abstractExpressionOut.toString());
            //生成数值终结符表达式
            Template valueTemplate = cfg.getTemplate("Value.ftl");
            Writer valueOut = new StringWriter();
            valueTemplate.process(dataMap, valueOut);
            map.put("Value", valueOut.toString());
            //生成变量终结符表达式
            Template variableTemplate = cfg.getTemplate("Variable.ftl");
            Writer variableOut = new StringWriter();
            variableTemplate.process(dataMap, variableOut);
            map.put("Variable", variableOut.toString());
            //生成环境类
            Template contextTemplate = cfg.getTemplate("Context.ftl");
            Writer contextOut = new StringWriter();
            contextTemplate.process(dataMap, contextOut);
            map.put("Context", contextOut.toString());
            //生成加法表达式
            Template addTemplate = cfg.getTemplate("Add.ftl");
            Writer addOut = new StringWriter();
            addTemplate.process(dataMap, addOut);
            map.put("Plus", addOut.toString());
            //生成加法表达式
            Template minusTemplate = cfg.getTemplate("Minus.ftl");
            Writer minusOut = new StringWriter();
            minusTemplate.process(dataMap, minusOut);
            map.put("Minus", minusOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final InterpreterStrategy INSTANCE = new InterpreterStrategy("interpreter");
    }

    //对外提供静态方法获取该对象
    public static InterpreterStrategy getInstance() {
        return InterpreterStrategy.SingletonHolder.INSTANCE;
    }
}
