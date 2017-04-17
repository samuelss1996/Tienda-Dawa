<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="productTagLib" %>
<%@include file="include/header.jsp"%>
<div class="container">
    <c:if test="${param.error == 'outOfStockWhenAddToCart'}">
        <div class="alert alert-danger">
            No se ha podido añadir el producto o productos al carrito por falta de stock.
        </div>
    </c:if>
    <c:if test="${param.error == 'outOfStockDuringProcess'}">
        <div class="alert alert-danger">
            No se ha podido continuar con el proceso de compra porque el stock de uno o varios productos ha cambiado,
            y actualmente no es suficiente para satisfacer su compra.
        </div>
    </c:if>

    <div class="panel panel-default">
        <div class="panel-heading"> <i class="glyphicon glyphicon-shopping-cart"></i> Carrito de la compra</div>
        <div class="panel-body">
            <table class="table table-hover">
                <c:choose>
                    <c:when test="${empty sessionScope.shoppingCart or sessionScope.shoppingCart.size eq 0}">
                        <div class="alert alert-info">El carrito está vacío.</div>
                    </c:when>
                    <c:otherwise>
                        <thead>
                            <tr>
                                <th>Producto</th>
                                <th>Precio por unidad</th>
                                <th style="width: 15%">Cantidad</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="orderLine" items="${sessionScope.shoppingCart.lines}" varStatus="status">
                                <tr>
                                    <td>
                                        <div class="col-md-2">
                                            <img src="img/${orderLine.product.id}.jpg" onerror="this.src='img/${orderLine.product.type}.png;'">
                                        </div>

                                        <div class="col-md-10">
                                            <h4>${orderLine.product.productName}</h4>
                                        </div>
                                    </td>
                                    <td><h4>${orderLine.product.priceAsString}</h4></td>
                                    <td>
                                        <form action="store" method="post">
                                            <input type="hidden" name="action" value="changeShopCartQuantity">
                                            <input type="hidden" name="lineNumber" value="${status.index}">
                                            <div class="input-group">
                                                <input type="number" class="form-control" name="quantity" min="1" max="${orderLine.product.stock}" value="${orderLine.quantity}" style="width: 75px;">
                                                <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-refresh"></i></button>
                                            </div>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="store" method="post">
                                            <input type="hidden" name="action" value="deleteFromCart">
                                            <input type="hidden" name="lineNumber" value="${status.index}">
                                            <button type="submit" class="btn btn-danger">Eliminar</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </div>

    <c:if test="${not empty sessionScope.shoppingCart && sessionScope.shoppingCart.size != 0}">
        <div class="pull-right">
        <h2 class="totalPrice">Total (${sessionScope.shoppingCart.size} producto/s): <span class="value">${sessionScope.shoppingCart.totalPriceAsString}€</span></h2>
        <c:choose>
            <c:when test="${not empty sessionScope.username}">
                <form method="post" action="store">
                    <input type="hidden" name="action" value="createOrder"/>
                    <button type="submit" class="btn btn-success btn-lg pull-right">Continuar</button>
                </form>
            </c:when>
            <c:otherwise>
                <a href="clientAuth.jsp" class="btn btn-success btn-lg pull-right">Continuar</a>
            </c:otherwise>
        </c:choose>
        </div>
    </c:if>
</div>
<%@include file="include/footer.jsp"%>