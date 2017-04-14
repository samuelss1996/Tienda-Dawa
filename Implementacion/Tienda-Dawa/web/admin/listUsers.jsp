<%@include file="../include/headerAdmin.jsp"%>
<div class="container">
    <jsp:include page="../include/tableHeaderAdmin.jsp">
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
            <tr>
                <td>samuel.soutullo</td>
                <td>samuel.soutullo@rai.usc.es</td>
                <td>
                    <input type="password" placeholder="(Sin modificar)">
                    <input type="password" placeholder="Confirmar contraseña">
                </td>
                <td><a href="#">Eliminar</a></td>
            </tr>
        </tbody>
    </table>
</div>
<%@include file="../include/footerAdmin.jsp"%>