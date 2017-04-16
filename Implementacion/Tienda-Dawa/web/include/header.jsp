<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CDProject</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/default.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="stock">Logo</a>
        </div>
        <form class="navbar-form" style="position: absolute; width: 100%; text-align: center; margin: 8px auto;" action="stock" method="get">
            <div class="input-group">
                <input type="hidden" name="action" value="search">

                <div class="input-group-btn">
                    <select class="form-control" name="type">
                        <option value="ALL">Todos los departamentos</option>
                        <option value="CD">CD's</option>
                        <option value="CACTUS">Cactus</option>
                    </select>
                </div>
                <input type="text" name="name" class="form-control" placeholder="Buscar productos..."
                       <c:if test="${not empty param.name}">value="${param.name}"</c:if> style="width: 500px;">
                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${not empty sessionScope.username}">
                    <li><a href="clientSettings.jsp"><span class="glyphicon glyphicon-user"></span> Hola, ${sessionScope.username}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="clientAuth.jsp"><span class="glyphicon glyphicon-user"></span> Mi cuenta</a></li>
                </c:otherwise>
            </c:choose>
            <li><a href="shoppingCart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span>
                Carrito <span class="badge">${sessionScope.shoppingCart.size}</span></a></li>
        </ul>
    </div>
</nav>