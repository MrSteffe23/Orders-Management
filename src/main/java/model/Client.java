package model;

public class Client {
    private int id;
    private String name;
    private String address;
    private String email;

    /**
     * default constructor for a Client
     */
    public Client() {
    }

    public Client(int id, String name, String address, String email) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Client(String name, String address, String email) {
        super();
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /**
     * getter for id
     *
     * @return the id of a client
     */
    public int getId() {
        return id;
    }

    /**
     * getter for name
     *
     * @return the name of a client
     */
    public String getName() {
        return name;
    }

    /**
     * getter for Address
     *
     * @return the Address of a client
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter for Email
     *
     * @return the id Email a client
     */
    public String getEmail() {
        return email;
    }

    /**
     * builds a string with the attributes of a client
     */
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + "]";
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
     * setter for Address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * setter for Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
