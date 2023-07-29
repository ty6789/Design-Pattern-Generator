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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ChainOfResponsibilityDialog extends DialogWrapper {

    private JPanel panel;
    private JTextField abstractHandlerField;
    private DefaultTableModel concreteHandlerModel;
    private JTable concreteHandlerTable;

    public ChainOfResponsibilityDialog(Project project) {
        super(project);
        init();
        setTitle("ChainOfResponsibility Generator");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel abstractHandler = new JPanel(new FlowLayout(FlowLayout.LEFT));
        abstractHandlerField = new JTextField(15);
        abstractHandler.add(new JLabel("Abstract Handler:"));
        abstractHandler.add(abstractHandlerField);
        panel.add(abstractHandler);

        concreteHandlerModel = new DefaultTableModel(new Object[]{"Handler Name (By Order)"}, 0);
        concreteHandlerModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (row >= 0 && column >= 0) {  // check valid row and column
                    Vector<Vector> dataVector = concreteHandlerModel.getDataVector();
                    Vector rowData = dataVector.get(row);
                    // update your vector
                    Object data = concreteHandlerModel.getValueAt(row, column);
                    rowData.set(column, data);
                }
            }
        });
        concreteHandlerTable = new JTable(concreteHandlerModel);
        JPanel concreteProductsPanel = new JPanel(new BorderLayout());
        concreteProductsPanel.add(new JLabel("Concrete Handler Chain:"), BorderLayout.NORTH);
        concreteProductsPanel.add(new JScrollPane(concreteHandlerTable), BorderLayout.CENTER);
        panel.add(concreteProductsPanel);

        JButton addButton = new JButton("Add Handler");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个新的对话框
                String productName = JOptionPane.showInputDialog(panel, "Enter the handler name:");
                if (productName != null && !"".equals(productName)) {
                    // 添加到表格中
                    Vector<String> newRow = new Vector<>();
                    newRow.add(productName);
                    concreteHandlerModel.addRow(newRow);
                }
            }
        });

        JButton deleteButton = new JButton("Delete Handler");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取选中的行
                int selectedRow = concreteHandlerTable.getSelectedRow();

                // 如果用户有选中一行，删除这一行
                if (selectedRow != -1) {
                    concreteHandlerModel.removeRow(selectedRow);
                }
            }
        });

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        panel.add(buttonsPanel);

        return panel;
    }

    public String getAbstractHandlerName() {
        return abstractHandlerField.getText();
    }

    public List<String> getChainList() {
        List<String> list = new ArrayList<>();
        Vector<Vector> rowData = concreteHandlerModel.getDataVector();
        for (Vector row : rowData) {
            String handlerName = (String) row.get(0);
            list.add(handlerName);
        }
        return list;
    }

}
