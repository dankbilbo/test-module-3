package Const;

public class Keywords {
    public static final String jdbcURL = "jdbc:mysql://localhost:3306/testmodule3?useSSL=false";
    public static final String jdbcUsername = "root";
    public static final String jdbcPassword = "1234";
    public static final String GET_ALL_PRODUCT = "select * from products";
    public static final String GET_PRODUCT_BY_NAME = "select * from products where name like ?";
    public static final String UPDATE_PRODUCT = "update products set name = ?, price = ?, amount = ?, color = ?, categoryId = ? , description = ? where id = ?";
    public static final String DELETE_PRODUCT = "delete from products where id = ?";
    public static final String INSERT_PRODUCT = "insert into products (name,price,amount,color,description, categoryId) value (?,?,?,?,?,?)";
    public static final String SELECT_PRODUCT = "select * from products where id = ?";
    public static final String GET_ALL_CATEGORY = "select * from category";
}
