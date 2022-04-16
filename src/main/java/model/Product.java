package model;

public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;

    public Product() {
    }

    public Product(int id, String name, int price, int stock) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, int price, int stock) {
        super();
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * getter for id
     *
     * @return the id of a product
     */
    public int getId() {
        return id;
    }

    /**
     * getter for Name
     *
     * @return the Name of a product
     */
    public String getName() {
        return name;
    }

    /**
     * getter for Price
     *
     * @return the Price of a product
     */
    public int getPrice() {
        return price;
    }

    /**
     * getter for Stock
     *
     * @return the Stock of a product
     */
    public int getStock() {
        return stock;
    }

    /**
     * setter for id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for Price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * setter for Stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * builds a string with the attributes of a client
     */
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
    }
}
