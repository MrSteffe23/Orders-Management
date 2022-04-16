package presentation;

import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;
import presentation.Models.ClientModel;
import presentation.Models.OrderModel;
import presentation.Models.ProductModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Controller {
    private final ClientBLL clientBll = new ClientBLL();
    private final ProductBLL productBll = new ProductBLL();
    private final OrdersBLL ordersBll = new OrdersBLL();
    private final ViewSelect viewselect;
    private final ViewClient viewclient;
    private final ViewProduct viewproduct;
    private final ViewOrder vieworder;
    private Client clientForOrder = null;
    private Product productForOrder = null;
    private int productIndexOrder = -1; //for updating the stock for the selected product after making an order
    private int clientIndexOrder = -1;

    /**
     * builds the entire interface
     *
     * @param viewSelect the view where the user can select an operation
     */
    public Controller(ViewSelect viewSelect) {
        viewselect = viewSelect;
        viewselect.setVisible(true);

        //creating the Clients Table
        ClientModel clientModel = new ClientModel();
        DefaultTableModel model = clientModel.createModelTable(clientBll.getClients());
        viewclient = new ViewClient(model);
        //creating the Products Table
        ProductModel productModel = new ProductModel();
        model = productModel.createModelTable(productBll.getProducts());
        viewproduct = new ViewProduct(model);

        vieworder = new ViewOrder(new OrderModel().createModelTable(ordersBll.getOrders()));
        //Listeners
        viewselect.clientListener(
                (ActionEvent e) -> {
                    viewclient.setVisible(true);
                    viewselect.setVisible(false);
                }
        );

        viewselect.productListener(
                (ActionEvent e) -> {
                    viewproduct.setVisible(true);
                    viewselect.setVisible(false);
                }
        );

        viewselect.orderListener(
                (ActionEvent e) -> {
                    vieworder.setModelClients(viewclient.getModel());
                    vieworder.setModelProducts(viewproduct.getModel());
                    vieworder.setModelOrders(new OrderModel().createModelTable(ordersBll.getOrders()));
                    vieworder.setVisible(true);
                    viewselect.setVisible(false);
                }
        );

        viewclient.setBtnGoBackToMenuListener(
                (ActionEvent e) -> {
                    viewselect.setVisible(true);
                    viewclient.setVisible(false);
                }
        );

        viewproduct.setBtnGoBackToMenuListener(
                (ActionEvent e) -> {
                    viewselect.setVisible(true);
                    viewproduct.setVisible(false);
                }
        );

        vieworder.setBtnGoBackToMenuListener(
                (ActionEvent e) -> {
                    viewselect.setVisible(true);
                    vieworder.setVisible(false);
                }
        );
        //--------------------------------------------------------------------------------------------------------CLIENTS-----------------
        viewclient.addListener(
                (ActionEvent e) -> {
                    if (validateClientData()) {
                        viewclient.getModel().addRow( //updating the JTable
                                new Object[]{
                                        viewclient.getName(),
                                        viewclient.getAddress(),
                                        viewclient.getEmail()
                                }
                        );
                        Client client = new Client(viewclient.getName(), viewclient.getAddress(), viewclient.getEmail());
                        clientBll.insertClient(client); //updating the DB
                        //Delete form after adding data
                        viewclient.setName("");
                        viewclient.setAddress("");
                        viewclient.setEmail("");
                    }
                }
        );
        viewclient.deleteListener(
                (ActionEvent e) -> {
                    if (viewclient.getTable().getSelectedRow() != -1) {
                        clientBll.deleteClient(viewclient.getTable().getSelectedRow());//delete the client from the DB
                        viewclient.getModel().removeRow(viewclient.getTable().getSelectedRow());
                        //Delete form after deleting data
                        viewclient.setName("");
                        viewclient.setAddress("");
                        viewclient.setEmail("");
                    }
                }
        );
        viewclient.editListener(
                (ActionEvent e) -> {
                    int i = viewclient.getSelectedRow();
                    if (i != -1) {
                        if (validateClientData()) {
                            Client client = new Client(viewclient.getName(), viewclient.getAddress(), viewclient.getEmail());
                            viewclient.getModel().setValueAt(viewclient.getName(), i, 0);
                            viewclient.getModel().setValueAt(viewclient.getAddress(), i, 1);
                            viewclient.getModel().setValueAt(viewclient.getEmail(), i, 2);
                            clientBll.updateClient(viewclient.getTable().getSelectedRow(), client);//update the client from the DB
                        }
                    }
                }
        );
        viewclient.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (viewclient.getSelectedRow() != -1) {
                int i = viewclient.getSelectedRow();
                viewclient.setName((String) viewclient.getModel().getValueAt(i, 0));
                viewclient.setAddress((String) viewclient.getModel().getValueAt(i, 1));
                viewclient.setEmail((String) viewclient.getModel().getValueAt(i, 2));
            }
        });
        //--------------------------------------------------------------------------------------------------------PRODUCTS-----------------
        viewproduct.addListener(
                (ActionEvent e) -> {
                    if (validateProductData()) {
                        viewproduct.getModel().addRow(
                                new Object[]{
                                        viewproduct.getName(),
                                        viewproduct.getPrice(),
                                        viewproduct.getStock()
                                }
                        );
                        Product product = new Product(viewproduct.getName(), parseInt(viewproduct.getPrice()), parseInt(viewproduct.getStock()));
                        productBll.insertProduct(product);
                        //Delete form after adding data
                        viewproduct.setName("");
                        viewproduct.setPrice("");
                        viewproduct.setStock("");
                    }
                }
        );
        viewproduct.deleteListener(
                (ActionEvent e) -> {
                    if (viewproduct.getTable().getSelectedRow() != -1) {
                        productBll.deleteProduct(viewproduct.getTable().getSelectedRow());//delete the product from DB
                        viewproduct.getModel().removeRow(viewproduct.getTable().getSelectedRow());
                        viewproduct.setName("");
                        viewproduct.setPrice("");
                        viewproduct.setStock("");
                    }
                }
        );
        viewproduct.editListener(
                (ActionEvent e) -> {
                    int i = viewproduct.getSelectedRow();
                    if (i != -1) {
                        if (validateProductData()) {
                            viewproduct.getModel().setValueAt(viewproduct.getName(), i, 0);
                            viewproduct.getModel().setValueAt(viewproduct.getPrice(), i, 1);
                            viewproduct.getModel().setValueAt(viewproduct.getStock(), i, 2);
                            Product product = new Product(viewproduct.getName(), parseInt(viewproduct.getPrice()), parseInt(viewproduct.getStock()));
                            productBll.updateProduct(viewproduct.getSelectedRow(), product);//updating the product inside the DB
                        }
                    }
                }
        );
        viewproduct.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (viewproduct.getSelectedRow() != -1) {
                int i = viewproduct.getSelectedRow();
                viewproduct.setName((String) viewproduct.getModel().getValueAt(i, 0));
                viewproduct.setPrice(viewproduct.getModel().getValueAt(i, 1) + "");
                viewproduct.setStock(viewproduct.getModel().getValueAt(i, 2) + "");
            }
        });
        //-------------------------------------------------------------------------------------------------------------ORDERS------------
        vieworder.getTableClients().getSelectionModel().addListSelectionListener(e -> {
            if (vieworder.getSelectedClientRow() != -1)
                clientIndexOrder = vieworder.getSelectedClientRow();
        });
        vieworder.getTableProducts().getSelectionModel().addListSelectionListener(e -> {
            if (vieworder.getSelectedProductRow() != -1)
                productIndexOrder = vieworder.getSelectedProductRow();
        });
        vieworder.makeOrderListener(
                (ActionEvent e) -> {
                    if (clientIndexOrder == -1)
                        JOptionPane.showMessageDialog(vieworder, "Choose a client first");
                    else if (productIndexOrder == -1)
                        JOptionPane.showMessageDialog(vieworder, "Choose a product first");
                    else if (validate(vieworder.getQuantity()))
                        JOptionPane.showMessageDialog(viewclient, "Insert a quantity");
                    else {
                        clientForOrder = clientBll.clientAtIndex(clientIndexOrder);
                        productForOrder = productBll.productAtIndex(productIndexOrder);
                        int ok = 1;
                        try {
                            parseInt(vieworder.getQuantity());
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(viewproduct, "Insert a numeric value for quantity");
                            ok = 0;
                        }
                        if (ok == 1) {
                            if (!ordersBll.validateQuantity(parseInt(vieworder.getQuantity()), productForOrder.getStock()))
                                JOptionPane.showMessageDialog(vieworder, "Insert a quantity between 1 and 1000000 that must be smaller than the stock product");
                            else//the data inserted is validated here
                            {
                                LocalDate localDate = LocalDate.now();
                                Date orderDate = java.sql.Date.valueOf(localDate);//getting the local date
                                vieworder.getModelOrders().addRow(
                                        new Object[]{
                                                vieworder.getQuantity(),
                                                orderDate,
                                                clientForOrder.getName(),
                                                productForOrder.getName(),
                                        }
                                );
                                vieworder.getTableProducts().getModel().setValueAt(productForOrder.getStock() - parseInt(vieworder.getQuantity()), productIndexOrder, 2);
                                Product product = new Product(productForOrder.getName(), productForOrder.getPrice(), productForOrder.getStock() - parseInt(vieworder.getQuantity()));
                                productBll.updateProduct(productIndexOrder, product);//updating the stock product
                                ordersBll.makeOrder(clientForOrder, productForOrder, parseInt(vieworder.getQuantity()), orderDate);//inserting a new order in the DB
                                vieworder.setModelOrders(new OrderModel().createModelTable(ordersBll.getOrders()));
                                List<Orders> listOrders = ordersBll.getOrders();
                                ordersBll.makeBil(clientForOrder.getName(), productForOrder.getName(), vieworder.getQuantity(), productForOrder.getPrice(), orderDate, listOrders.get(listOrders.size() - 1).getId());
                            }
                            //Delete form after adding data
                            vieworder.setQuantity("");
                        }
                    }
                }
        );
    }

    /**
     * Validates a string for not being empty
     *
     * @param s string inserted by the user
     * @return true if the string is empty, false otherwise
     */
    public boolean validate(String s) {
        return s.equals("");//0 - for s = "", else 1
    }

    /**
     * Validates the client data inserted by the user
     *
     * @return true if de client data is valid, false otherwise
     */
    public boolean validateClientData() {
        if (validate(viewclient.getName()) || validate(viewclient.getAddress()) || validate(viewclient.getEmail())) {
            JOptionPane.showMessageDialog(viewclient, "Insert some data");
            return false;
        } else {
            Client client = new Client(viewclient.getName(), viewclient.getAddress(), viewclient.getEmail()); //building a temporary client
            if (!clientBll.getEmailValidator().validate(client))//validate the client by validating his email
            {
                JOptionPane.showMessageDialog(viewclient, "Invalid email");
                return false;
            }
        }
        return true;
    }

    /**
     * Validates the product data inserted by the user
     *
     * @return true if the product data is valid, false otherwise
     */
    public boolean validateProductData() {
        if (validate(viewproduct.getName()) || validate(viewproduct.getPrice()) || validate(viewproduct.getStock())) {
            JOptionPane.showMessageDialog(viewclient, "Insert some data");
            return false;
        } else {
            try {
                parseInt(viewproduct.getPrice());
                parseInt(viewproduct.getStock());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(viewproduct, "Insert a numeric value for price or stock");
                return false;
            }
            if (!productBll.priceValidate(parseInt(viewproduct.getPrice()))) {
                JOptionPane.showMessageDialog(viewproduct, "Insert a price between 0 and 10000000");
                return false;
            } else if (!productBll.stockValidate(parseInt(viewproduct.getStock()))) {
                JOptionPane.showMessageDialog(viewproduct, "Insert a stock between 1 and 1000000");
                return false;
            }
        }
        return true;
    }
}
