<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav nav-tabs">
    <li <c:if test="${param.section == 'users'}">class="active" </c:if>><a href="../../admin/listUsers.jsp">Administrar usuarios</a></li>
    <li <c:if test="${param.section == 'products'}">class="active" </c:if>><a href="../../admin/listProducts.jsp">Administrar productos</a></li>
    <a class="btn btn-success pull-right">Guardar cambios</a>
    <c:if test="${param.section == 'products'}"><a class="btn btn-primary pull-right" style="margin-right: 10px;">Nuevo producto</a></c:if>
</ul>