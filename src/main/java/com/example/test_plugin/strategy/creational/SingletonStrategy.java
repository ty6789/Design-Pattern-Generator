package com.example.test_plugin.strategy.creational;

import com.example.test_plugin.dialog.creational.SingletonTypeDialog;
import com.example.test_plugin.strategy.GenerateCodeStrategy;
import com.example.test_plugin.util.FileUtil;
import com.intellij.openapi.ui.DialogWrapper;

import java.util.HashMap;
import java.util.Map;

public class SingletonStrategy extends GenerateCodeStrategy {

    private static String DIR = "singleton";
    private SingletonStrategy() {}

    @Override
    protected Map<String, String> generateCodeForClasses() {
        SingletonTypeDialog dialog = new SingletonTypeDialog();
        dialog.pack();
        dialog.show();
        if (dialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            String pattern = dialog.getSelectedPattern();
            String className = dialog.getInputText();
            if (className == null || className.equals(""))return null;
            //读取code
            String code = FileUtil.readCodeFromFile(DIR, pattern + ".txt");
            code = code.replaceAll("Singleton", className);
            Map<String, String> map = new HashMap<>();
            map.put(className, code);
            return map;
        }
        return null;
    }

    private static class SingletonHolder {
        private static final SingletonStrategy INSTANCE = new SingletonStrategy();
    }

    //对外提供静态方法获取该对象
    public static SingletonStrategy getInstance() {
        return SingletonStrategy.SingletonHolder.INSTANCE;
    }
}
