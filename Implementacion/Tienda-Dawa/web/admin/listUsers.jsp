<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/admin.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">Logo</a>
        </div>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="clientAuth.jsp"><span class="glyphicon glyphicon-user"></span> Mi cuenta</a></li>
        </ul>
    </div>
</nav>







<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="#">Administrar usuarios</a></li>
        <li><a href="#">Administrar productos</a></li>
        <a class="btn btn-success pull-right">Guardar cambios</a>
    </ul>

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








</body>
</html>