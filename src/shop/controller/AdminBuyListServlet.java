package shop.controller;

import shop.entity.BuyList;
import shop.myConnection.MyConnection;
import shop.repository.BuyListRepository;
import shop.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bungubbang
 * Date: 13. 3. 7.
 * Time: 오후 7:51
 * To change this template use File | Settings | File Templates.
 */
public class AdminBuyListServlet extends HttpServlet{

    UserRepository userRepository = UserRepository.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyConnection.getConnection();

        List<BuyList> buyLists = null;
        try {
            int id = UserLoginServlet.getLoginId(request, response);

            buyLists = BuyListRepository.getInstance().BuyListByUserId(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
            MyConnection.connException(connection);
        }

        request.setAttribute("buyLists", buyLists);

        RequestDispatcher view = request.getRequestDispatcher("/shop/buyList.jsp");
        view.forward(request,response);

        MyConnection.endConnection(connection);
    }
}
