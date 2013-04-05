<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>adminPage</title>
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1 style="margin-left: 200px;">Admin</h1>
<br>
    <ul class="nav nav-tabs">
        <li class="active">
            <a href="#">상품관리</a>
        </li>
        <li>
            <a href="#">주문관리</a>
        </li>
    </ul>

    <table class="table table-striped table-hover" style="width: 50%; margin: 0 auto; cursor: hand;">
        <thead>
        <tr>
            <th>NAME</th>
            <th>PRICE</th>
            <th>QUANTITY</th>
            <th>STATE</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}"  var="product">
            <tr>
                <td style="width: 40%;">${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>
                    <c:if test="${product.state == 0}">
                        판매대기중
                    </c:if>
                    <c:if test="${product.state == 1}">
                        판매중
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p><a class="btn" href="/shop/addProductForm">상품 추가</a></p>

</html>