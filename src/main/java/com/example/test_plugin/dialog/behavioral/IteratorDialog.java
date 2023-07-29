package com.example.test_plugin.dialog.behavioral;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;

import javax.annotation.Nullable;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class IteratorDialog extends DialogWrapper {
    private JPanel panel;
    private JTextField elementField;

    public IteratorDialog(Project project) {
        super(project);
        init();
        setTitle("Iterator Generator");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //元素类
        JPanel elementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        elementField = new JTextField(15);
        elementPanel.add(new JLabel("Element:"));
        elementPanel.add(elementField);
        panel.add(elementPanel);
        return panel;
    }

    public String getElement() {
        return elementField.getText();
    }
}
