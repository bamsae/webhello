package shop.repository;

import shop.entity.User;
import shop.myConnection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: bungubbang
 * Date: 13. 3. 7.
 * Time: 오후 7:53
 * To change this template use File | Settings | File Templates.
 */
public class UserRepository {

    private static UserRepository instance = new UserRepository();

    public static UserRepository getInstance() {
        return instance;
    }

    private UserRepository() {}

    public User findById(Connection connection, int id) throws Exception {
        User user = new User();
        if(connection == null)
            System.out.println("connection null!!!!");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from shopuser where id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("passwd"));
        user.setMoney(resultSet.getInt("money"));
        return user;
    }

    public User findByName(Connection connection, String name) throws Exception {
        User user = new User();
        if(connection == null)
            System.out.println("connection null!!!!");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from shopuser where name=?");
        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("passwd"));
        user.setMoney(resultSet.getInt("money"));
        return user;
    }

    public void addUser(Connection connection, User user) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shopuser(name, passwd, money) VALUES (?, ?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getMoney());
        preparedStatement.executeUpdate();
    }

    public void updateUser(Connection connection, User user) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("update shopuser set name=?, passwd=?, money=? where id=?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getMoney());
        preparedStatement.setInt(4, user.getId());
        preparedStatement.executeUpdate();
    }
}
