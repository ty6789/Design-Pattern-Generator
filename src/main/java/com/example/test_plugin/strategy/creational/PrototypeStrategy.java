package com.example.test_plugin.strategy.creational;

import com.example.test_plugin.dialog.creational.PrototypeDialog;
import com.example.test_plugin.strategy.GenerateCodeStrategy;
import com.example.test_plugin.util.FileUtil;
import com.intellij.openapi.ui.DialogWrapper;

import java.util.HashMap;
import java.util.Map;

public class PrototypeStrategy extends GenerateCodeStrategy {

    public static String DIR = "prototype";

    private PrototypeStrategy() {}

    @Override
    protected Map<String, String> generateCodeForClasses() {
        Map<String, String> result = new HashMap<>();
        PrototypeDialog prototypeDialog = new PrototypeDialog();
        prototypeDialog.pack();
        prototypeDialog.show();
        if (prototypeDialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            String className = prototypeDialog.getInputText();
            if (className == null || className.equals(""))return null;
            //读取code
            String code = FileUtil.readCodeFromFile(DIR,   "prototype.txt");
            code = code.replace("${className}", className);
            Map<String, String> map = new HashMap<>();
            map.put(className, code);
            return map;
        }
        return null;
    }


    private static class SingletonHolder {
        private static final PrototypeStrategy INSTANCE = new PrototypeStrategy();
    }

    //对外提供静态方法获取该对象
    public static PrototypeStrategy getInstance() {
        return PrototypeStrategy.SingletonHolder.INSTANCE;
    }
}
