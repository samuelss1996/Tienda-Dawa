<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="/WEB-INF/productTagLib.tld" %>
<%@include file="include/header.jsp"%>
<div class="container">
    <div class="row well well-lg">
        <div class="col-md-3">
            <img src="img/${item.id}.jpg">
            <p>Valoración media: ****</p>
        </div>

        <div class="col-md-6">
            <h3>${item.productName}</h3>
            <p:details type="${item.type}" product="${item}"/>
        </div>

        <div class="col-md-3">
            <h1><span class="label label-primary pull-right">${item.priceAsString} €</span></h1>

            <div class="input-group pull-right" style="margin-top: 25px">
                <form>
                    <div class="input-group-btn">
                        <input type="number" class="form-control" min="1" max="999" value="1" style="width: 75px;">
                        <button class="btn btn-warning" type="submit">
                            <i class="glyphicon glyphicon-shopping-cart"></i> Añadir al carrito
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Opinar sobre el producto</div>
        <div class="panel-body">

            <form id="addReview">
                <input type="number" class="form-control" min="0" max="5" value="5" name="ratingValue"/>
                <p>Aquí irían las estrellitas</p>

                <div class="checkbox">
                    <label><input type="checkbox" onchange="$('.collapse').collapse('toggle')"> Deseo añadir un comentario a mi valoración</label>
                </div>

                <div class="collapse">
                    <div class="form-group">
                        <label for="title">Título:</label>
                        <input type="text" class="form-control" id="title" name="ratingTitle" required>
                    </div>
                    <div class="form-group">
                        <label for="content">Comentario:</label>
                        <textarea class="form-control" id="content" name="ratingContent" rows="20" required></textarea>
                    </div>
                </div>

                <input type="hidden" name="itemId" value="${item.id}"/>
                <input type="hidden" name="action" value="addRating" />
                <button type="submit" class="btn btn-success">Enviar valoración</button>
            </form>
        </div>
    </div>

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