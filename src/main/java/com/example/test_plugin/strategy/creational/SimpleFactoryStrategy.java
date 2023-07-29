package com.example.test_plugin.strategy.creational;

import com.example.test_plugin.MyAction;
import com.example.test_plugin.dialog.creational.SimpleFactoryDialog;
import com.example.test_plugin.strategy.GenerateCodeStrategy;
import com.intellij.openapi.ui.DialogWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleFactoryStrategy extends GenerateCodeStrategy {

    private SimpleFactoryStrategy() {}

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            SimpleFactoryDialog dialog = new SimpleFactoryDialog(MyAction.project);
            dialog.setSize(400, 450);
            dialog.pack();
            dialog.show();
            if (dialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
                Map<String, String> map = new HashMap<>();
                String concreteFactory = dialog.getConcreteFactory();
                String abstractProduct = dialog.getAbstractProduct();
                List<String> concreteProducts = dialog.getConcreteProducts();

                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "simplefactory");
                Template template = cfg.getTemplate("concretefactory.ftl");

                Map<String, Object> data = new HashMap<>();
                data.put("FactoryName", concreteFactory);
                data.put("ProductInterface", abstractProduct);
                data.put("productTypes", concreteProducts);

                Writer out = new StringWriter();
                template.process(data, out);
                map.put(concreteFactory, out.toString());

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
            return null;
        }
        return null;
    }

    private static class SingletonHolder {
        private static final SimpleFactoryStrategy INSTANCE = new SimpleFactoryStrategy();
    }

    //对外提供静态方法获取该对象
    public static SimpleFactoryStrategy getInstance() {
        return SimpleFactoryStrategy.SingletonHolder.INSTANCE;
    }
}
