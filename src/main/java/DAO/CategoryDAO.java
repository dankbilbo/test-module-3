package DAO;

import Const.Keywords;
import Model.Category;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDAO implements iCategoryDAO {
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(Keywords.jdbcURL, Keywords.jdbcUsername, Keywords.jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public ArrayList<Category> selectAllCategories() {
        ArrayList<Category> categories = null;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(Keywords.GET_ALL_CATEGORY);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
