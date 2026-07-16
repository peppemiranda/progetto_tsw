<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Report Ordini - PianetaCalcio</title>

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
                <a href="AdminServlet" class="link-accesso">Torna alla Dashboard</a>
            </div>
        </nav>
    </header>
    
    <main class="contenitore-principale">
        <h2 class="titolo-form">Storico Ordini Clienti</h2>

        <section class="sezione-tabella-admin">
            <c:choose>
                <c:when test="${not empty listaOrdini}">
                    <table class="tabella-admin">
                        <thead>
                            <tr>
                                <th>ID Ordine</th>
                                <th>ID Cliente</th>
                                <th>Data Acquisto</th>
                                <th>Totale Ordine</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ordine" items="${listaOrdini}">
                                <tr>
                                    <td>#${ordine.idOrdine}</td>
                                    <td>Utente ID: ${ordine.idUtente}</td>
                                    <td>${ordine.dataAcquisto}</td>
                                    <td>€ ${ordine.totaleOrdine}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
                    </c:when>
                <c:otherwise>
                    <p class="messaggio-vuoto">Nessun ordine presente al momento nel database.</p>
                </c:otherwise>
            </c:choose>
        </section>

    </main>
</body>
</html>