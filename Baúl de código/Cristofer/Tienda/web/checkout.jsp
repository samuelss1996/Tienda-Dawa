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
            <th><font face="Times New Roman,Times" size="+3">Caja</font></th>
            <th><img src="" align="center"></th>
        </tr>
    </table>
    <hr>
    <p>
    <table align="center" border="1">
        <tr>
            <th>TOTAL A PAGAR</th>
        </tr>
        <tr>
            <th><fmt:formatNumber type="number" maxFractionDigits="2" value="${sessionScope.shoppingCart.totalPrice}"/></th>
        </tr>
    </table>
    <hr>
    <center>
        <c:choose>
            <c:when test="${userLogged}">
                <form action="store" method="post">
                    <input type="hidden" name="actionId" value="confirmBuy"/>
                    <p>Al confirmar el pago se le remitirá la factura por correo electrónico.</p>
                    <input type="submit" value="Pagar y volver a la página principal"/>
                </form>
            </c:when>
            <c:otherwise>
                <form action="user" method="post">
                    <input type="hidden" name="actionId" value="gotoLogin"/>
                    <input type="submit" value="Login o registrarse"/>
                </form>
            </c:otherwise>
        </c:choose>
    </center>
    <hr>
</body>
</html>

