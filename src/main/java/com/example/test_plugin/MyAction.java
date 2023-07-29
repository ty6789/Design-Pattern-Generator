package com.example.test_plugin;

import com.example.test_plugin.common.Categories;
import com.example.test_plugin.factory.DesignPatternStrategyFactory;
import com.example.test_plugin.strategy.GenerateCodeStrategy;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.IconUtil;

import javax.swing.*;


public class MyAction extends AbstractPackageNameAction {

    private DesignPatternStrategyFactory factory = new DesignPatternStrategyFactory();

    public MyAction() {
        super(IconUtil.scale(IconLoader.getIcon("dumpster-fire.png"), null, 0.02f));
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        applyPackageName(e);
        if (!"".equals(packageName)) packageName = packageName.replace("/", ".") + ";";
        String category = firstOptionPane();
        if (category == null || "".equals(category))return;
        // 根据用户选择的类别，创建一个包含对应设计模式的数组
        String[] patterns;
        switch (category) {
            case "Structural":
                patterns = Categories.structuralPatterns;
                break;
            case "Creational":
                patterns = Categories.creationalPatterns;
                break;
            case "Behavioral":
                patterns = Categories.behavioralPatterns;
                break;
            default:
                return;
        }
        // 创建一个新的选择框让用户选择具体的设计模式
        String pattern = secondOptionPane(patterns);
        if (pattern == null || "".equals(pattern))return;
        if (path == null) return;
        GenerateCodeStrategy strategy = factory.getStrategy(pattern);
        strategy.generateCode(path);
    }


    private String firstOptionPane() {
         return (String) JOptionPane.showInputDialog(
                null,
                "Choose a category:",
                "Design Patterns",
                JOptionPane.QUESTION_MESSAGE,
                IconUtil.scale(IconLoader.getIcon("dumpster-fire.png"), null, 0.08f),
                Categories.categories,
                Categories.categories[0]
        );
    }

    private String secondOptionPane(String[] patterns) {
        return (String) JOptionPane.showInputDialog(
                null,
                "Choose a design pattern:",
                "Design Patterns",
                JOptionPane.QUESTION_MESSAGE,
                IconUtil.scale(IconLoader.getIcon("dumpster-fire.png"), null, 0.08f),
                patterns,
                patterns[0]
        );
    }


}
