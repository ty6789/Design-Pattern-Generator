package com.example.test_plugin.factory;

import com.example.test_plugin.strategy.GenerateCodeStrategy;
import com.example.test_plugin.strategy.creational.SingletonStrategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DesignPatternStrategyFactory {

    private Map<String, GenerateCodeStrategy> strategyMap = new HashMap<>();

    private Properties prop = new Properties();

    public DesignPatternStrategyFactory() {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("strategy.properties")) {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load design pattern strategies", e);
        }
    }

    public GenerateCodeStrategy getStrategy(String patternName) {
        GenerateCodeStrategy strategy = null;
        try {
            strategy = strategyMap.get(patternName);
            if (strategy == null) {
                String className = prop.getProperty(patternName);
                Class<?> clazz = Class.forName(className);
                strategy = (GenerateCodeStrategy) clazz.getDeclaredMethod("getInstance").invoke(null);
                strategyMap.put(patternName, strategy);
            }
        }catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new IllegalArgumentException("Invalid design pattern: " + patternName);
        }
        return strategy;
    }
}
