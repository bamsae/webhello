<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>adminBuyList</title>
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="/shop/login">logout</a>
<h1 style="margin-left: 200px;">Admin</h1>
<br>
    <ul class="nav nav-tabs">
        <li>
            <a href="product">상품관리</a>
        </li>
        <li class="active">
            <a href="#">주문관리</a>
        </li>
    </ul>

    <table class="table table-striped table-hover" style="width: 50%; margin: 0 auto; cursor: hand;">
        <thead>
        <tr>
            <th>구매자ID</th>
            <th>상품명</th>
            <th>수량</th>
            <th>배송상태</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${buyLists}"  var="buyList">
            <tr>
                <td style="width: 40%;">${buyList.user.name}</td>
                <td>${buyList.product.name}</td>
                <td>${buyList.productNum}</td>
                <c:if test="${buyList.state == 0}">
                    <td>주문완료</td>
                    <td><input type="submit" onclick="viewContents('${buyList.id}', '1')" value="발송"></td>
                </c:if>
                <c:if test="${buyList.state == 1}">
                    <td>배송중</td>
                    <td><input type="submit" onclick="viewContents('${buyList.id}', '2')" value="배송완료"></td>
                </c:if>
                <c:if test="${buyList.state == 2}">
                    <td>배송완료</td>
                    <td><input type="submit" onclick="delContents('${buyList.id}')" value="내역삭제"/></td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <script type="text/javascript">
        var viewContents = function(id, state) {
            location.href = '/admin/modifyBuyList?id=' + id + '&state=' + state;
        }
        var delContents = function(id) {
            location.href = '/shop/delBuyList?from=admin&id=' + id;
        }
    </script>

</body>

</html>