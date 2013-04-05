<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>productList</title>
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="/shop/login">logout</a>
<h1 style="margin-left: 200px;">Guest</h1>
<br>
    <ul class="nav nav-tabs">
        <li class="active">
            <a href="#">상품목록</a>
        </li>
        <li>
            <a href="buyList">주문내역</a>
        </li>
    </ul>

    <div><h5>MONEY : ${user.money}</h5></div>

    <table class="table table-striped table-hover" style="width: 50%; margin: 0 auto; cursor: hand;">
        <thead>
        <tr>
            <th>NAME</th>
            <th>PRICE</th>
            <th>QUANTITY</th>
            <th>수량</th>
            <th>구매버튼</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}"  var="product">
            <c:if test="${product.state == 1}">
            <tr>
                <td style="width: 40%;">${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td><input type="number" id="quantity" name="quantity" value="0" style="height: 25px;"></td>
                <td><input type="submit" onclick="viewContents('${product.id}', '${product.quantity}', '${product.price}', '${user.money}')" value="구매"></td>
            </tr>

            </c:if>
        </c:forEach>
        </tbody>
    </table>

    <script type="text/javascript">
        var viewContents = function(id, prqua, price, money) {
            var qua = document.getElementById("quantity").value;

            if(parseInt(qua) > 0) {
                window.alert("한개 이상 사시죠?");
            }
            else if(parseInt(qua) > prqua) {
                window.alert("수량이 부족합니다.");
            }
            else if(parseInt(qua)*price > money) {
                window.alert("잔액이 부족합니다.");
            }
            else {
                location.href = '/shop/buy?id=' + id + '&quantity=' + qua;
            }
        }
    </script>

</body>

</html>