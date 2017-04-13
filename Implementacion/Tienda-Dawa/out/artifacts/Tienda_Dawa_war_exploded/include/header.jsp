<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/default.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">Logo</a>
        </div>
        <form class="navbar-form" style="position: absolute; width: 100%; text-align: center; margin: 8px auto;" action="searchResults.jsp">
            <div class="input-group">
                <div class="input-group-btn">
                    <select class="form-control">
                        <option>Todos los departamentos</option>
                    </select>
                </div>
                <input type="text" class="form-control" placeholder="Buscar productos..." style="width: 500px;">
                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="clientAuth.jsp"><span class="glyphicon glyphicon-user"></span> Mi cuenta</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Carrito <span class="badge">0</span></a></li>
        </ul>
    </div>
</nav>