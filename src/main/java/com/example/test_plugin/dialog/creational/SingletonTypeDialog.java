package com.example.test_plugin.dialog.creational;

import com.intellij.openapi.ui.DialogWrapper;
import javax.swing.*;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class SingletonTypeDialog extends DialogWrapper {
    private JPanel contentPane;
    private JComboBox<String> comboBox;
    private JTextField textField;

    public SingletonTypeDialog() {
        super(false);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        comboBox = new JComboBox<>();
        comboBox.addItem("Eager Singleton (Static Variable)");
        comboBox.addItem("Eager Singleton (Static Block)");
        comboBox.addItem("Lazy Singleton (Double-Checked Locking)");
        comboBox.addItem("Lazy Singleton (Static Inner Class)");

        JPanel fileNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 创建新的面板，并设置流布局管理器
        JLabel label = new JLabel("Class Name:"); // 创建提示标签
        textField = new JTextField(21); // 创建一个新的文本输入框，并设置一个适当的列数

        fileNamePanel.add(label);  // 将提示标签添加到新的面板中
        fileNamePanel.add(textField); // 将文本输入框添加到新的面板中

        contentPane.add(comboBox);
        contentPane.add(fileNamePanel); // 添加新的面板到对话框中

        init();
        setTitle("Choose Singleton Pattern Type");
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }

    public String getSelectedPattern() {
        return (String) comboBox.getSelectedItem();
    }

    public String getInputText() {
        return textField.getText();
    }
}

