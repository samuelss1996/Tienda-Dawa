<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav nav-tabs">
    <li <c:if test="${param.section == 'users'}">class="active" </c:if>><a href="../../administration?action=listUsers">Administrar usuarios</a></li>
    <li <c:if test="${param.section == 'products'}">class="active" </c:if>><a href="../../administration?action=listProducts&type=CD">Administrar productos</a></li>
    <c:if test="${param.section == 'products'}"><a href="#" class="btn btn-primary pull-right" onclick="$('#addProductContainer').removeClass('hidden')">Nuevo producto</a></c:if>
</ul>