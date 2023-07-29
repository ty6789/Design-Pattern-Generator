package com.example.test_plugin.strategy.structural;

import com.example.test_plugin.dialog.behavioral.MementoDialog;
import com.example.test_plugin.dialog.creational.ProxyDialog;
import com.example.test_plugin.strategy.DialogHelper;
import com.intellij.openapi.ui.DialogWrapper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ProxyStrategy extends DialogHelper {

    private ProxyStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        ProxyDialog dialog = new ProxyDialog();
        dialog.pack();
        dialog.show();
        if (dialog.getExitCode() != DialogWrapper.OK_EXIT_CODE)return null;
        String selectedPattern = dialog.getSelectedPattern();
        if ("Static Proxy".equals(selectedPattern)) {
            return staticProxy();
        }else if ("JDK Dynamic Proxy".equals(selectedPattern)){
            return jdkProxy();
        }else return cglibProxy();
    }

    private Map<String, String> cglibProxy() {
        try {
            //java 文件map
            map = new HashMap<>();
            //从dialog中获取数据
            Map<String, String> dataMap = new HashMap<>();
            //生成发起人
            Template BOriginatorTemplate = cfg.getTemplate("Target.ftl");
            Writer BOriginatorOut = new StringWriter();
            BOriginatorTemplate.process(dataMap, BOriginatorOut);
            map.put("Target", BOriginatorOut.toString());
            //生成管理者
            Template BCaretakerTemplate = cfg.getTemplate("TargetImpl.ftl");
            Writer BCaretakerOut = new StringWriter();
            BCaretakerTemplate.process(dataMap, BCaretakerOut);
            map.put("TargetImpl", BCaretakerOut.toString());
            //生成管理者
            Template ProxyFactoryTemplate = cfg.getTemplate("cglibproxyfactory.ftl");
            Writer ProxyFactoryOut = new StringWriter();
            ProxyFactoryTemplate.process(dataMap, ProxyFactoryOut);
            map.put("ProxyFactory", ProxyFactoryOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private Map<String, String> jdkProxy() {
        try {
            //java 文件map
            map = new HashMap<>();
            //从dialog中获取数据
            Map<String, String> dataMap = new HashMap<>();
            //生成发起人
            Template BOriginatorTemplate = cfg.getTemplate("Target.ftl");
            Writer BOriginatorOut = new StringWriter();
            BOriginatorTemplate.process(dataMap, BOriginatorOut);
            map.put("Target", BOriginatorOut.toString());
            //生成管理者
            Template BCaretakerTemplate = cfg.getTemplate("TargetImpl.ftl");
            Writer BCaretakerOut = new StringWriter();
            BCaretakerTemplate.process(dataMap, BCaretakerOut);
            map.put("TargetImpl", BCaretakerOut.toString());
            //生成管理者
            Template ProxyFactoryTemplate = cfg.getTemplate("ProxyFactory.ftl");
            Writer ProxyFactoryOut = new StringWriter();
            ProxyFactoryTemplate.process(dataMap, ProxyFactoryOut);
            map.put("ProxyFactory", ProxyFactoryOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private Map<String, String> staticProxy() {
        try {
            //java 文件map
            map = new HashMap<>();
            //从dialog中获取数据
            Map<String, String> dataMap = new HashMap<>();
            //生成备忘录接口
            Template MementoIFTemplate = cfg.getTemplate("StaticProxy.ftl");
            Writer MementoIFOut = new StringWriter();
            MementoIFTemplate.process(dataMap, MementoIFOut);
            map.put("StaticProxy", MementoIFOut.toString());
            //生成发起人
            Template BOriginatorTemplate = cfg.getTemplate("Target.ftl");
            Writer BOriginatorOut = new StringWriter();
            BOriginatorTemplate.process(dataMap, BOriginatorOut);
            map.put("Target", BOriginatorOut.toString());
            //生成管理者
            Template BCaretakerTemplate = cfg.getTemplate("TargetImpl.ftl");
            Writer BCaretakerOut = new StringWriter();
            BCaretakerTemplate.process(dataMap, BCaretakerOut);
            map.put("TargetImpl", BCaretakerOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final ProxyStrategy INSTANCE = new ProxyStrategy("proxy");
    }

    //对外提供静态方法获取该对象
    public static ProxyStrategy getInstance() {
        return ProxyStrategy.SingletonHolder.INSTANCE;
    }
}
