<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Il tuo Carrello - PianetaCalcio</title>

<link rel="stylesheet" type="text/css" href="styles/style.css">

</head>
<body>

	<header class="intestazione-sito">
        <a href="CatalogoServlet" class="titolo-sito">PianetaCalcio</a>
    </header>
    
    <main class="contenitore-principale">
        <h2>Il tuo Carrello</h2>
        <c:choose>
            <c:when test="${not empty carrello}">
                <table>
                    <tr><th>Modello</th><th>Marca</th><th>Prezzo</th><th>Azione</th></tr>
                    <c:forEach var="scarpa" items="${carrello}">
                        <tr>
                            <td><c:out value="${scarpa.modello}" /></td>
                            <td><c:out value="${scarpa.marca}" /></td>
                            <td>€ <c:out value="${scarpa.prezzoAttuale}" /></td>
                            <td><a href="CarrelloServlet?azione=rimuovi&id=${scarpa.idScarpa}">Rimuovi</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
                <a href="CarrelloServlet?azione=svuota">Svuota tutto</a>
            </c:when>
            <c:otherwise>
                <p>Il carrello è vuoto.</p>
            </c:otherwise>
        </c:choose>
    </main>

</body>
</html>