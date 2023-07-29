package com.example.test_plugin.strategy.creational;

import com.example.test_plugin.MyAction;
import com.example.test_plugin.dialog.creational.AbstractFactoryDialog;
import com.example.test_plugin.strategy.GenerateCodeStrategy;
import com.intellij.openapi.ui.DialogWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractFactoryStrategy extends GenerateCodeStrategy {

    private AbstractFactoryStrategy() {}

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            AbstractFactoryDialog dialog = new AbstractFactoryDialog(MyAction.project);
            dialog.setSize(400, 450);
            dialog.pack();
            dialog.show();
            //判断是否确认
            if (dialog.getExitCode() != DialogWrapper.OK_EXIT_CODE)return null;
            //java文件map
            Map<String, String> map = new HashMap<>();
            //从dialog中获取数据
            String abstractFactory = dialog.getAbstractFactory();
            List<String> productInterfaces = dialog.getProductInterfaces();
            Map<String, Map<String, String>> concreteFactories = dialog.getConcreteFactories();

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "abstractfactory");
            //生成抽象工厂
            Template template = cfg.getTemplate("AbstractFactory.ftl");
            Map<String, Object> abstractFactoryData = new HashMap<>();
            abstractFactoryData.put("abstractFactory", abstractFactory);
            abstractFactoryData.put("productInterfaces", productInterfaces);
            Writer abstractFactoryOut = new StringWriter();
            template.process(abstractFactoryData, abstractFactoryOut);
            map.put(abstractFactory, abstractFactoryOut.toString());
            //生成具体工厂
            Template concreteFactoryTemplate = cfg.getTemplate("ConcreteFactory.ftl");
            for (String concreteFactoryName : concreteFactories.keySet()) {
                Map<String, Object> concreteFactoryMap = new HashMap<>();
                concreteFactoryMap.put("products", concreteFactories.get(concreteFactoryName));
                concreteFactoryMap.put("abstractFactoryName", abstractFactory);
                concreteFactoryMap.put("concreteFactoryName", concreteFactoryName);
                Writer concreteFactoryOut = new StringWriter();
                concreteFactoryTemplate.process(concreteFactoryMap, concreteFactoryOut);
                map.put(concreteFactoryName, concreteFactoryOut.toString());
            }
            //生成抽象产品
            Template abstractProductTemplate = cfg.getTemplate("AbstractProduct.ftl");
            for (String productInterface : productInterfaces) {
                Map<String, Object> abstractProductData = new HashMap<>();
                abstractProductData.put("ProductInterface", productInterface);
                Writer abstractProductOut = new StringWriter();
                abstractProductTemplate.process(abstractProductData, abstractProductOut);
                map.put(productInterface, abstractProductOut.toString());
            }
            //生成具体产品
            Template concreteProductTemplate = cfg.getTemplate("ConcreteProduct.ftl");
            for (Map<String, String> productMap : concreteFactories.values()) {
                for (String interfaceName : productMap.keySet()) {
                    String value = productMap.get(interfaceName);
                    Map<String, Object> concreteProductData = new HashMap<>();
                    concreteProductData.put("ProductType", value);
                    concreteProductData.put("ProductInterface", interfaceName);
                    Writer concreteProductOut = new StringWriter();
                    concreteProductTemplate.process(concreteProductData, concreteProductOut);
                    map.put(value, concreteProductOut.toString());
                }
            }
            return map;
        }catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final AbstractFactoryStrategy INSTANCE = new AbstractFactoryStrategy();
    }

    //对外提供静态方法获取该对象
    public static AbstractFactoryStrategy getInstance() {
        return AbstractFactoryStrategy.SingletonHolder.INSTANCE;
    }
}
