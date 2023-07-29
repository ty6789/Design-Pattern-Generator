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
import java.util.*;
import java.util.List;


public class CommandDialog extends DialogWrapper {
    private JPanel panel;
    private JTextField abstractCommandField;
    private JTable concreteCommandTable;
    private DefaultTableModel concreteCommandTableModel;
    private JTextField commandInvoker;

    public CommandDialog(Project project) {
        super(project);
        init();
        setTitle("Command Generator");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //抽象命令
        JPanel abstractCommandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        abstractCommandField = new JTextField(15);
        abstractCommandPanel.add(new JLabel("Abstract Command:"));
        abstractCommandPanel.add(abstractCommandField);
        panel.add(abstractCommandPanel);
        //具体命令和对应的接收者
        concreteCommandTableModel = new DefaultTableModel(new Object[]{"Command Name","Receiver Name"}, 0);
        concreteCommandTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (row >= 0 && column >= 0) {  // check valid row and column
                    Vector<Vector> dataVector = concreteCommandTableModel.getDataVector();
                    Vector rowData = dataVector.get(row);
                    // update your vector
                    Object data = concreteCommandTableModel.getValueAt(row, column);
                    rowData.set(column, data);
                }
            }
        });
        concreteCommandTable = new JTable(concreteCommandTableModel);
        JPanel concreteProductsPanel = new JPanel(new BorderLayout());
        concreteProductsPanel.add(new JLabel("Concrete Commands:"), BorderLayout.NORTH);
        concreteProductsPanel.add(new JScrollPane(concreteCommandTable), BorderLayout.CENTER);
        panel.add(concreteProductsPanel);

        JButton addButton = new JButton("Add Command");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个新的对话框
                String commandName = JOptionPane.showInputDialog(panel, "Enter the command name:");
                String receiverName = JOptionPane.showInputDialog(panel, "Enter the receiver name:");
                if (commandName != null && !"".equals(commandName) && receiverName != null && !"".equals(receiverName)) {
                    // 添加到表格中
                    Vector<String> newRow = new Vector<>();
                    newRow.add(commandName);
                    newRow.add(receiverName);
                    concreteCommandTableModel.addRow(newRow);
                }
            }
        });

        JButton deleteButton = new JButton("Delete Command");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取选中的行
                int selectedRow = concreteCommandTable.getSelectedRow();

                // 如果用户有选中一行，删除这一行
                if (selectedRow != -1) {
                    concreteCommandTableModel.removeRow(selectedRow);
                }
            }
        });

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        panel.add(buttonsPanel);
        //命令调用者
        JPanel commandInvokerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        commandInvoker = new JTextField(15);
        commandInvokerPanel.add(new JLabel("Command Invoker:"));
        commandInvokerPanel.add(commandInvoker);
        panel.add(commandInvokerPanel);

        return panel;
    }

    public String getAbstractCommand() {
        return abstractCommandField.getText();
    }

    public Map<String, String> getConcreteCommandAndReceiver() {
        Map<String, String> map = new HashMap<>();
        Vector<Vector> rowData = concreteCommandTableModel.getDataVector();
        for (Vector row : rowData) {
            String commandName = (String) row.get(0);
            String receiverName = (String) row.get(1);
            map.put(commandName, receiverName);
        }
        return map;
    }

    public String getCommandInvoker() {
        return commandInvoker.getText();
    }
}
