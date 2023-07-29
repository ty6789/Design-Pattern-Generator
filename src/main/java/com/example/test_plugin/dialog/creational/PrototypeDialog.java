package com.example.test_plugin.dialog.creational;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class PrototypeDialog extends DialogWrapper {
    private JPanel contentPane;
    private JTextField textField;

    public PrototypeDialog() {
        super(false);
        contentPane = new JPanel();

        JPanel fileNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 创建新的面板，并设置流布局管理器
        JLabel label = new JLabel("Class Name:"); // 创建提示标签
        textField = new JTextField(21); // 创建一个新的文本输入框，并设置一个适当的列数

        fileNamePanel.add(label);  // 将提示标签添加到新的面板中
        fileNamePanel.add(textField); // 将文本输入框添加到新的面板中

        contentPane.add(fileNamePanel); // 添加新的面板到对话框中

        init();
        setTitle("Choose Class Name");
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }

    public String getInputText() {
        return textField.getText();
    }
}

