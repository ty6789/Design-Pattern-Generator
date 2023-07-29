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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class BuilderOtherDialog extends DialogWrapper {

    private JPanel panel;
    private JTextField targetProductField;
    private DefaultTableModel componentTableModel;
    private JTable componentTable;

    public BuilderOtherDialog(Project project) {
        super(project);
        init();
        setTitle("Design Builder Type");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        componentTableModel = new DefaultTableModel(new Object[]{"Type Name"}, 0);
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
        concreteProductsPanel.add(new JLabel("Builder Types:"), BorderLayout.NORTH);
        concreteProductsPanel.add(new JScrollPane(componentTable), BorderLayout.CENTER);
        panel.add(concreteProductsPanel);

        JButton addButton = new JButton("Add Type");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个新的对话框
                String productName = JOptionPane.showInputDialog(panel, "Enter the type name:");
                if (productName != null && !"".equals(productName)) {
                    // 添加到表格中
                    Vector<String> newRow = new Vector<>();
                    newRow.add(productName);
                    componentTableModel.addRow(newRow);
                }
            }
        });

        JButton deleteButton = new JButton("Delete Type");
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

    public List<String> getTypes() {
        List<String> list = new ArrayList<>();
        Vector<Vector> rowData = componentTableModel.getDataVector();
        for (Vector row : rowData) {
            String typeName = (String) row.get(0);
            if (!typeName.endsWith("Builder"))typeName = typeName.concat("Builder");
            typeName = typeName.substring(0, 1).toUpperCase() + typeName.substring(1);
            list.add(typeName);
        }
        return list;
    }

}
