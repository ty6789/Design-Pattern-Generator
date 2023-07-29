package com.example.test_plugin.dialog.creational;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class ProxyDialog extends DialogWrapper {
    private JPanel contentPane;
    private JComboBox<String> comboBox;

    public ProxyDialog() {
        super(false);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        comboBox = new JComboBox<>();
        comboBox.addItem("Static Proxy");
        comboBox.addItem("JDK Dynamic Proxy");
        comboBox.addItem("CGLIB Dynamic Proxy");

        contentPane.add(comboBox);

        init();
        setTitle("Choose Proxy Type");
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }

    public String getSelectedPattern() {
        return (String) comboBox.getSelectedItem();
    }

}

