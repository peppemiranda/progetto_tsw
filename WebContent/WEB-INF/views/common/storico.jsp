<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Storico Ordini - PianetaCalcio</title>

<link rel="stylesheet" type="text/css" href="styles/style.css">

</head>
<body>

	<header class="intestazione-sito">
        <nav class="barra-navigazione">
            <div class="logo">
                <a href="CatalogoServlet">
                    <img src="images/logo.png" alt="PianetaCalcio Logo" class="immagine-logo">
                </a>
            </div>
            
            <div class="carrello-info">
                <a href="CarrelloServlet" class="link-neutro">
                    <img src="images/carrello.png" alt="Carrello" class="immagine-icona-carrello"> 
                    Cart: <c:out value="${not empty sessionScope.carrello ? sessionScope.carrello.size() : 0}" />
                </a>
                <span class="utente-loggato">
                    Benvenuto, <c:out value="${sessionScope.utenteLoggato.nome}" default="Ospite" />!
                </span>
            </div>
        </nav>
    </header>
    
    <main class="contenitore-principale">
        <h2>Il tuo Storico Ordini</h2>
        
        <c:choose>
            <c:when test="${not empty listaOrdini}">
                <table class="tabella-ordini">
                    <tr>
                        <th>Numero Ordine</th>
                        <th>Totale Pagato</th>
                    </tr>
                    <c:forEach var="ordine" items="${listaOrdini}">
                        <tr>
                            <td><c:out value="${ordine.idOrdine}" /></td>
                            <td>€ <c:out value="${ordine.totaleOrdine}" /></td>
                        </tr>
                    </c:forEach>
                </table>
                
                </c:when>
            <c:otherwise>
                <p>Non hai ancora effettuato nessun ordine.</p>
            </c:otherwise>
        </c:choose>
        
        <form action="CatalogoServlet" method="GET" class="form-spaziato">
            <button type="submit" class="bottone-verde">Torna al Catalogo</button>
        </form>
    </main>

</body>
</html>