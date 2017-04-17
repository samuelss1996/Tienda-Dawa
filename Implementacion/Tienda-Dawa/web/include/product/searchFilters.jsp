<%@ page import="model.util.UTFUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${param.type == 'CD'}">
        <div class="form-group">
            <label for="cdTitle">Título:</label>
            <input type="text" class="form-control" id="cdTitle" name="cdTitle" <c:if test="${not empty param.cdTitle}">value="<%= UTFUtils.getParameter(request, "cdTitle") %></c:if>">
        </div>
        <div class="form-group">
            <label for="cdAuthor">Artista:</label>
            <input type="text" class="form-control" id="cdAuthor" name="cdAuthor" <c:if test="${not empty param.cdAuthor}">value="<%= UTFUtils.getParameter(request, "cdAuthor") %></c:if>">
        </div>
        <div class="form-group">
            <label for="minCDYear">Desde el año:</label>
            <input type="number" class="form-control" id="minCDYear" name="minCDYear" <c:if test="${not empty param.minCDYear}">value="<%= UTFUtils.getParameter(request, "minCDYear") %></c:if>">
        </div>
        <div class="form-group">
            <label for="maxCDYear">Hasta el año:</label>
            <input type="number" class="form-control" id="maxCDYear" name="maxCDYear" <c:if test="${not empty param.maxCDYear}">value="<%= UTFUtils.getParameter(request, "maxCDYear") %></c:if>">
        </div>
    </c:when>
    <c:when test="${param.type == 'CACTUS'}">
        <div class="form-group">
            <label for="cactusSpecies">Especie:</label>
            <input type="text" class="form-control" id="cactusSpecies" name="cactusSpecies" <c:if test="${not empty param.cactusSpecies}">value="<%= UTFUtils.getParameter(request, "cactusSpecies") %></c:if>">
        </div>
        <div class="form-group">
            <label for="cactusOrigin">Origen:</label>
            <input type="text" class="form-control" id="cactusOrigin" name="cactusOrigin" <c:if test="${not empty param.cactusOrigin}">value="<%= UTFUtils.getParameter(request, "cactusOrigin") %></c:if>">
        </div>
    </c:when>
</c:choose>