<%@include file="include/header.jsp"%>
<div class="container">
    <div id="searchSide" class="row">
        <div class="col-md-2">
            <div class="panel panel-default">
                <div class="panel-heading">Departamentos</div>
                <div class="panel-body">
                    <ul>
                        <li>Todos</li>
                    </ul>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">Filtros</div>
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="minPrice">Precio mínimo:</label>
                            <input type="number" class="form-control" id="minPrice">
                        </div>
                        <div class="form-group">
                            <label for="maxPrice">Precio máximo:</label>
                            <input type="number" class="form-control" id="maxPrice">
                        </div>

                        <button type="submit" class="btn btn-default pull-right" id="applyFilterBtn">Aplicar</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-10">
            <ul class="productList">
                <li class="well well-lg">
                    <div class="row">
                        <img class="col-md-2" src="img/dragon.jpg">

                        <div class="col-md-8">
                            <h3>DragonForce - The Power Within</h3>
                            <p>Detalles específicos</p>
                        </div>

                        <div class="col-md-2">
                            <p class="btn btn-primary disabled pull-right" role="button">50€</p>
                            <button type="button" class="btn btn-warning pull-right quickAdd"><i class="glyphicon glyphicon-shopping-cart"></i> Añadir ya</button>
                        </div>
                    </div>
                </li>
            </ul>

            <ul class="pager">
                <li><a href="#">Anterior</a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">Siguiente</a></li>
            </ul>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp"%>