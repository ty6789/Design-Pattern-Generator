package com.example.test_plugin.strategy;

import com.example.test_plugin.MyAction;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFileManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class GenerateCodeStrategy {

    // 模板方法
    public final void generateCode(String directory) {
        Map<String, String> codeFiles = generateCodeForClasses();
        if (codeFiles == null || codeFiles.isEmpty())return;
        for (Map.Entry<String, String> entry : codeFiles.entrySet()) {
            String fileName = entry.getKey();
            String code = entry.getValue();
            //判断类名是否合法
            String regex = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
            if (!fileName.matches(regex))continue;
            if (!"".equals(MyAction.packageName)) {
                code = code.replace("11package", MyAction.packageName);
            }else {
                code = code.substring(19);
            }
            //生成文件
            saveCodeToFile(directory, fileName, code);
        }
        fresh(directory);
    }

    private void fresh(String directoryPath) {
        VirtualFileManager.getInstance().syncRefresh();
    }

    // 各个步骤的方法
    protected abstract Map<String, String> generateCodeForClasses();

    protected void saveCodeToFile(String directory, String className, String code) {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, className + ".java");
        while (file.exists()) {
            className = className + "_";
            file = new File(dir, className + ".java");
        }
        try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            printWriter.write(code);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
