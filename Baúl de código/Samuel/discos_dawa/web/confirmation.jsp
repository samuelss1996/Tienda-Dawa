<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Confirmar compra</title>
    <meta charset="utf-8"/>
</head>
<body>
    <center>
        <h1>Caja</h1>

        <table border="1">
            <tr><th>Total a pagar</th></tr>
            <tr><td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${sessionScope.shopCart.totalPrize}"/> $</td></tr>
        </table>

        <p>Se enviará la factura por correo electrónico a: <strong>${sessionScope.user.email}</strong></p>

        <form action="shop" method="post">
            <input type="hidden" name="action" value="finish-shopping">
            <input type="submit" value="Pagar y volver a la página principal">
        </form>
    </center>
</body>
</html>