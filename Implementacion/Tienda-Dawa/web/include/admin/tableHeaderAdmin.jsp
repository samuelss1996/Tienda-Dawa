<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav nav-tabs">
    <li <c:if test="${param.section == 'users'}">class="active" </c:if>><a href="../../admin/listUsers.jsp">Administrar usuarios</a></li>
    <li <c:if test="${param.section == 'products'}">class="active" </c:if>><a href="../../admin/listProducts.jsp">Administrar productos</a></li>
</ul>