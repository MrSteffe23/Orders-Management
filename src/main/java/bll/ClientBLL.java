package bll;

import bll.validators.EmailValidator;
import dao.ClientDAO;
import model.Client;

import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private final ClientDAO clientDAO;
    private final EmailValidator emailValidator = new EmailValidator();

    /**
     * Constructor which assigns a ClientDAO to clientDAO attribute
     */
    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     * This class finds a Student with a specified id
     *
     * @param id represents the id of a client
     * @return a client with the wanted id
     */
    public Client findClientById(int id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * @return a list of all Students from the Data Base
     */
    public List<Client> getClients() {
        return clientDAO.findAll();
    }

    /**
     * Inserts a specified client in the Data Base
     *
     * @param client a generated Client
     */
    public void insertClient(Client client) {
        clientDAO.insert(client);
    }

    /**
     * Deletes a specified client from the Data Base
     *
     * @param index represents the index of a client
     */
    public void deleteClient(int index) {
        Client client = clientAtIndex(index);
        clientDAO.delete(client);
    }

    /**
     * Updates a client with id=index, with values from clientNew in the Data Base
     *
     * @param index     represents the index of a client
     * @param clientNew a generated Client
     */
    public void updateClient(int index, Client clientNew) {
        Client client = clientAtIndex(index);
        int id = client.getId();
        client = new Client(id, clientNew.getName(), clientNew.getAddress(), clientNew.getEmail());
        clientDAO.update(client);
    }

    /**
     * Finds the client at the index "index"
     *
     * @param index represents the index of a client
     * @return the client at index "index" from the list of clients from the Data Base
     */
    public Client clientAtIndex(int index) {
        List<Client> clientsList = getClients();
        return clientsList.get(index);
    }

    /**
     * getter for emailValidator
     *
     * @return an email validator
     */
    public EmailValidator getEmailValidator() {
        return emailValidator;
    }

}