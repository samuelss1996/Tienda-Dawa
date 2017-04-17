<%@include file="include/admin/headerAdmin.jsp"%>
<div class="container">
    <c:if test="${param.success == 'deleteAccount'}">
        <div class="alert alert-success">El usuario ha sido borrado correctamente.</div>
    </c:if>

    <c:if test="${param.success == 'changePassword'}">
        <div class="alert alert-success">La contraseña del usuario se ha cambiado correctamente.</div>
    </c:if>
    <c:if test="${param.error == 'changePassword'}">
        <div class="alert alert-danger">Las contraseña estaba vacía o no coincidía con la confirmación.</div>
    </c:if>

    <jsp:include page="include/admin/tableHeaderAdmin.jsp">
        <jsp:param name="section" value="users"/>
    </jsp:include>

    <table class="table table-hover dark">
        <thead>
            <tr>
                <th width="20%">Nombre</th>
                <th width="30%">Correo electrónico</th>
                <th width="40%">Contraseña</th>
                <th width="10%"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="client" items="${requestScope.clientsList}">
                <tr>
                    <td>${client.username}</td>
                    <td>${client.email}</td>
                    <td>
                        <form action="administration" method="post" class="form-inline">
                            <input type="password" class="form-control" placeholder="Nueva" name="password" style="width: 130px;">
                            <input type="password" class="form-control" placeholder="Confirmar" name="password-again" style="width: 130px;">

                            <input type="hidden" name="action" value="changePassword">
                            <input type="hidden" name="clientId" value="${client.id}">
                            <button type="submit" class="btn btn-default">Cambiar contraseña</button>
                        </form>
                    </td>
                    <td>
                        <form action="administration" method="post">
                            <input type="hidden" name="action" value="deleteAccount">
                            <input type="hidden" name="clientId" value="${client.id}">
                            <button type="submit" class="btn btn-danger">Eliminar cuenta</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="include/admin/footerAdmin.jsp"%>