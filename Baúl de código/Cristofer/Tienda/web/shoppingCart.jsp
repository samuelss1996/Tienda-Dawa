<%--
  Created by IntelliJ IDEA.
  User: crist
  Date: 04/03/2017
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="es-ES">
<head>
    <meta charset="UTF-8">
    <title>Musica para DAA</title>
</head>
<body bgcolor="#FDF5E6">
<table align="center" border="0">
    <tr>
        <th><img src="" align="center"></th>
        <th><font face="Times New Roman,Times" size="+3">Carrito de la compra</font></th>
        <th><img src="" align="center"></th>
    </tr>
</table>
<hr>
<p>
    <table align="center" border="1">
        <form action="store" method="post">
        <tr>
            <th>TITULO DEL CD</th>
            <th>Cantidad</th>
            <th>Importe</th>
            <th></th>
        </tr>
        <c:forEach var="cd" items="${sessionScope.shoppingCart.products}">
            <tr>
                <td>${cd.title} | ${cd.artist} | ${cd.country} | $${cd.price}</td>
                <td>${cd.reservedQuantity}</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${cd.price * cd.reservedQuantity}"/></td>
                <td><input type="radio" name="cdTitle" value="${cd.title}"/></td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td>IMPORTE TOTAL</td>
            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${sessionScope.shoppingCart.totalPrice}"/></td>
            <td><input type="submit" value="Eliminar"/></td>
        </tr>
        <input type="hidden" name="actionId" value="removeItem"/>
        </form>
    </table>
    <center>
        <form action="store" method="post">
            <input type="hidden" name="actionId" value="keepShopping"/>
            <input type="submit" value="Seguir comprando" />
        </form>
        <form action="store" method="post">
            <input type="hidden" name="actionId" value="gotoCheckout"/>
            <input type="submit" value="Pagar"/>
        </form>
    </center>
<hr>
</body>
</html>
