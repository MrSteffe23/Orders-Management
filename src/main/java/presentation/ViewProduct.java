package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewProduct extends JFrame {
    private final JTable table;
    private final JTextField textName;
    private final JTextField textPrice;
    private final JTextField textStock;
    private final JButton btnAdd;
    private final JButton btnEdit;
    private final JButton btnDelete;
    private final JButton btnGoBackToMenu;

    private final DefaultTableModel model;

    /**
     * builds the Product interface
     *
     * @param Model model for the JTable
     */
    public ViewProduct(DefaultTableModel Model) {
        model = Model;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 803, 630);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblProductOperations = new JLabel("Product Operations");
        lblProductOperations.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblProductOperations.setBounds(305, 5, 172, 75);
        contentPane.add(lblProductOperations);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblName.setBounds(34, 124, 64, 35);
        contentPane.add(lblName);

        textName = new JTextField();
        textName.setBounds(94, 130, 172, 28);
        contentPane.add(textName);
        textName.setColumns(10);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrice.setBounds(34, 175, 62, 28);
        contentPane.add(lblPrice);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblStock.setBounds(34, 226, 50, 28);
        contentPane.add(lblStock);

        textPrice = new JTextField();
        textPrice.setColumns(10);
        textPrice.setBounds(94, 178, 172, 28);
        contentPane.add(textPrice);

        textStock = new JTextField();
        textStock.setColumns(10);
        textStock.setBounds(94, 229, 172, 28);
        contentPane.add(textStock);

        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAdd.setBounds(87, 291, 126, 44);
        contentPane.add(btnAdd);

        btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnEdit.setBounds(87, 360, 126, 44);
        contentPane.add(btnEdit);

        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnDelete.setBounds(87, 427, 126, 44);
        contentPane.add(btnDelete);

        btnGoBackToMenu = new JButton("Go back to menu");
        btnGoBackToMenu.setBounds(10, 572, 131, 21);
        contentPane.add(btnGoBackToMenu);

        table = new JTable(model) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(282, 90, 497, 493);
        contentPane.add(scrollPane);
        table.setFillsViewportHeight(true);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public String getName() {
        return textName.getText();
    }

    public String getPrice() {
        return textPrice.getText();
    }

    public String getStock() {
        return textStock.getText();
    }

    public void setName(String s) {
        textName.setText(s);
    }

    public void setPrice(String s) {
        textPrice.setText(s);
    }

    public void setStock(String s) {
        textStock.setText(s);
    }

    public void addListener(ActionListener buttonadd) {
        btnAdd.addActionListener(buttonadd);
    }

    public void deleteListener(ActionListener buttondelete) {
        btnDelete.addActionListener(buttondelete);
    }

    public void editListener(ActionListener buttonedit) {
        btnEdit.addActionListener(buttonedit);
    }

    public void setBtnGoBackToMenuListener(ActionListener buttonGoBack) {
        btnGoBackToMenu.addActionListener(buttonGoBack);
    }

    public JTable getTable() {
        return table;
    }
}
