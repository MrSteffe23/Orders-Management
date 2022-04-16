package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewSelect extends JFrame {
    private final JButton btnClient;
    private final JButton btnProduct;
    private final JButton btnOrder;

    /**
     * builds the Select interface
     */
    public ViewSelect() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 405, 417);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel SelectOperation = new JLabel("Select Operation");
        SelectOperation.setFont(new Font("Tahoma", Font.PLAIN, 20));
        SelectOperation.setBounds(121, 68, 161, 41);
        contentPane.add(SelectOperation);

        btnClient = new JButton("Client");
        btnClient.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnClient.setBounds(137, 136, 114, 33);
        contentPane.add(btnClient);

        btnProduct = new JButton("Product");
        btnProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnProduct.setBounds(137, 207, 114, 33);
        contentPane.add(btnProduct);

        btnOrder = new JButton("Order");
        btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnOrder.setBounds(137, 279, 114, 33);
        contentPane.add(btnOrder);
    }

    public void clientListener(ActionListener client) {
        btnClient.addActionListener(client);
    }

    public void productListener(ActionListener product) {
        btnProduct.addActionListener(product);
    }

    public void orderListener(ActionListener order) {
        btnOrder.addActionListener(order);
    }
}
