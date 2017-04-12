<%@include file="../include/headerAdmin.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-sm-4 col-sm-offset-4" style="margin-top: 100px;">
            <div class="panel panel-default">
                <div class="panel-heading">Panel de administración</div>
                <div class="panel-body">
                    <form>
                        <div class="form-group">
                            <label for="login-name">Nombre de usuario:</label>
                            <input type="text" class="form-control" id="login-name">
                        </div>
                        <div class="form-group">
                            <label for="login-password">Contraseña:</label>
                            <input type="password" class="form-control" id="login-password">
                        </div>

                        <button type="submit" class="btn btn-default pull-right">Iniciar sesión</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>