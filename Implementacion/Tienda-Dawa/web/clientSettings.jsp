<%@include file="include/header.jsp"%>
<div class="container">
    <div class="row" style="margin-top: 200px;">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">Cambiar contraseña</div>
                <div class="panel-body">
                    <form>
                        <div class="form-group">
                            <label for="old-password">Contraseña vieja:</label>
                            <input type="password" class="form-control" id="old-password">
                        </div>

                        <div class="form-group">
                            <label for="new-password">Contraseña nueva:</label>
                            <input type="password" class="form-control" id="new-password">
                        </div>

                        <div class="form-group">
                            <label for="new-password-again">Confirmar contraseña:</label>
                            <input type="password" class="form-control" id="new-password-again">
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
                    <p style="text-align: center;"><a class="btn btn-danger">Cerrar sesión</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp"%>