<%@include file="../include/headerAdmin.jsp"%>
<div class="container">
    <jsp:include page="../include/tableHeaderAdmin.jsp">
        <jsp:param name="section" value="products"/>
    </jsp:include>

    <select id="type" class="form-control pull-right" style="width: initial; color: #EEEEEE;">
        <option>CD</option>
    </select>
    <label for="type" class="pull-right" style="color: #EEEEEE; margin: 7px;">Tipo de producto: </label>

    <div class="row" style="margin-top: 75px;">
        <div class="col-md-4">
            <div class="well">
                <h4>DragonForce - The Power Within</h4>
                <div class="form-group">
                    <label>Precio: <input type="text" class="form-control"></label>
                </div>
                <div class="form-group">
                    <label>Stock: <input type="number" class="form-control"></label>
                </div>
                <p>Más detalles específicos</p>
            </div>
        </div>
    </div>
</div>
<%@include file="../include/footerAdmin.jsp"%>