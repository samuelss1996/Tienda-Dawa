<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${param.type == 'CD'}">
        <div class="form-group">
            <label for="cdTitle">Título:</label>
            <input type="text" class="form-control" id="cdTitle" name="cdTitle" value="${param.cdTitle}">
        </div>
        <div class="form-group">
            <label for="cdAuthor">Artista:</label>
            <input type="text" class="form-control" id="cdAuthor" name="cdAuthor" value="${param.cdAuthor}">
        </div>
        <div class="form-group">
            <label for="minCDYear">Desde el año:</label>
            <input type="number" class="form-control" id="minCDYear" name="minCDYear" value="${param.minCDYear}">
        </div>
        <div class="form-group">
            <label for="maxCDYear">Hasta el año:</label>
            <input type="number" class="form-control" id="maxCDYear" name="maxCDYear" value="${param.maxCDYear}">
        </div>
    </c:when>
    <c:when test="${param.type == 'CACTUS'}">
        <div class="form-group">
            <label for="cactusSpecies">Especie:</label>
            <input type="text" class="form-control" id="cactusSpecies" name="cactusSpecies" value="${param.cactusSpecies}">
        </div>
        <div class="form-group">
            <label for="cactusOrigin">Origen:</label>
            <input type="text" class="form-control" id="cactusOrigin" name="cactusOrigin" value="${param.cactusOrigin}">
        </div>
    </c:when>
</c:choose>