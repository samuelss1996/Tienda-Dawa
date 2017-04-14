<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="include/header.jsp"%>
<div class="container" style="margin-top: 100px;">
    <c:if test="${param.success == 'register'}">
        <div class="alert alert-success">Te has registrado correctamente.</div>
    </c:if>

    <c:if test="${param.error == 'register'}">
        <div class="alert alert-danger">
            Se ha producido un error. Asegúrese de que ha rellenado todos los campos correctamente. Se debe introducir
            un email válido y las dos contraseñas deben coincidir.
        </div>
    </c:if>

    <div class="row">
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">Registro</div>
                <div class="panel-body">
                    <form method="post" action="user">
                        <div class="row">
                            <div class="form-group col-sm-6">
                                <label for="register-name">Nombre de usuario:</label>
                                <input type="text" class="form-control" id="register-name" name="username" required>
                            </div>
                            <div class="form-group col-sm-6">
                                <label for="register-email">Correo electrónico:</label>
                                <input type="email" class="form-control" id="register-email" name="email" required>
                            </div>

                            <div class="form-group col-sm-6">
                                <label for="register-password">Contraseña:</label>
                                <input type="password" class="form-control" id="register-password" name="password" required>
                            </div>

                            <div class="form-group col-sm-6">
                                <label for="register-password-again">Confirmar contraseña:</label>
                                <input type="password" class="form-control" id="register-password-again" name="password-again" required>
                            </div>
                        </div>

                        <input type="hidden" name="action" value="registerClient">
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