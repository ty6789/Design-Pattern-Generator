package com.example.test_plugin.strategy.behavioral;

import com.example.test_plugin.MyAction;
import com.example.test_plugin.dialog.behavioral.ChainOfResponsibilityDialog;
import com.example.test_plugin.strategy.GenerateCodeStrategy;
import com.intellij.openapi.ui.DialogWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChainOfResponsibilityStrategy extends GenerateCodeStrategy {

    private ChainOfResponsibilityStrategy() {}

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            ChainOfResponsibilityDialog dialog = new ChainOfResponsibilityDialog(MyAction.project);
            dialog.setSize(400, 450);
            dialog.pack();
            dialog.show();
            if (dialog.getExitCode() != DialogWrapper.OK_EXIT_CODE)return null;
            //java文件map
            Map<String, String> map = new HashMap<>();
            //获取dialog中的数据
            String abstractHandlerName = dialog.getAbstractHandlerName();
            List<String> chainList = dialog.getChainList();
            //配置freemarker
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "chainofresponsibility");
            //生成抽象处理者
            Template abstractHandlerTemplate = cfg.getTemplate("AbstractHandler.ftl");
            Map<String, Object> abstractHandlerData = new HashMap<>();
            abstractHandlerData.put("abstractHandler", abstractHandlerName);
            Writer abstractHandlerOut = new StringWriter();
            abstractHandlerTemplate.process(abstractHandlerData, abstractHandlerOut);
            map.put(abstractHandlerName, abstractHandlerOut.toString());
            //生成具体处理者
            Template concreteHandlerTemplate = cfg.getTemplate("ConcreteHandler.ftl");
            for (int i = 0; i < chainList.size(); i++) {
                Map<String, Object> concreteHandlerData = new HashMap<>();
                concreteHandlerData.put("abstractHandler", abstractHandlerName);
                concreteHandlerData.put("thisHandler", chainList.get(i));
                if (i != chainList.size() - 1)concreteHandlerData.put("nextHandler", chainList.get(i + 1));
                Writer concreteHandlerOut = new StringWriter();
                concreteHandlerTemplate.process(concreteHandlerData, concreteHandlerOut);
                map.put(chainList.get(i), concreteHandlerOut.toString());
            }
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final ChainOfResponsibilityStrategy INSTANCE = new ChainOfResponsibilityStrategy();
    }

    //对外提供静态方法获取该对象
    public static ChainOfResponsibilityStrategy getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
