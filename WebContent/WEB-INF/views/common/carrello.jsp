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
            <c:when test="${not empty sessionScope.carrello}">
                <table>
                    <tr><th>Modello</th><th>Marca</th><th>Prezzo</th><th>Azione</th></tr>
                    <c:forEach var="scarpa" items="${sessionScope.carrello}">
                        <tr>
                            <td><c:out value="${scarpa.modello}" /></td>
                            <td><c:out value="${scarpa.marca}" /></td>
                            <td>€ <c:out value="${scarpa.prezzoAttuale}" /></td>
                            <td>
                                <form action="CarrelloServlet" method="POST" style="display:inline;">
                                    <input type="hidden" name="azione" value="rimuovi">
                                    <input type="hidden" name="id" value="${scarpa.idScarpa}">
                                    <button type="submit" style="background: none; border: none; color: red; text-decoration: underline; cursor: pointer; font-size: 16px;">Rimuovi</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                
                <form action="CarrelloServlet" method="POST" style="margin-top: 15px; margin-bottom: 15px;">
                    <input type="hidden" name="azione" value="svuota">
                    <button type="submit" style="background: none; border: none; color: red; text-decoration: underline; cursor: pointer; font-size: 16px;">Svuota tutto</button>
                </form>
                
                <form action="CheckoutServlet" method="GET">
    				<button type="submit" class="bottone-verde">Procedi all'acquisto</button>
			    </form>
			
            </c:when>
            <c:otherwise>
                <p>Il carrello è vuoto.</p>
            </c:otherwise>
        </c:choose>
    </main>

</body>
</html>