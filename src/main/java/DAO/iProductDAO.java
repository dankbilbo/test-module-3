package DAO;

import Model.Category;
import Model.Product;

import java.util.ArrayList;

public interface iProductDAO {
    public ArrayList<Product> getAllProducts();
    public ArrayList<Product> getProductsByName(String name);
    public int updateProduct(Product product);
    public int insertProduct(Product product);
    public int deleteProduct(int productId);
    public Product selectProduct(int id);
}
