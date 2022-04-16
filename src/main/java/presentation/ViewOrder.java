package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewOrder extends JFrame {
    private final JTextField QuantityField;
    private final JTable tableClients;
    private final JTable tableProducts;
    private final JTable tableOrders;
    private final JButton btnGoBackToMenu;
    private final JButton btnMakeOrder;

    private final DefaultTableModel modelOrders;

    /**
     * builds the Order interface
     *
     * @param model model for the JTable
     */
    public ViewOrder(DefaultTableModel model) {
        modelOrders = model;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 999, 645);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblOrderOperations = new JLabel("Order Operations");
        lblOrderOperations.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblOrderOperations.setBounds(426, 10, 160, 40);
        contentPane.add(lblOrderOperations);

        btnGoBackToMenu = new JButton("Go back to menu");
        btnGoBackToMenu.setBounds(0, 587, 131, 21);
        contentPane.add(btnGoBackToMenu);
        DefaultTableModel modelClients = new DefaultTableModel();
        tableClients = new JTable(modelClients) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        tableClients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableClients.setFillsViewportHeight(true);
        JScrollPane scrollPaneClients = new JScrollPane(tableClients);
        scrollPaneClients.setBounds(24, 78, 440, 235);
        contentPane.add(scrollPaneClients);

        DefaultTableModel modelProducts = new DefaultTableModel();
        tableProducts = new JTable(modelProducts) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        tableProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableProducts.setFillsViewportHeight(true);
        JScrollPane scrollPaneProducts = new JScrollPane(tableProducts);
        scrollPaneProducts.setBounds(24, 349, 440, 235);
        contentPane.add(scrollPaneProducts);


        tableOrders = new JTable(modelOrders) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        tableOrders.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableOrders.setFillsViewportHeight(true);
        JScrollPane scrollPaneOrders = new JScrollPane(tableOrders);
        scrollPaneOrders.setBounds(492, 212, 471, 372);
        contentPane.add(scrollPaneOrders);

        JLabel lblInsertQuantity = new JLabel("Insert a quantity:");
        lblInsertQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblInsertQuantity.setBounds(601, 92, 125, 26);
        contentPane.add(lblInsertQuantity);

        QuantityField = new JTextField();
        QuantityField.setBounds(736, 98, 96, 19);
        contentPane.add(QuantityField);
        QuantityField.setColumns(10);

        btnMakeOrder = new JButton("Make order");
        btnMakeOrder.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnMakeOrder.setBounds(648, 141, 147, 29);
        contentPane.add(btnMakeOrder);

        JLabel lblClientsTable = new JLabel("Clients table");
        lblClientsTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblClientsTable.setBounds(205, 56, 89, 21);
        contentPane.add(lblClientsTable);

        JLabel lblProductsTable = new JLabel("Products table");
        lblProductsTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProductsTable.setBounds(205, 323, 102, 21);
        contentPane.add(lblProductsTable);

        JLabel lblOrdersTable = new JLabel("Orders table");
        lblOrdersTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOrdersTable.setBounds(683, 192, 102, 21);
        contentPane.add(lblOrdersTable);
    }

    public String getQuantity() {
        return QuantityField.getText();
    }

    public void setQuantity(String s) {
        QuantityField.setText(s);
    }

    public int getSelectedClientRow() {
        return tableClients.getSelectedRow();
    }

    public int getSelectedProductRow() {
        return tableProducts.getSelectedRow();
    }

    public void setBtnGoBackToMenuListener(ActionListener buttonGoBack) {
        btnGoBackToMenu.addActionListener(buttonGoBack);
    }

    public void setModelClients(DefaultTableModel model) {
        tableClients.setModel(model);
    }

    public void setModelProducts(DefaultTableModel model) {
        tableProducts.setModel(model);
    }

    public void setModelOrders(DefaultTableModel model) {
        tableOrders.setModel(model);
    }

    public JTable getTableClients() {
        return tableClients;
    }

    public JTable getTableProducts() {
        return tableProducts;
    }

    public DefaultTableModel getModelOrders() {
        return modelOrders;
    }

    public void makeOrderListener(ActionListener buttonOrder) {
        btnMakeOrder.addActionListener(buttonOrder);
    }

}
