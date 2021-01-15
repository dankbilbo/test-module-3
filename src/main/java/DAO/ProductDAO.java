package DAO;

import Const.Keywords;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO implements iProductDAO {
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(Keywords.jdbcURL,Keywords.jdbcUsername,Keywords.jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(Keywords.GET_ALL_PRODUCT);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String color = rs.getString("color");
                String desc = rs.getString("description");
                int amount = rs.getInt("amount");
                int categoryId = rs.getInt("categoryId");
                Product product = new Product(id, name, price, amount, color, desc, categoryId);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public ArrayList<Product> getProductsByName(String name) {
        ArrayList<Product> products = null;
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Keywords.GET_PRODUCT_BY_NAME);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String nameDB = rs.getString("name");
                double price = rs.getDouble("price");
                String color = rs.getString("color");
                String desc = rs.getString("description");
                int amount = rs.getInt("amount");
                int categoryId = rs.getInt("categoryId");
                Product product = new Product(id, name, price, amount, color, desc, categoryId);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public int updateProduct(Product product) {
        int resultSet = 0;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Keywords.UPDATE_PRODUCT);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getAmount());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setInt(5,product.getCategoryId());
            preparedStatement.setString(6,product.getDescription());
            preparedStatement.setInt(7,product.getId());
            resultSet = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {

        }
        return resultSet;
    }

    @Override
    public int insertProduct(Product product) {
        int resultSet = 0;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Keywords.INSERT_PRODUCT);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getAmount());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5,product.getDescription());
            preparedStatement.setInt(6,product.getCategoryId());
            resultSet = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public int deleteProduct(int productId ) {
        int resultSet = 0;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Keywords.DELETE_PRODUCT);
            preparedStatement.setInt(1,productId);
            resultSet = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Keywords.SELECT_PRODUCT);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int idDB = rs.getInt("id");
                String nameDB = rs.getString("name");
                double price = rs.getDouble("price");
                String color = rs.getString("color");
                String desc = rs.getString("description");
                int amount = rs.getInt("amount");
                int categoryId = rs.getInt("categoryId");
                product = new Product(id, nameDB, price, amount, color, desc, categoryId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }
}
