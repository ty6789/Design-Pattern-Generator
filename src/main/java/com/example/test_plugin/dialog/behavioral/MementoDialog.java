package com.example.test_plugin.dialog.behavioral;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MementoDialog extends DialogWrapper {

    private JPanel contentPane;
    private JComboBox<String> comboBox;
    private JTextField textField;

    public MementoDialog() {
        super(false);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        comboBox = new JComboBox<>();
        comboBox.addItem("White-Box Memento");
        comboBox.addItem("Black-Box Memento");

        contentPane.add(comboBox);
        init();
        setTitle("Choose Memento Type");
    }

    public String getSelectedPattern() {
        return (String) comboBox.getSelectedItem();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return contentPane;
    }
}
