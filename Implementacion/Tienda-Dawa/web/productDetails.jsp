<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="/WEB-INF/productTagLib.tld" %>
<%@include file="include/header.jsp"%>
<div class="container">
    <c:if test="${param.success == 'rating'}">
        <div class="alert alert-success">Tu valoración ha sido añadida correctamente.</div>
    </c:if>

    <div class="row well well-lg">
        <div class="col-md-3">
            <img src="img/${item.id}.jpg" onerror="this.src='img/${item.type}.png;'">
            <h3><span class="label label-success">Valoración media: ${requestScope.averageRating}/5,0</span></h3>
        </div>

        <div class="col-md-6">
            <h3>${item.productName}</h3>
            <p:details type="${item.type}" product="${item}"/>
        </div>

        <div class="col-md-3">
            <h1><span class="label label-primary pull-right">${item.priceAsString} €</span></h1>

            <div class="input-group pull-right" style="margin-top: 25px">
                <form method="post" action="store">
                    <div class="input-group-btn">
                        <%--TODO controlar el login y la falta de stock y hacer algo con la redirección, ajax si eso --%>
                        <input type="number" class="form-control" min="1" max="${item.stock}" value="1" style="width: 75px;" name="quantity">
                        <input type="hidden" value="${item.id}"           name="productId"/>
                        <input type="hidden" value="${item.productName}"  name="productName"/>
                        <input type="hidden" value="${item.price}"        name="productPrice"/>
                        <input type="hidden" value="${item.stock}"        name="productStock"/>
                        <input type="hidden" value="${item.type}"         name="productType"/>
                        <input type="hidden" value="addToCart" name="action" />
                        <button class="btn btn-warning" type="submit">
                            <i class="glyphicon glyphicon-shopping-cart"></i> Añadir al carrito
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <c:choose>
        <c:when test="${requestScope.isOwner}">
            <div class="panel panel-default">
                <div class="panel-heading">Opinar sobre el producto</div>
                <div class="panel-body">

                    <form id="addReview" method="post" action="stock">
                        <input type="number" class="form-control" min="0" max="5" value="5" name="ratingValue"/>

                        <div class="checkbox">
                            <label><input type="checkbox" onchange="$('.collapse').collapse('toggle')"> Deseo añadir un comentario a mi valoración</label>
                        </div>

                        <div class="collapse">
                            <div class="form-group">
                                <label for="title">Título:</label>
                                <input type="text" class="form-control" id="title" name="ratingTitle">
                            </div>
                            <div class="form-group">
                                <label for="content">Comentario:</label>
                                <textarea class="form-control" id="content" name="ratingContent" rows="20"></textarea>
                            </div>
                        </div>

                        <input type="hidden" name="itemId" value="${item.id}"/>
                        <input type="hidden" name="itemType" value="${item.type}">
                        <input type="hidden" name="action" value="addRating" />
                        <button type="submit" class="btn btn-success">Enviar valoración</button>
                    </form>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">Si compras este producto podrás publicar una opinión sobre él.</div>
        </c:otherwise>
    </c:choose>

    <div class="panel panel-default">
        <div class="panel-heading">Opiniones de clientes</div>
        <div class="panel-body">
            <ul class="reviewList">
                <c:forEach var="rating" items="${ratings}">
                    <li>
                        <h5>${rating.comment.title}</h5>
                        <h7>${rating.value}</h7>
                        <p class="author">Por ${rating.client.username} el ${rating.date}</p>
                        <p class="content">${rating.comment.content}</p>
                    </li>
                </c:forEach>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp"%>