<c:choose>
    <c:when test="${requestScope.type == 'CD'}">
        <div class="form-group">
            <label for="cdTitle">Título:</label>
            <input type="text" class="form-control" id="cdTitle" name="cdTitle" value="${product.title}">
        </div>
        <div class="form-group">
            <label for="cdAuthor">Artista:</label>
            <input type="text" class="form-control" id="cdAuthor" name="cdAuthor" value="${product.author}">
        </div>
        <div class="form-group">
            <label for="cdYear">Año:</label>
            <input type="number" class="form-control" id="cdYear" name="cdYear" value="${product.year}">
        </div>
    </c:when>
    <c:when test="${requestScope.type == 'CACTUS'}">
        <div class="form-group">
            <label for="cactusSpecies">Especie:</label>
            <input type="text" class="form-control" id="cactusSpecies" name="cactusSpecies" value="${product.species}">
        </div>
        <div class="form-group">
            <label for="cactusOrigin">Origen:</label>
            <input type="text" class="form-control" id="cactusOrigin" name="cactusOrigin" value="${product.origin}">
        </div>
    </c:when>
</c:choose>