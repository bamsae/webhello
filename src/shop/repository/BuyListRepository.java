package shop.repository;

import shop.entity.BuyList;
import shop.myConnection.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: skplanet
 * Date: 13. 3. 29.
 * Time: 오전 10:34
 * To change this template use File | Settings | File Templates.
 */
public class BuyListRepository {

    private static BuyListRepository instance = new BuyListRepository();
    public static BuyListRepository getInstance() {
        return instance;
    }

    public List<BuyList> BuyList(Connection connection) throws Exception {
        List<BuyList> list = new ArrayList<BuyList>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from buylist");
        while (resultSet.next()) {
            BuyList buyList = new BuyList();
            buyList.setId(resultSet.getInt("id"));
            buyList.setProductId(resultSet.getInt("productid"));
            buyList.setProductNum(resultSet.getInt("productnum"));
            buyList.setUserId(resultSet.getInt("userid"));
            buyList.setState(resultSet.getInt("state"));

            buyList.setUser(UserRepository.getInstance().findById(connection, buyList.getUserId()));
            buyList.setProduct(ProductRepository.getInstance().FindById(connection, buyList.getProductId()));

            list.add(buyList);
        }
        return list;
    }

    public List<BuyList> BuyListByUserId(Connection connection, int id) throws Exception {
        List<BuyList> list = new ArrayList<BuyList>();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from buylist where userid=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while( resultSet.next() ) {
            BuyList buyList = new BuyList();
            buyList.setId(resultSet.getInt("id"));
            buyList.setProductId(resultSet.getInt("productid"));
            buyList.setProductNum(resultSet.getInt("productnum"));
            buyList.setUserId(resultSet.getInt("userid"));
            buyList.setState(resultSet.getInt("state"));

            buyList.setUser(UserRepository.getInstance().findById(connection, buyList.getUserId()));
            buyList.setProduct(ProductRepository.getInstance().FindById(connection, buyList.getProductId()));

            list.add(buyList);
        }

        return list;
    }

    public void addBuyList(Connection connection, BuyList buyList) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO buylist(userid, productid, productnum, state) VALUES (?, ?, ?, 0)");
        preparedStatement.setInt(1, buyList.getUserId());
        preparedStatement.setInt(2, buyList.getProductId());
        preparedStatement.setInt(3, buyList.getProductNum());
        preparedStatement.executeUpdate();
    }

    public void modifyBuyList(Connection connection, int id, int state) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("update buylist set state=? where id=?");
        preparedStatement.setInt(1, state);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();

    }

    public void deleteBuyList(Connection connection, int id) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from buylist where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }
}
