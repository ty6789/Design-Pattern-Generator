package com.example.test_plugin.dialog.creational;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BuilderDialog extends DialogWrapper {
    private JPanel panel;
    private JTextField targetProductField;
    private DefaultTableModel componentTableModel;
    private JTable componentTable;

    public BuilderDialog(Project project) {
        super(project);
        init();
        setTitle("Design the Product");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel abstractProductPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        targetProductField = new JTextField(15);
        abstractProductPanel.add(new JLabel("Target Product:"));
        abstractProductPanel.add(targetProductField);
        panel.add(abstractProductPanel);

        componentTableModel = new DefaultTableModel(new Object[]{"Component Name"}, 0);
        componentTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (row >= 0 && column >= 0) {  // check valid row and column
                    Vector<Vector> dataVector = componentTableModel.getDataVector();
                    Vector rowData = dataVector.get(row);
                    // update your vector
                    Object data = componentTableModel.getValueAt(row, column);
                    rowData.set(column, data);
                }
            }
        });
        componentTable = new JTable(componentTableModel);
        JPanel concreteProductsPanel = new JPanel(new BorderLayout());
        concreteProductsPanel.add(new JLabel("Product Components:"), BorderLayout.NORTH);
        concreteProductsPanel.add(new JScrollPane(componentTable), BorderLayout.CENTER);
        panel.add(concreteProductsPanel);

        JButton addButton = new JButton("Add Component");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个新的对话框
                String productName = JOptionPane.showInputDialog(panel, "Enter the component name:");
                if (productName != null && !"".equals(productName)) {
                    // 添加到表格中
                    Vector<String> newRow = new Vector<>();
                    newRow.add(productName);
                    componentTableModel.addRow(newRow);
                }
            }
        });

        JButton deleteButton = new JButton("Delete Component");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取选中的行
                int selectedRow = componentTable.getSelectedRow();

                // 如果用户有选中一行，删除这一行
                if (selectedRow != -1) {
                    componentTableModel.removeRow(selectedRow);
                }
            }
        });

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        panel.add(buttonsPanel);

        return panel;
    }

    public String getTargetProduct() {
        return targetProductField.getText();
    }

    public List<String> getComponents() {
        List<String> list = new ArrayList<>();
        Vector<Vector> rowData = componentTableModel.getDataVector();
        for (Vector row : rowData) {
            String componentName = (String) row.get(0);
            componentName = componentName.substring(0, 1).toUpperCase() + componentName.substring(1);
            list.add(componentName);
        }
        return list;
    }
}
