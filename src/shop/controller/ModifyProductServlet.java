package shop.controller;

import shop.entity.Product;
import shop.myConnection.MyConnection;
import shop.repository.ProductRepository;

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
public class ModifyProductServlet extends HttpServlet{


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyConnection.getConnection();

        request.setCharacterEncoding("utf-8");
        Product product = new Product();
        product.setName(request.getParameter("name"));
        product.setPrice(Integer.valueOf(request.getParameter("price")));
        product.setQuantity(Integer.valueOf(request.getParameter("quantity")));
        product.setState(Integer.valueOf(request.getParameter("state")));

        try {
            ProductRepository.getInstance().addProduct(connection, product);
        } catch (Exception e) {
            MyConnection.connException(connection);
        }

        MyConnection.endConnection(connection);

        response.sendRedirect("/admin/product");
    }
}
