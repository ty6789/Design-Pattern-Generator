package com.example.test_plugin.dialog.other;

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
import java.util.*;

public class AbstractFactoryOtherDialog extends DialogWrapper {
    private JPanel panel;
    private JTextField factoryField;
    private DefaultTableModel productsTableModel;
    private JTable productsTable;

    public AbstractFactoryOtherDialog(Project project) {
        super(project);
        init();
        setTitle("Abstract Factory Generator");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel factoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        factoryField = new JTextField(15);
        factoryPanel.add(new JLabel("Concrete Factory:"));
        factoryPanel.add(factoryField);
        panel.add(factoryPanel);

        productsTableModel = new DefaultTableModel(new Object[]{"Abstract Product", "Concrete Product"}, 0);
        productsTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (row >= 0 && column >= 0) {  // check valid row and column
                    Vector<Vector> dataVector = productsTableModel.getDataVector();
                    Vector rowData = dataVector.get(row);
                    // update your vector
                    Object data = productsTable.getValueAt(row, column);
                    rowData.set(column, data);
                }
            }
        });
        productsTable = new JTable(productsTableModel);
        JPanel productsPanel = new JPanel(new BorderLayout());
        productsPanel.add(new JLabel("Products:"), BorderLayout.NORTH);
        productsPanel.add(new JScrollPane(productsTable), BorderLayout.CENTER);
        panel.add(productsPanel);

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个新的对话框
                String concreteProduct = JOptionPane.showInputDialog(panel, "Enter the abstract product name:");
                String abstractProduct = JOptionPane.showInputDialog(panel, "Enter the concrete product name:");
                if (concreteProduct != null && !"".equals(concreteProduct) && abstractProduct != null && !"".equals(abstractProduct)) {
                    // 添加到表格中
                    Vector<String> newRow = new Vector<>();
                    newRow.add(concreteProduct);
                    newRow.add(abstractProduct);
                    productsTableModel.addRow(newRow);
                }
            }
        });

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取选中的行
                int selectedRow = productsTable.getSelectedRow();

                // 如果用户有选中一行，删除这一行
                if (selectedRow != -1) {
                    productsTableModel.removeRow(selectedRow);
                }
            }
        });
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        panel.add(buttonsPanel);

        return panel;
    }

    public String getFactoryName() {
        return factoryField.getText();
    }

    public Map<String, String> getProducts() {
        Map<String, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        Vector<Vector> rowData = productsTableModel.getDataVector();
        for (Vector row : rowData) {
            String abstractProduct = (String) row.get(0);
            String concreteProduct = (String) row.get(1);
            String regex = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
            if (abstractProduct == null || !abstractProduct.matches(regex) || concreteProduct == null || !concreteProduct.matches(regex))continue;
            if (set.contains(concreteProduct)) {
                Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    if (entry.getValue().equals(concreteProduct)) {
                        iterator.remove(); // 安全地删除元素
                        map.put(abstractProduct, concreteProduct);
                        break;
                    }
                }
            }else {
                set.add(concreteProduct);
                map.put(abstractProduct, concreteProduct);
            }
        }
        return map;
    }
}
