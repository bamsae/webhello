package shop.controller;

import shop.entity.Product;
import shop.entity.User;
import shop.myConnection.MyConnection;
import shop.repository.ProductRepository;
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
public class ProductListServlet extends HttpServlet{

    UserRepository userRepository = UserRepository.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyConnection.getConnection();

        List<Product> products = null;
        User user = null;

        try {
            products = ProductRepository.getInstance().getProducts(connection);
            user = UserRepository.getInstance().findById(connection, UserLoginServlet.getLoginId(request,response));
        } catch (Exception e) {
            MyConnection.connException(connection);
        }

        request.setAttribute("products", products);
        request.setAttribute("user", user);

        MyConnection.endConnection(connection);

        RequestDispatcher view = request.getRequestDispatcher("/shop/productList.jsp");
        view.forward(request,response);
    }
}
