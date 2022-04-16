package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewClient extends JFrame {
    private final JTable table;
    private final JTextField textField;
    private final JTextField textField_1;
    private final JTextField textField_2;
    private final JButton btnAdd;
    private final JButton btnEdit;
    private final JButton btnDelete;
    private final JButton btnGoBackToMenu;
    private final DefaultTableModel model;

    /**
     * builds the Client interface
     *
     * @param Model model for the JTable
     */
    public ViewClient(DefaultTableModel Model) {
        this.model = Model;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 803, 630);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblClientOperations = new JLabel("Client Operations");
        lblClientOperations.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblClientOperations.setBounds(305, 5, 172, 75);
        contentPane.add(lblClientOperations);

        table = new JTable(model) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);//This creates a scroll pane as a container for the table; the table is automatically added to the container.
        scrollPane.setBounds(282, 90, 497, 493);
        contentPane.add(scrollPane);
        table.setFillsViewportHeight(true);//the table uses the entire height of the container, even if the table doesn't have enough rows to use the whole vertical space

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblName.setBounds(34, 124, 64, 35);
        contentPane.add(lblName);

        textField = new JTextField();
        textField.setBounds(94, 130, 172, 28);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAddress.setBounds(22, 175, 62, 28);
        contentPane.add(lblAddress);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEmail.setBounds(34, 226, 50, 28);
        contentPane.add(lblEmail);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(94, 178, 172, 28);
        contentPane.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(94, 229, 172, 28);
        contentPane.add(textField_2);

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
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public String getName() {
        return textField.getText();
    }

    public String getAddress() {
        return textField_1.getText();
    }

    public String getEmail() {
        return textField_2.getText();
    }

    public void setName(String s) {
        textField.setText(s);
    }

    public void setAddress(String s) {
        textField_1.setText(s);
    }

    public void setEmail(String s) {
        textField_2.setText(s);
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
