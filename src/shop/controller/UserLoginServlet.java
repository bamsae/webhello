package shop.controller;


import shop.entity.User;
import shop.myConnection.MyConnection;
import shop.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
 * To change this template use File | Setøtings | File Templates.
 */
public class UserLoginServlet extends HttpServlet{

    UserRepository userRepository = UserRepository.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("checkckckck");
        Cookie[] cookies = request.getCookies();
        if(cookies != null)
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("id")) {
                request.setAttribute("id",cookie.getValue());
            }
        }

        System.out.println("checkckcck2");
        RequestDispatcher view = request.getRequestDispatcher("/shop/userLogin.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection connection = MyConnection.getConnection();
        User user = null;
        try {
            user = userRepository.findByName(connection, request.getParameter("id"));
            MyConnection.endConnection(connection);
        } catch (Exception e) {
            MyConnection.connException(connection);
            Connection connection1 = MyConnection.getConnection();
            user = new User();
            user.setName(request.getParameter("id"));
            user.setMoney(100000);
            user.setPassword(request.getParameter("password"));
            try {
                userRepository.addUser(connection1, user);
                System.out.println("makenew ididididididi");
                MyConnection.endConnection(connection1);
            } catch (Exception e1) {
                e1.printStackTrace();
                MyConnection.connException(connection1);
            }
        }

        if(request.getParameter("rememberId") != null) {
            Cookie cookie = new Cookie("id", request.getParameter("id"));
            cookie.setMaxAge(30 * 24 * 60 * 60); // 한달
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("id", request.getParameter("id"));
            cookie.setMaxAge(0); // 한달
            response.addCookie(cookie);
        }

        Connection connection2 = MyConnection.getConnection();
        try {
            user = UserRepository.getInstance().findByName(connection2, user.getName());
            MyConnection.endConnection(connection2);
        } catch (Exception e) {
            MyConnection.connException(connection2);
        }
        if( user.getPassword().equals(request.getParameter("password"))) {

            if( "admin".equals(user.getName())) {
                System.out.println("admin access");
                response.sendRedirect("/admin/product");
                request.getSession().setAttribute("isLogin", "admin");
                return;
            }
            request.getSession().setAttribute("isLogin", user.getId());
            System.out.println("userid : hahahahahahahah " + user.getId());

            ServletContext servletContext = request.getServletContext();

            // 현재 로그인 사용자 조사
            if( servletContext.getAttribute("loginCount") == null ) {
                // loginCount가 없으면 초기화
                servletContext.setAttribute("loginCount", 0);
            }

            // loginCout를 얻어와서 1을 증가
            servletContext.setAttribute("loginCount", Integer.parseInt(String.valueOf(servletContext.getAttribute("loginCount"))) + 1 );

            response.sendRedirect("/shop/list");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("isLogin");
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("loginCount", Integer.parseInt(String.valueOf(servletContext.getAttribute("loginCount"))) - 1);
        response.sendRedirect("/shop/login");
    }

    public static String checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String state = request.getSession().getAttribute("isLogin").toString();
        if(state ==  null) {
            response.sendRedirect("/shop/login");
            return null;
        }
        return state;
    }
    public static int getLoginId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(checkLogin(request,response) != null) {
            return Integer.valueOf(request.getSession().getAttribute("isLogin").toString());
        }
        return -1;
    }
}
