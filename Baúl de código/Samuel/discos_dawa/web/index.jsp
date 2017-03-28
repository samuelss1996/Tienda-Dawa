<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Música para DAWA</title>
    <meta charset="utf-8"/>
</head>
<body>
    <center>
        <h1>Música para DAWA</h1>

        <form action="shop" method="post">
            <label for="cd-name"><strong>CD:</strong></label>
            <select name="cd-name" id="cd-name">
                <option>Yuan | The Guo Brothers | China | $14.95</option>
                <option>Drums of Passion | Babatunde Olatunji | Nigeria | $16.95</option>
                <option>Kaira | Tounami Diabate | Mali | $16.95</option>
                <option>The Lion is Loose | Eliades Ochoa | Cuba | $13.95</option>
                <option>Dance the Devil Away | Outback | Australia | $14.95</option>
                <option>Record of Changes | Samulnori | Korea | $12.95</option>
                <option>Djelika | Tounami Diabate | Mali | $14.95</option>
                <option>Rapture | Nusrat Fateh Ali Khan | Pakistan | $12.95</option>
                <option>Cesaria Evora | Cesaria Evora | Cape Verde | $16.95</option>
                <option>DAA | GSTIC | Spain | $50.00</option>
            </select>

            <label for="cd-quantity"><strong>Cantidad:</strong></label>
            <input type="number" min="1" name="cd-quantity" id="cd-quantity" value="1"/>
            <input type="hidden" name="action" value="add-to-cart">

            <input type="submit" value="Seleccionar producto" style="display: block; margin-top: 2rem"/>
        </form>
    </center>
</body>
</html>