<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>addProduct</title>
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body style="text-align: center">

<a href="/shop/login">logout</a>
<h1> Add Product </h1>
<br>
<p><a href="/admin/product"><- admin 페이지로 </a></p>
<form action="/admin/modifyProduct" method="post">
    <input type="hidden" name="id" value="${product.getId()}">
<table class="table table-striped" style="width: 50%; margin: 0 auto;">
    <tr>
        <td>상품명</td>
        <td><input type="text" name="name" value="${product.name}"></td>
    </tr>
    <tr>
        <td>가격</td>
        <td><input type="text" name="price" value="${product.price}"></td>
    </tr>
    <tr>
        <td>수량</td>
        <td><input type="text" name="quantity" value="${product.quantity}"></td>
    </tr>
    <tr>
        <td>판매상태</td>
        <td>
            <c:if test="${product.state == 0}">
                <input type="radio" name="state" value="0" checked="checked">판매대기
                <input type="radio" name="state" value="1">판매
            </c:if>
            <c:if test="${product.state == 1}">
                <input type="radio" name="state" value="0">판매대기
                <input type="radio" name="state" value="1" checked="checked">판매
            </c:if>
        </td>
    </tr>
    <tr>
        <td></td>
        <td><input type="submit" class="btn" value="완료"/> </td>
    </tr>
</table>

</body>
</html>