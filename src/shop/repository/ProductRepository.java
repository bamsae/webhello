package shop.repository;

import shop.entity.Product;
import shop.myConnection.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bungubbang
 * Email: bungubbang57@gmail.com
 */
public class ProductRepository {

    private static ProductRepository instance = new ProductRepository();

    public static ProductRepository getInstance() {
        return instance;
    }
    private ProductRepository() {}

    public int hashCode() {
        return super.hashCode();    //To change body of overridden methods use File | Settings | File Templates.
    }


    public List<Product> getProducts(Connection connection) throws Exception {
        List<Product> products = new ArrayList<Product>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from shopproduct order by id desc");
        while (resultSet.next()) {

            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getInt("price"));
            product.setState(resultSet.getInt("state"));
            product.setQuantity(resultSet.getInt("quantity"));

            System.out.println("product call    " + product);

            products.add(product);
        }
        return products;
    }

    
    public Product FindById(Connection connection, int id) throws Exception {
        Product product = new Product();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from shopproduct where id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        product.setId(resultSet.getInt("id"));
        product.setPrice(resultSet.getInt("price"));
        product.setState(resultSet.getInt("state"));
        product.setName(resultSet.getString("name"));
        product.setQuantity(resultSet.getInt("quantity"));
        return product;
    }

    
    public void addProduct(Connection connection, Product product) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shopproduct(name, state, price, quantity) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getState());
        preparedStatement.setInt(3, product.getPrice());
        preparedStatement.setInt(4, product.getQuantity());

        preparedStatement.executeUpdate();
    }

    
    public void modifyProduct(Connection connection, Product product) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("update shopproduct set name=?, state=?, price=?, quantity=? where id=?");
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getState());
        preparedStatement.setInt(3, product.getPrice());
        preparedStatement.setInt(4, product.getQuantity());
        preparedStatement.setInt(5, product.getId());

        preparedStatement.executeUpdate();
    }

    
    public void deleteProduct(Connection connection, int id) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from shopproduct where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
