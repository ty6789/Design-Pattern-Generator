package com.example.test_plugin.dialog.creational;

import com.example.test_plugin.MyAction;
import com.example.test_plugin.dialog.other.AbstractFactoryOtherDialog;
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
import java.util.List;

public class AbstractFactoryDialog extends DialogWrapper {
    private JPanel panel;
    private JTextField abstractFactoryField;
    private DefaultTableModel concreteProductsTableModel;
    private JTable concreteProductsTable;
    private Map<String, Map<String, String>> concreteFactoryMap = new HashMap<>();

    public AbstractFactoryDialog(Project project) {
        super(project);
        init();
        setTitle("Abstract Factory Generator");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel abstractProductPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        abstractFactoryField = new JTextField(15);
        abstractProductPanel.add(new JLabel("Abstract Factory:"));
        abstractProductPanel.add(abstractFactoryField);
        panel.add(abstractProductPanel);

        concreteProductsTableModel = new DefaultTableModel(new Object[]{"Concrete Factory Name"}, 0);
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
        concreteProductsPanel.add(new JLabel("Concrete Factories:"), BorderLayout.NORTH);
        concreteProductsPanel.add(new JScrollPane(concreteProductsTable), BorderLayout.CENTER);
        panel.add(concreteProductsPanel);

        JButton addButton = new JButton("Add Factory");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractFactoryOtherDialog abstractFactoryOtherDialog = new AbstractFactoryOtherDialog(MyAction.project);
                abstractFactoryOtherDialog.setSize(400, 450);
                abstractFactoryOtherDialog.pack();
                if (abstractFactoryOtherDialog.showAndGet()) {
                    String concreteFactoryName = abstractFactoryOtherDialog.getFactoryName();
                    Map<String, String> products = abstractFactoryOtherDialog.getProducts();
                    concreteFactoryMap.put(concreteFactoryName, products);
                    // 添加到表格中
                    Vector<String> newRow = new Vector<>();
                    newRow.add(concreteFactoryName);
                    concreteProductsTableModel.addRow(newRow);
                }
            }
        });

        JButton deleteButton = new JButton("Delete Factory");
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

        return panel;
    }

    public Map<String, Map<String, String>> getConcreteFactories() {
        return concreteFactoryMap;
    }

    public String getAbstractFactory() {
        return abstractFactoryField.getText();
    }

    public List<String> getProductInterfaces() {
        List<String> list = new ArrayList<>();
        for (Map<String, String> map : concreteFactoryMap.values()) {
            for (String value : map.keySet()) {
                if (!list.contains(value)){
                    list.add(value);
                }
            }
        }
        return list;
    }
}
