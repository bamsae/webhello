package shop.controller;

import shop.entity.BuyList;
import shop.myConnection.MyConnection;
import shop.repository.BuyListRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: bungubbang
 * Date: 13. 3. 7.
 * Time: 오후 7:51
 * To change this template use File | Settings | File Templates.
 */
public class ModifyBuyListServlet extends HttpServlet{


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyConnection.getConnection();

        request.setCharacterEncoding("utf-8");

        BuyList buyList = new BuyList();
        try {
            buyList.setUserId(UserLoginServlet.getLoginId(request,response));
            buyList.setProductId(Integer.valueOf(request.getParameter("id")));
            buyList.setProductNum(Integer.valueOf(request.getParameter("quantity")));
            buyList.setState(0);

            System.out.println(buyList.getUserId() + "" + buyList.getProductId());
            BuyListRepository.getInstance().addBuyList(connection, buyList);
        } catch (Exception e) {
            e.printStackTrace();
            MyConnection.connException(connection);
        }

        MyConnection.endConnection(connection);

        response.sendRedirect("/shop/buyList");
    }
}
