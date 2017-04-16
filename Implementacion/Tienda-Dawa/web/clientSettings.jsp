<%@include file="include/header.jsp"%>

<div class="container" style="padding-top: 200px;">
    <c:if test="${param.error == 'changePassword'}">
        <div class="alert alert-danger">
            Alguna de las contraseñas introducidas estaba en blanco o las dos contraseñas nuevas no coinciden. Inténtelo de nuevo.
        </div>
    </c:if>
    <c:if test="${param.error == 'wrongChangePassword'}">
        <div class="alert alert-danger">La contraseña actual introducida no es correcta. Inténtelo de nuevo.</div>
    </c:if>
    <c:if test="${param.success == 'changePassword'}">
        <div class="alert alert-success">La contraseña se ha cambiado correctamente.</div>
    </c:if>

    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">Cambiar contraseña</div>
                <div class="panel-body">
                    <form action="user" method="post">
                        <input type="hidden" name="action" value="changePassword">

                        <div class="form-group">
                            <label for="old-password">Contraseña vieja:</label>
                            <input type="password" class="form-control" id="old-password" name="old-password">
                        </div>

                        <div class="form-group">
                            <label for="new-password">Contraseña nueva:</label>
                            <input type="password" class="form-control" id="new-password" name="new-password">
                        </div>

                        <div class="form-group">
                            <label for="new-password-again">Confirmar contraseña:</label>
                            <input type="password" class="form-control" id="new-password-again" name="new-password-again">
                        </div>
                        <button type="submit" class="btn btn-primary pull-right">Cambiar mi contraseña</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">Cerrar sesión</div>
                <div class="panel-body">
                    <p style="text-align: center;"><a href="user?action=closeSession" class="btn btn-danger">Cerrar sesión</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp"%>