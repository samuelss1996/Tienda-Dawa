<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jsp"%>
<div class="container">
    <div class="alert alert-success">
        <p>Tu pedido se ha realizado correctamente. Se ha enviado un e-mail a <strong>${order.client.email}</strong></p>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Factura</div>
        <div class="panel-body">
            <h3>Datos del cliente</h3>
            <table id="clientOrder" class="table table-striped">
                <thead>
                    <tr>
                        <th>Usuario</th>
                        <th>Fecha</th>
                        <th>Correo electrónico</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${order.client.username}</td>
                        <td>${order.date}</td>
                        <td>${order.client.email}</td>
                    </tr>
                </tbody>
            </table>

            <h3>Datos de la compra</h3>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Precio/unidad</th>
                        <th>Unidades</th>
                        <th>Precio total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="orderLine" items="${order.lines}">
                        <tr>
                            <td>${orderLine.product.productName}</td>
                            <td>${orderLine.product.priceAsString}€</td>
                            <td>${orderLine.quantity}</td>
                            <td>${orderLine.linePriceAsString}€</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="pull-right">
                <h4 class="totalPrice">Base: <span class="value">${order.basePriceAsString}€</span></h4>
                <h4 class="totalPrice">Impuestos (21%): <span class="value">8.68€</span></h4> <%-- TODO: ups --%>
                <h4 class="totalPrice">Descuento (20%): <span class="value">-${order.fullDiscountAsString}€</span></h4>
                <h2 class="totalPrice">Precio final: <span class="value">${order.finalPriceAsString}€</span></h2>
            </div>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp"%>