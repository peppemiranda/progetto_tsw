<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Dettaglio Prodotto - PianetaCalcio</title>

<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>

	<header class="intestazione-sito">
        <nav class="barra-navigazione">
            <a href="CatalogoServlet" class="titolo-sito">PianetaCalcio</a>
        </nav>
    </header>
    
    <main class="contenitore-principale">
        <article class="dettaglio-prodotto">
            <img src="${scarpa.immagine}" alt="<c:out value='${scarpa.modello}' />" class="immagine-dettaglio">
            <h1><c:out value="${scarpa.modello}" /></h1>
            <p>Marca: <c:out value="${scarpa.marca}" /></p>
			<p>Prezzo: € <c:out value="${scarpa.prezzoAttuale}" /></p>

				<c:if test="${empty sessionScope.utenteLoggato or sessionScope.utenteLoggato.ruolo != 'Admin'}">
    <form action="CarrelloServlet" method="POST">
        <input type="hidden" name="azione" value="aggiungi">
        <input type="hidden" name="id" value="${scarpa.idScarpa}">
        <button type="submit" class="bottone-verde">Aggiungi al Carrello</button>
    </form>
			</c:if>

	</article>
    </main>

</body>
</html>