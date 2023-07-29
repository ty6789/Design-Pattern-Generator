package com.example.test_plugin.strategy;

import freemarker.template.Configuration;

import java.util.HashMap;
import java.util.Map;

public abstract class MapAndFreemarkerConfig extends GenerateCodeStrategy{

    protected Map<String, String> map;

    protected Configuration cfg;

    protected MapAndFreemarkerConfig(String basePackagePath) {
        //配置freemarker
        cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), basePackagePath);
    }
}
