<%--
  Created by IntelliJ IDEA.
  User: crist
  Date: 04/03/2017
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="es-ES">
<head>
    <meta charset="UTF-8">
    <title>Musica para DAA</title>
</head>
<body bgcolor="#FDF5E6">
    <table align="center" border="0">
        <tr>
            <td>
            <form action="user" method="post">
                <center><font face="Times New Roman,Times" size="+3">Login</font><br></center>
                <label>Nombre</label>
                <input type="text" name="username" required/><br>
                <label>Contraseña</label>
                <input type="password" name="passwod" required/><br>
                <input type="hidden" name="actionId" value="loginUser"/>
                <input type="submit" value="Login"/>
            </form>
            </td>
        </tr>
        <hr>
        <tr>
            <td>
            <form action="user" method="post">
                <center><font face="Times New Roman,Times" size="+3">Registro</font><br></center>
                <label>Nombre</label>
                <input type="text" name="username" required/><br>
                <label>Contraseña</label>
                <input type="password" name="password" required/><br>
                <label>Email</label>
                <input type="email" name="email" required/><br>
                <input type="hidden" name="actionId" value="registerUser"/>
                <input type="submit" value="Registrarse"/>
            </form>
            </td>
        </tr>
    </table>
    <p>
    <hr>
</body>
</html>
