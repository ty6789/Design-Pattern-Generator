package com.example.test_plugin.strategy.creational;

import com.example.test_plugin.MyAction;
import com.example.test_plugin.dialog.creational.BuilderDialog;
import com.example.test_plugin.dialog.other.BuilderOtherDialog;
import com.example.test_plugin.strategy.GenerateCodeStrategy;
import com.intellij.openapi.ui.DialogWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilderStrategy extends GenerateCodeStrategy {

    private BuilderStrategy() {}

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            BuilderDialog builderDialog = new BuilderDialog(MyAction.project);
            builderDialog.setSize(400, 450);
            builderDialog.pack();
            builderDialog.show();
            if (builderDialog.getExitCode() != DialogWrapper.OK_EXIT_CODE)return null;
            BuilderOtherDialog builderOtherDialog = new BuilderOtherDialog(MyAction.project);
            builderOtherDialog.setSize(400, 450);
            builderOtherDialog.pack();
            builderOtherDialog.show();
            if (builderOtherDialog.getExitCode() != DialogWrapper.OK_EXIT_CODE)return null;
            //从dialog中获取数据
            String targetProduct = builderDialog.getTargetProduct();
            List<String> components = builderDialog.getComponents();
            List<String> types = builderOtherDialog.getTypes();
            //java文件map
            Map<String, String> map = new HashMap<>();

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "builder");
            //生成目标产品
            Template targetProductTemplate = cfg.getTemplate("targetProduct.ftl");
            Map<String, Object> targetProductData = new HashMap<>();
            targetProductData.put("targetProduct", targetProduct);
            Writer targetProductOut = new StringWriter();
            targetProductTemplate.process(targetProductData, targetProductOut);
            map.put(targetProduct, targetProductOut.toString());
            //生成抽象Builder类
            Template abstractBuilderTemplate = cfg.getTemplate("abstractBuilder.ftl");
            Map<String, Object> abstractBuilderData = new HashMap<>();
            abstractBuilderData.put("targetProduct", targetProduct);
            abstractBuilderData.put("components", components);
            Writer abstractBuilderOut = new StringWriter();
            abstractBuilderTemplate.process(abstractBuilderData, abstractBuilderOut);
            map.put(targetProduct + "Builder", abstractBuilderOut.toString());
            //生成具体Builder类
            Template builderTypeTemplate = cfg.getTemplate("BuilderType.ftl");
            for (int i = 0; i < types.size(); i++) {
                Map<String, Object> builderTypeData = new HashMap<>();
                builderTypeData.put("targetProduct", targetProduct);
                builderTypeData.put("components", components);
                builderTypeData.put("type", types.get(i));
                Writer builderTypeOut = new StringWriter();
                builderTypeTemplate.process(builderTypeData, builderTypeOut);
                map.put(types.get(i), builderTypeOut.toString());
            }
            //生成指挥者
            Template directorTemplate = cfg.getTemplate("director.ftl");
            Map<String, Object> directData = new HashMap<>();
            directData.put("targetProduct", targetProduct);
            directData.put("components", components);
            Writer directorOut = new StringWriter();
            directorTemplate.process(directData, directorOut);
            map.put(targetProduct + "BuilderDirector", directorOut.toString());
            return map;
        }catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final BuilderStrategy INSTANCE = new BuilderStrategy();
    }

    //对外提供静态方法获取该对象
    public static BuilderStrategy getInstance() {
        return BuilderStrategy.SingletonHolder.INSTANCE;
    }
}
