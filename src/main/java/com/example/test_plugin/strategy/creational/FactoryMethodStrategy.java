package com.example.test_plugin.strategy.creational;

import com.example.test_plugin.MyAction;
import com.example.test_plugin.dialog.creational.FactoryMethodDialog;
import com.example.test_plugin.strategy.GenerateCodeStrategy;
import com.intellij.openapi.ui.DialogWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactoryMethodStrategy extends GenerateCodeStrategy {

    private FactoryMethodStrategy() {}

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            FactoryMethodDialog dialog = new FactoryMethodDialog(MyAction.project);
            dialog.setSize(400, 450);
            dialog.pack();
            dialog.show();
            if (dialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
                Map<String, String> map = new HashMap<>();
                String abstractFactory = dialog.getAbstractFactory();
                String abstractProduct = dialog.getAbstractProduct();
                List<String> concreteProducts = dialog.getConcreteProducts();

                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "factorymethod");
                Template template = cfg.getTemplate("AbstractFactory.ftl");

                Map<String, Object> data = new HashMap<>();
                data.put("FactoryName", abstractFactory);
                data.put("ProductInterface", abstractProduct);

                Writer out = new StringWriter();
                template.process(data, out);
                map.put(abstractFactory, out.toString());

                //Generate the concrete factory.
                Template concreteFactoryTemplate = cfg.getTemplate("ConcreteFactory.ftl");
                for (int i = 0; i < concreteProducts.size(); i++) {
                    String concreteProduct = concreteProducts.get(i);
                    //判断类名是否合法
                    String regex = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
                    if (!concreteProduct.matches(regex))continue;

                    Map<String, Object> concreteFactoryData = new HashMap<>();
                    concreteFactoryData.put("productType", concreteProduct);
                    concreteFactoryData.put("ProductInterface", abstractProduct);
                    concreteFactoryData.put("FactoryName", abstractFactory);

                    Writer concreteFactoryOut = new StringWriter();
                    concreteFactoryTemplate.process(concreteFactoryData, concreteFactoryOut);
                    map.put(concreteProduct + "Factory", concreteFactoryOut.toString());
                }

                // Generate the abstract product.
                Template abstractProductTemplate = cfg.getTemplate("AbstractProduct.ftl");
                Map<String, Object> abstractProductData = new HashMap<>();
                abstractProductData.put("ProductInterface", abstractProduct);
                Writer abstractProductOut = new StringWriter();
                abstractProductTemplate.process(abstractProductData, abstractProductOut);
                map.put(abstractProduct, abstractProductOut.toString());

                // Generate the concrete products.
                Template concreteProductTemplate = cfg.getTemplate("ConcreteProduct.ftl");
                for (String productType : concreteProducts) {
                    Map<String, Object> concreteProductData = new HashMap<>();
                    concreteProductData.put("ProductInterface", abstractProduct);
                    concreteProductData.put("ProductType", productType);
                    Writer concreteProductOut = new StringWriter();
                    concreteProductTemplate.process(concreteProductData, concreteProductOut);
                    map.put(productType, concreteProductOut.toString());
                }
                return map;
            }
        }catch (Exception e) {
            //do nothing
        }
        return null;
    }

    private static class SingletonHolder {
        private static final FactoryMethodStrategy INSTANCE = new FactoryMethodStrategy();
    }

    //对外提供静态方法获取该对象
    public static FactoryMethodStrategy getInstance() {
        return FactoryMethodStrategy.SingletonHolder.INSTANCE;
    }
}
