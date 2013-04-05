package shop.controller;

import shop.entity.Product;
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
public class AdminProductServlet extends HttpServlet{

    UserRepository userRepository = UserRepository.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!"admin".equals(UserLoginServlet.checkLogin(request, response))) {
            return;
        }
        Connection connection = MyConnection.getConnection();

        System.out.println("admin done");

        List<Product> products = null;
        try {
            products = ProductRepository.getInstance().getProducts(connection);
        } catch (Exception e) {
            MyConnection.connException(connection);
        }

        request.setAttribute("products", products);

        System.out.println("testttt" + products);

        MyConnection.endConnection(connection);

        RequestDispatcher view = request.getRequestDispatcher("/shop/adminProduct.jsp");
        view.forward(request,response);
    }
}
