package shop.controller;

import shop.myConnection.MyConnection;
import shop.repository.ProductRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: bungubbang
 * Date: 13. 2. 28.
 * Time: 오후 7:26
 * To change this template use File | Settings | File Templates.
 */
public class ModifyProductFormServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!"admin".equals(UserLoginServlet.checkLogin(request,response))) {
            return;
        }
        Connection connection = MyConnection.getConnection();

        try {
            request.setAttribute("product", ProductRepository.getInstance().FindById(connection, Integer.valueOf(request.getParameter("id"))));
        } catch (Exception e) {
            e.printStackTrace();
            MyConnection.connException(connection);
        }

        MyConnection.endConnection(connection);

        RequestDispatcher view = request.getRequestDispatcher("/shop/modifyProduct.jsp");
        view.forward(request, response);
    }
}
