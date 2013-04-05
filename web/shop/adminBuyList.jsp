<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>productList</title>
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1 style="margin-left: 200px;">Guest</h1>
<br>
    <ul class="nav nav-tabs">
        <li>
            <a href="list">상품목록</a>
        </li>
        <li class="active">
            <a href="#">주문내역</a>
        </li>
    </ul>

    <table class="table table-striped table-hover" style="width: 50%; margin: 0 auto; cursor: hand;">
        <thead>
        <tr>
            <th>NAME</th>
            <th>PRICE</th>
            <th>QUANTITY</th>
            <th>배송상태</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${buyLists}"  var="buyList">
            <tr>
                <td style="width: 40%;">${buyList.product.name}</td>
                <td>${buyList.product.price}</td>
                <td>${buyList.productNum}</td>
                <td>
                    <c:if test="${buyList.state == 0}">
                        주문완료
                    </c:if>
                    <c:if test="${buyList.state == 1}">
                        배송중
                    </c:if>
                    <c:if test="${buyList.state == 2}">
                        배송완료
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>

</html>