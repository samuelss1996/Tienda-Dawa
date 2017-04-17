<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jsp"%>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading"> <i class="glyphicon glyphicon-shopping-cart"></i> Carrito de la compra</div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Producto</th>
                    <th>Precio por unidad</th>
                    <th>Cantidad</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="orderLine" items="${order.lines}">
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
                            <td><h4>${orderLine.quantity}</h4></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="pull-right">
        <h4 class="totalPrice">Total: <span class="value">${order.priceWithoutDiscountAsString}€</span></h4>
        <h4 class="totalPrice">Descuento (${order.discount}%): <span class="value">-${order.fullDiscountAsString}€</span></h4>
        <h2 class="totalPrice">Precio final: <span class="value">${order.finalPriceAsString}€</span></h2>
        <form method="post" action="store">
            <input type="hidden" name="action" value="confirmOrder"/>
            <button type="submit" class="btn btn-success btn-lg pull-right">Pagar y finalizar</button>
        </form>
    </div>
</div>
<%@include file="include/footer.jsp"%>