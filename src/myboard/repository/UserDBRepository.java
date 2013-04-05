package myboard.repository;

import myboard.entity.User;

import java.sql.*;

/**
 * Created by bungubbang
 * Email: bungubbang57@gmail.com
 */
public class UserDBRepository implements UserRepository{

    private static UserDBRepository instance = new UserDBRepository();

    public static UserDBRepository getInstance() {
        return instance;
    }

    private String url = "jdbc:postgresql://localhost:5432/study";
    private String usr = "study";
    private String pwd = "1234";

    @Override
    public User findById(String id) {
        User user = new User();
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(url, usr, pwd);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from boarduser where id=?");
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user.setId(resultSet.getString("id"));
            user.setPassword(resultSet.getString("password"));

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        try {
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO boarduser(id, password) VALUES (?, ?)");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
