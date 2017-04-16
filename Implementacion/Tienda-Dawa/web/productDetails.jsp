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
                <li>
                    <h5>**** Título del comentario</h5>
                    <p class="author">Por samuel, el 6 de agosto de 2021</p>
                    <p class="content">El mando definitivo para PC y la razón es simple, el nuevo mando de XBOX con BLUETOOTH es el mando ideal para disfrutar en un PC, Steam Link y como no en tu Xbox One, funciona a la primera y sin problemas (al menos en mi caso con Windows 10 y un adaptador Bluetooth 4.0 o mi Steam Link), Lo primero que hice fue conectarlo al PC mediante USB y actualizar el mando con la aplicación "Accesorios de Xbox (que se encuentra en la tienda de Windows 10)", luego lo desconecto e inicio el enlace Bluetooth y me conecta a la primera y ya puedo empezar a jugar. Tambien en caso de no disponer de conexion Bluetooth puedes usar cualquier cable tipo microUSB para conectar tu mando.
                        Lo he probado con el Fifa 16 en Origin, múltiples juegos de Steam y el The Witcher 3 en GOG y de momento ningún fallo.</p>
                </li>
            </ul>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp"%>