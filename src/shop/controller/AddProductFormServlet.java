package shop.controller;

import myboard.controller.BoardLoginServlet;
import shop.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: bungubbang
 * Date: 13. 2. 28.
 * Time: 오후 7:26
 * To change this template use File | Settings | File Templates.
 */
public class AddProductFormServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!"admin".equals(UserLoginServlet.checkLogin(request,response))) {
            return;
        }

        RequestDispatcher view = request.getRequestDispatcher("/shop/addProduct.jsp");
        view.forward(request, response);
    }
}
