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
                <tr>
                    <td>
                        <div class="col-md-2">
                            <img src="img/1.jpg">
                        </div>

                        <div class="col-md-10">
                            <h4>DragonForce - The Power Within</h4>
                            <p>Cosas específicas</p>
                        </div>
                    </td>
                    <td><h4>50 €</h4></td>
                    <td><h4>1</h4></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="pull-right">
        <h4 class="totalPrice">Total: <span class="value">50€</span></h4>
        <h4 class="totalPrice">Descuento (20%): <span class="value">-10€</span></h4>
        <h2 class="totalPrice">Precio final: <span class="value">40€</span></h2>
        <a href="orderResult.jsp" class="btn btn-success btn-lg pull-right">Pagar y finalizar</a>
    </div>
</div>
<%@include file="include/footer.jsp"%>