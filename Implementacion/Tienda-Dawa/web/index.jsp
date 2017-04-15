<%@taglib prefix="p" uri="/WEB-INF/productTagLib.tld" %>

<%@include file="include/header.jsp"%>
<div class="container">
    <div class="page-header">
        <div class='btn-toolbar pull-right'>
            <div class='btn-group'>
                <%--TODO hacer el ver todo--%>
                <button type='button' class='btn btn-default'><span class="glyphicon glyphicon-list"></span> Ver todo</button>
            </div>
        </div>
        <h1>Destacado en CD's</h1>
    </div>

    <div class="row">
        <c:forEach var="cd" items="${requestScope.cds}">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/${cd.id}.jpg">
                    <div class="caption">
                        <h3>${cd.productName}</h3>
                            <p:details type="CD" product="${cd}"/>
                        <p style="text-align: right"><span class="btn btn-primary disabled" role="button">${cd.priceAsString} €</span></p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="page-header">
        <div class='btn-toolbar pull-right'>
            <div class='btn-group'>
                <%--TODO hacer el ver todo--%>
                <button type='button' class='btn btn-default'><span class="glyphicon glyphicon-list"></span> Ver todo</button>
            </div>
        </div>
        <h1>Destacado en Cactus</h1>
    </div>

    <div class="row">
        <c:forEach var="cactus" items="${requestScope.cacti}">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/${cactus.id}.jpg">
                    <div class="caption">
                        <h3>${cactus.productName}</h3>
                        <p:details type="CACTUS" product="${cactus}"/>
                        <p style="text-align: right"><span class="btn btn-primary disabled" role="button">${cactus.priceAsString} €</span></p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="include/footer.jsp"%>