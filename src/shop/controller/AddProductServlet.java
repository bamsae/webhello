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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bungubbang
 * Date: 13. 3. 7.
 * Time: 오후 7:51
 * To change this template use File | Settings | File Templates.
 */
public class AddProductServlet extends HttpServlet{

    UserRepository userRepository = UserRepository.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyConnection.startConnection();

        if(!"admin".equals(UserLoginServlet.checkLogin(request, response))) {
            return;
        }

        List<Product> products = ProductRepository.getInstance().getProducts();

        request.setAttribute("products", products);

        RequestDispatcher view = request.getRequestDispatcher("/shop/adminProduct.jsp");
        view.forward(request,response);

        MyConnection.endConnection();
    }
}
