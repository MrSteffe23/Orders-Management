package bll;

import dao.OrdersDAO;
import model.Client;
import model.Orders;
import model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class OrdersBLL {
    private final OrdersDAO ordersDAO;

    /**
     * Constructor which assigns a OrdersDAO to ordersDAO attribute
     */
    public OrdersBLL() {
        ordersDAO = new OrdersDAO();
    }

    /**
     * @return a list of all Orders from the Data Base
     */
    public List<Orders> getOrders() {
        return ordersDAO.findAll();
    }

    /**
     * Inserts a specified client in the Data Base
     *
     * @param client   a generated client
     * @param product  a generated product
     * @param quantity a specified quantity for the product
     * @param time     the local time
     */
    public void makeOrder(Client client, Product product, int quantity, Date time) {
        Orders order = new Orders(quantity, time, client.getId(), product.getId());
        ordersDAO.insert(order);
    }

    /**
     * Validates a specific quantity for a product
     *
     * @param quantity a specified quantity for the product
     * @param stock    the number of products of a type from the Data Base
     * @return true if the quantity is valid or false otherwise
     */
    public boolean validateQuantity(int quantity, int stock) {
        if (quantity < 0 || quantity > 1000000)
            return false;
        return quantity <= stock;
    }

    /**
     * Generates a pdf file with the bill the client have after making an order
     *
     * @param clientName  The name of a client
     * @param productName The name of a product
     * @param quantity    a specified quantity for the product
     * @param price       the price for a single product
     * @param date        the local time
     * @param id          the id of a product
     */
    public void makeBil(String clientName, String productName, String quantity, int price, Date date, int id) {
        Document doc = new Document();
        try {
            //generate a PDF at the specified location
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\stef_\\OneDrive\\Desktop\\Cursuri\\TP\\Laborator\\Tema 3\\PT2022_30227_Atitienei_Stefan_Assignment_3\\Bill_" + id + ".pdf"));
            //opens the PDF
            doc.open();
            //adds paragraph to the PDF file
            doc.add(new Paragraph("Client: " + clientName));
            doc.add(new Paragraph("Product: " + productName));
            doc.add(new Paragraph("Quantity: " + quantity));
            doc.add(new Paragraph("Price: " + price * parseInt(quantity)));
            doc.add(new Paragraph("Order date: " + date));
            //close the PDF file
            doc.close();
            //closes the writer
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
