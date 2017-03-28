<%--
  Created by IntelliJ IDEA.
  User: crist
  Date: 21/02/2017
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es-ES">
<head>
  <meta charset="UTF-8">
  <title>Musica para DAA</title>
</head>
<body bgcolor="#FDF5E6">
  <table align="center" border="0">
    <tr>
      <th><img src="" align="center"></th>
      <th><font face="Times New Roman,Times" size="+3">Música para DAA</font></th>
      <th><img src="" align="center"></th>
    </tr>
  </table>
  <hr>
  <p>
  <center>
    <form action="store" method="post">
      <b>CD:</b>
      <select name="cdInfo">
        <option>Yuan                 | The Guo Brothers      | China      | $14.95</option>
        <option>Drums of Passion     | Babatunde Olatunji    | Nigeria    | $16.95</option>
        <option>Kaira                | Tounami Diabate       | Mali       | $16.95</option>
        <option>The Lion is Loose    | Eliades Ochoa         | Cuba       | $13.95</option>
        <option>Dance the Devil Away | Outback               | Australia  | $14.95</option>
        <option>Record of Changes    | Samulnori             | Korea      | $12.95</option>
        <option>Djelika              | Tounami Diabate       | Mali       | $14.95</option>
        <option>Rapture              | Nusrat Fateh Ali Khan | Pakistan   | $12.95</option>
        <option>Cesaria Evora        | Cesaria Evora         | Cape Verde | $16.95</option>
        <option>DAA                  | GSTIC                 | Spain      | $50.00</option>
      </select>
      <b>Cantidad:</b>
      <input type="number" name="quantity" value="1" min="0"/>
      <p>
      <center>
        <input type="hidden" name="actionId" value="addItem"/>
        <input type="submit" value="Selecciona Producto"/>
      </center>
    </form>
  </center>
  <hr>
</body>
</html>