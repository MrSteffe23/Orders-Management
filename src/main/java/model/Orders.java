package model;

import java.util.Date;

public class Orders {
    private int id;
    private int quantity;
    private Date date;
    private int id_client;
    private int id_product;

    public Orders() {
    }

    public Orders(int id, int quantity, Date date, int id_client, int id_product) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.date = date;
        this.id_client = id_client;
        this.id_product = id_product;
    }

    public Orders(int quantity, Date date, int id_client, int id_product) {
        super();
        this.quantity = quantity;
        this.date = date;
        this.id_client = id_client;
        this.id_product = id_product;
    }

    /**
     * getter for Id
     *
     * @return the Id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for quantity
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * getter for Date
     *
     * @return the Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * getter for Id_client
     *
     * @return the Id_client
     */
    public int getId_client() {
        return id_client;
    }

    /**
     * getter for Id_product
     *
     * @return the Id_product
     */
    public int getId_product() {
        return id_product;
    }

    /**
     * setter for Id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for Quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * setter for Date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * setter for Address
     */
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    /**
     * setter for Address
     */
    public void setId_product(int id_product) {
        this.id_product = id_product;
    }
}
