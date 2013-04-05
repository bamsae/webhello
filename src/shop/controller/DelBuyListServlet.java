package shop.controller;

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
public class DelBuyListServlet extends HttpServlet{


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyConnection.getConnection();

        request.setCharacterEncoding("utf-8");

        try {
            int id = Integer.valueOf(request.getParameter("id"));

            BuyListRepository.getInstance().deleteBuyList(connection, id);

        } catch (Exception e) {
            e.printStackTrace();
            MyConnection.connException(connection);
        }

        MyConnection.endConnection(connection);

        if(request.getParameter("from").equals("admin"))
            response.sendRedirect("/admin/buyList");
        else
            response.sendRedirect("/shop/buy");
    }
}
