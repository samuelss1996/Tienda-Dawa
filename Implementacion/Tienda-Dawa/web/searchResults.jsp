<%@taglib prefix="p" uri="/WEB-INF/productTagLib.tld" %>

<%@include file="include/header.jsp"%>
<div class="container">
    <div id="searchSide" class="row">
        <div class="col-md-2">
            <div class="panel panel-default">
                <div class="panel-heading">Departamentos</div>
                <div class="panel-body">
                    <ul>
                        <li><a href="stock?action=search&name=${param.name}&type=ALL">Todos</a></li>
                        <li><a href="stock?action=search&name=${param.name}&type=CD">CD's</a></li>
                        <li><a href="stock?action=search&name=${param.name}&type=CACTUS">Cactus</a></li>
                    </ul>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">Filtros</div>
                <div class="panel-body">
                    <form class="form-inline" method="get" action="stock">
                        <input type="hidden" name="action" value="search">
                        <input type="hidden" name="name" value="${param.name}">
                        <input type="hidden" name="type" value="${param.type}">

                        <div class="form-group">
                            <label for="minPrice">Precio mínimo:</label>
                            <input type="number" class="form-control" id="minPrice" name="minPrice" value="${param.minPrice}">
                        </div>
                        <div class="form-group">
                            <label for="maxPrice">Precio máximo:</label>
                            <input type="number" class="form-control" id="maxPrice" name="maxPrice" value="${param.maxPrice}">
                        </div>
                        <c:if test="${not empty param.type && param.type != 'ALL'}">
                            <jsp:include page="include/searchFilters.jsp">
                                <jsp:param name="type" value="${param.type}"/>
                            </jsp:include>
                        </c:if>

                        <button type="submit" class="btn btn-default pull-right" id="applyFilterBtn">Aplicar</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-10">
            <ul class="productList">
                <c:forEach var="result" items="${requestScope.results}">
                    <li class="well well-lg">
                        <div class="row">
                            <a href="stock?action=details&productId=${result.id}&type=${result.type}"><img class="col-md-2" src="img/${result.id}.jpg"></a>

                            <div class="col-md-8">
                                <h3><a href="stock?action=details&productId=${result.id}&type=${result.type}">${result.productName}</a></h3>
                                <c:if test="${not empty param.type && param.type != 'ALL'}">
                                    <p:details type="${param.type}" product="${result}" />
                                </c:if>
                            </div>

                            <div class="col-md-2">
                                <p class="btn btn-primary disabled pull-right" role="button">${result.priceAsString} €</p>
                                <%--TODO implementar el añadir ya--%>
                                <button type="button" class="btn btn-warning pull-right quickAdd"><i class="glyphicon glyphicon-shopping-cart"></i> Añadir ya</button>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>

            <%--TODO hacer que esta verga funcione--%>
            <ul class="pager">
                <li><a href="#">Anterior</a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">Siguiente</a></li>
            </ul>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp"%>