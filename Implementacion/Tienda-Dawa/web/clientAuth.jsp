<%@include file="include/header.jsp"%>
<div class="container">
    <div class="row" style="margin-top: 100px;">
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">Registro</div>
                <div class="panel-body">
                    <form>
                        <div class="row">
                            <div class="form-group col-sm-6">
                                <label for="register-name">Nombre de usuario:</label>
                                <input type="text" class="form-control" id="register-name">
                            </div>
                            <div class="form-group col-sm-6">
                                <label for="register-email">Correo electrónico:</label>
                                <input type="email" class="form-control" id="register-email">
                            </div>

                            <div class="form-group col-sm-6">
                                <label for="register-password">Contraseña:</label>
                                <input type="password" class="form-control" id="register-password">
                            </div>

                            <div class="form-group col-sm-6">
                                <label for="register-password-again">Confirmar contraseña:</label>
                                <input type="password" class="form-control" id="register-password-again">
                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary pull-right">Registrarme</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">Inicio de sesión</div>
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

                        <button type="submit" class="btn btn-success pull-right">Iniciar sesión</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp"%>