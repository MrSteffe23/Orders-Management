package bll;

import dao.ProductDAO;
import model.Product;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private final ProductDAO productDAO;

    /**
     * Constructor which assigns a ProductDAO to productDAO attribute
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * This class finds a Product with a specified id
     *
     * @param id represents the id of a product
     * @return a product with the wanted id
     */
    public Product findProductById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return product;
    }

    /**
     * @return a list of all Products from the Data Base
     */
    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    /**
     * Inserts a specified product in the Data Base
     *
     * @param product a generated Product
     */
    public void insertProduct(Product product) {
        productDAO.insert(product);
    }

    /**
     * Deletes a specified product from the Data Base
     *
     * @param index represents the index of a product
     */
    public void deleteProduct(int index) {
        Product product = productAtIndex(index);
        productDAO.delete(product);
    }

    /**
     * Updates a product with id=index, with values from productNew in the Data Base
     *
     * @param index      represents the index of a product
     * @param productNew a generated Product
     */
    public void updateProduct(int index, Product productNew) {
        Product product = productAtIndex(index);
        int id = product.getId();
        product = new Product(id, productNew.getName(), productNew.getPrice(), productNew.getStock());//I built another product with the same "id", but with different attributes
        productDAO.update(product);//update the newProduct
    }

    /**
     * Finds the product at the index "index"
     *
     * @param index represents the index of a product
     * @return the product at index "index" from the list of products from the Data Base
     */
    public Product productAtIndex(int index) {
        List<Product> productsList = getProducts();
        return productsList.get(index);
    }

    /**
     * Checks for a valid price
     *
     * @param price the price of a product
     * @return true for a valid price, false otherwise
     */
    public boolean priceValidate(int price) {
        return price >= 0 && price <= 10000000;
    }

    /**
     * Checks for a valid stock
     *
     * @param stock the stock of a product
     * @return true for a valid stock, false otherwise
     */
    public boolean stockValidate(int stock) {
        return stock > 0 && stock <= 1000000;
    }
}
