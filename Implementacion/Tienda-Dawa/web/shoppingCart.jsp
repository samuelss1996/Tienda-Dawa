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
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <div class="col-md-2">
                                <img src="img/dragon.jpg">
                            </div>

                            <div class="col-md-10">
                                <h4>DragonForce - The Power Within</h4>
                                <p>Cosas específicas</p>
                            </div>
                        </td>
                        <td><h4>50 €</h4></td>
                        <td>
                            <input type="number" class="form-control pull-right" min="1" max="999" value="1" style="width: 75px;">
                        </td>
                        <td><a href="#" style="margin-left: 25px;">Eliminar</a></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="pull-right">
        <h2>Total (1 producto): <span id="totalPrice">50€</span></h2>
        <a href="checkout.jsp" class="btn btn-success btn-lg pull-right">Continuar</a>
    </div>
</div>
<%@include file="include/footer.jsp"%>