<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="productTagLib" %>
<%@include file="include/header.jsp"%>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading"> <i class="glyphicon glyphicon-shopping-cart"></i> Carrito de la compra</div>
        <div class="panel-body">
            <table class="table table-hover">
                <c:choose><c:when test="${empty sessionScope.shoppingCart or sessionScope.shoppingCart.size eq 0}">
                    <div class="alert alert-danger">El carrito está vacío.</div>
                </c:when>
                <c:otherwise>
                    <thead>
                        <tr>
                            <th>Producto</th>
                            <th>Precio por unidad</th>
                            <th>Cantidad</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="orderLine" items="${sessionScope.shoppingCart.lines}">
                            <tr>
                                <td>
                                    <div class="col-md-2">
                                        <img src="img/${orderLine.product.id}.png"  onerror="this.src='img/${orderLine.product.type}.png;'">
                                    </div>

                                    <div class="col-md-10">
                                        <h4>${orderLine.product.productName}</h4>
                                    </div>
                                </td>
                                <td><h4>${orderLine.product.priceAsString}</h4></td>
                                <td>
                                    <input type="number" class="form-control pull-right" min="1" max="${orderLine.quantity}" value="${orderLine.quantity}" style="width: 75px;">
                                </td>
                                <td><a href="#" style="margin-left: 25px;">Eliminar</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </c:otherwise></c:choose>
            </table>
        </div>
    </div>

    <div class="pull-right">
        <h2 class="totalPrice">Total (${sessionScope.shoppingCart.size} producto/s): <span class="value">${sessionScope.shoppingCart.totalPriceAsString}€</span></h2>
        <form method="post" action="store">
            <input type="hidden" name="action" value="createOrder"/>
            <button type="submit" class="btn btn-success btn-lg pull-right">Continuar</button>
        </form>
    </div>
</div>
<%@include file="include/footer.jsp"%>