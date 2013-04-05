package shop.controller;

import myboard.entity.User;
import myboard.repository.UserDBRepository;
import myboard.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: bungubbang
 * Date: 13. 3. 7.
 * Time: 오후 7:51
 * To change this template use File | Settings | File Templates.
 */
public class UserLoginServlet extends HttpServlet{

    UserRepository userRepository = UserDBRepository.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("checkckckck");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("id")) {
                request.setAttribute("id",cookie.getValue());
            }
        }

        System.out.println("checkckcck2");
        RequestDispatcher view = request.getRequestDispatcher("/board/login.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userRepository.findById(request.getParameter("id"));
        if(request.getParameter("rememberId") != null) {
            Cookie cookie = new Cookie("id", request.getParameter("id"));
            cookie.setMaxAge(30 * 24 * 60 * 60); // 한달
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("id", request.getParameter("id"));
            cookie.setMaxAge(0); // 한달
            response.addCookie(cookie);
        }

        if( user.getPassword().equals(request.getParameter("password"))) {
            request.getSession().setAttribute("isLogin", "ok");
            ServletContext servletContext = request.getServletContext();

            // 현재 로그인 사용자 조사
            if( servletContext.getAttribute("loginCount") == null ) {
                // loginCount가 없으면 초기화
                servletContext.setAttribute("loginCount", 0);
            }

            // loginCout를 얻어와서 1을 증가
            servletContext.setAttribute("loginCount", Integer.parseInt(String.valueOf(servletContext.getAttribute("loginCount"))) + 1 );

            response.sendRedirect("/board/list");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("isLogin");
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("loginCount", Integer.parseInt(String.valueOf(servletContext.getAttribute("loginCount"))) - 1 );
        response.sendRedirect("/board/login");
    }

    public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("isLogin") ==  null) {
            response.sendRedirect("/board/login");
            return false;
        }
        return true;
    }
}
