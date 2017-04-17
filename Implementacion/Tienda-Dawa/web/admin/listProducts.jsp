<%@include file="../include/admin/headerAdmin.jsp"%>
<div class="container">
    <jsp:include page="../include/admin/tableHeaderAdmin.jsp">
        <jsp:param name="section" value="products"/>
    </jsp:include>

    <form method="get" action="../administration">
        <input type="hidden" name="action" value="listProducts">
        <select id="type" class="form-control pull-right" name="type" onchange="this.form.submit()" style="width: initial; color: #EEEEEE;">
            <option value="CD" <c:if test="${param.type == 'CD'}">selected</c:if> >CD's</option>
            <option value="CACTUS" <c:if test="${param.type == 'CACTUS'}">selected</c:if> >Cactus</option>
        </select>
    </form>
    <label for="type" class="pull-right" style="color: #EEEEEE; margin: 7px;">Tipo de producto: </label>

    <div class="row" style="margin-top: 75px;">
        <div class="col-md-4 hidden" id="addProductContainer">
            <div class="well" style="padding-bottom: 50px;">
                <h4>Producto nuevo</h4>
                <form method="post" action="../administration">
                    <div class="form-group">
                        <label>Precio base: <input type="text" class="form-control" name="price"></label>
                    </div>
                    <div class="form-group">
                        <label>Stock: <input type="number" class="form-control" name="stock"></label>
                    </div>
                    <%@include file="../include/product/productFields.jsp"%>

                    <input type="hidden" name="type" value="${param.type}">
                    <input type="hidden" name="action" value="addProduct">
                    <button type="submit" class="btn btn-success pull-right">Añadir producto</button>
                </form>
            </div>
        </div>

        <c:if test="${param.error == 'insertError'}">
            <div class="alert alert-danger">
                El elemento insertado ya existe en la base de datos.
            </div>
        </c:if>

        <c:forEach var="product" items="${requestScope.productsList}">
            <div class="col-md-4">
                <div class="well" style="padding-bottom: 50px;">
                    <h4>${product.productName}</h4>
                    <form method="post" action="../administration">
                        <div class="form-group">
                            <label>Precio base: <input type="text" class="form-control" name="price" value="${product.priceAsString}"></label>
                        </div>
                        <div class="form-group">
                            <label>Stock: <input type="number" class="form-control" name="stock" value="${product.stock}"></label>
                        </div>
                        <%@include file="../include/product/productFields.jsp"%>

                        <input type="hidden" name="type" value="${param.type}">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="action" value="editProduct">
                        <button type="submit" class="btn btn-success pull-right">Guardar cambios</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="../include/admin/footerAdmin.jsp"%>