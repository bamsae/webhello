<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <%

        Date date = new Date();
        String name = request.getParameter("name");
        out.write("hello=" + name + " time=" + date.toString());

        //게좌이체

    %>
  </body>
</html>