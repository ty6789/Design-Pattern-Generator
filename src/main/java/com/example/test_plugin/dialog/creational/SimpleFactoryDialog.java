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

public class SimpleFactoryDialog extends DialogWrapper {
    private JPanel panel;
    private JTextField abstractProductField;
    private DefaultTableModel concreteProductsTableModel;
    private JTable concreteProductsTable;
    private JTextField concreteFactoryField;

    public SimpleFactoryDialog(Project project) {
        super(project);
        init();
        setTitle("Simple Factory Generator");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel abstractProductPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        abstractProductField = new JTextField(15);
        abstractProductPanel.add(new JLabel("Abstract Product:"));
        abstractProductPanel.add(abstractProductField);
        panel.add(abstractProductPanel);

        concreteProductsTableModel = new DefaultTableModel(new Object[]{"Product Name"}, 0);
        concreteProductsTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (row >= 0 && column >= 0) {  // check valid row and column
                    Vector<Vector> dataVector = concreteProductsTableModel.getDataVector();
                    Vector rowData = dataVector.get(row);
                    // update your vector
                    Object data = concreteProductsTableModel.getValueAt(row, column);
                    rowData.set(column, data);
                }
            }
        });
        concreteProductsTable = new JTable(concreteProductsTableModel);
        JPanel concreteProductsPanel = new JPanel(new BorderLayout());
        concreteProductsPanel.add(new JLabel("Concrete Products:"), BorderLayout.NORTH);
        concreteProductsPanel.add(new JScrollPane(concreteProductsTable), BorderLayout.CENTER);
        panel.add(concreteProductsPanel);

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个新的对话框
                String productName = JOptionPane.showInputDialog(panel, "Enter the product name:");
                if (productName != null && !"".equals(productName)) {
                    // 添加到表格中
                    Vector<String> newRow = new Vector<>();
                    newRow.add(productName);
                    concreteProductsTableModel.addRow(newRow);
                }
            }
        });

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取选中的行
                int selectedRow = concreteProductsTable.getSelectedRow();

                // 如果用户有选中一行，删除这一行
                if (selectedRow != -1) {
                    concreteProductsTableModel.removeRow(selectedRow);
                }
            }
        });

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        panel.add(buttonsPanel);

        JPanel concreteFactoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        concreteFactoryField = new JTextField(15);
        concreteFactoryPanel.add(new JLabel("Concrete Factory:"));
        concreteFactoryPanel.add(concreteFactoryField);
        panel.add(concreteFactoryPanel);

        return panel;
    }

    public String getAbstractProduct() {
        return abstractProductField.getText();
    }

    public List<String> getConcreteProducts() {
        List<String> list = new ArrayList<>();
        Vector<Vector> rowData = concreteProductsTableModel.getDataVector();
        for (Vector row : rowData) {
            String productName = (String) row.get(0);
            list.add(productName);
        }
        return list;
    }

    public String getConcreteFactory() {
        return concreteFactoryField.getText();
    }
}
