<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Carrito de la compra</title>
    <meta charset="utf-8"/>
</head>
<body>
    <center>
        <h1>Carrito de la compra</h1>

        <form action="shop" method="post">
            <table border="1">
                <tr>
                    <th>Título del CD</th>
                    <th>Artista</th>
                    <th>Precio/Ud.</th>
                    <th>Cantidad</th>
                    <th>Importe</th>
                    <th></th>
                </tr>
                <c:forEach var="shopLine" items="${sessionScope.shopCart.shopLineList}" varStatus="loop">
                    <tr>
                        <td>${shopLine.product.title}</td>
                        <td>${shopLine.product.artist}</td>
                        <td>${shopLine.product.prize} $</td>
                        <td>${shopLine.quantity}</td>
                        <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${shopLine.totalPrize}"/> $</td>
                        <td><button type="submit" name="cd-index" value="${loop.index}">Eliminar</button></td>
                    </tr>
                </c:forEach>
            </table>

            <input type="hidden" name="action" value="remove-from-cart">
        </form>

        <table border="1" style="margin-top: 2rem;">
            <tr><th>Importe total</th></tr>
            <tr><td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${sessionScope.shopCart.totalPrize}"/> $</td></tr>
        </table>

        <p><a href="index.jsp">Seguir comprando</a></p>

        <hr/>

        <form action="shop" method="post">
            <label style="display: block;">Nombre: <input type="text" name="name" required/></label>
            <label style="display: block; margin: 2rem;">Correo electrónico: <input type="email" name="email" required/></label>

            <input type="hidden" name="action" value="finish-shop-cart"/>
            <input type="submit" value="Pagar" <c:if test="${empty sessionScope.shopCart.shopLineList}">disabled</c:if> />
        </form>
    </center>
</body>
</html>