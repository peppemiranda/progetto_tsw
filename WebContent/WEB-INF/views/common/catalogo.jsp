<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Catalogo - PianetaCalcio</title>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
    <header class="intestazione-sito">
        <nav class="barra-navigazione">
            <div class="logo">
                <a href="CatalogoServlet"><img src="images/logo.png" alt="PianetaCalcio Logo" class="immagine-logo"></a>
            </div>
            <div class="barra-ricerca">
                <form action="CatalogoServlet" method="GET">
                    <input type="text" name="q" placeholder="Cerca scarpette..." class="input-ricerca" required>
                    <button type="submit" class="bottone-verde">Cerca</button>
                </form>
            </div>
            <div class="carrello-info">
                <a href="CarrelloServlet" class="link-neutro">
                    <img src="images/carrello.png" alt="Carrello" class="immagine-icona-carrello"> 
                    Cart: <c:out value="${not empty sessionScope.carrello ? sessionScope.carrello.size() : 0}" />
                </a>

                <c:choose>
                    <c:when test="${not empty sessionScope.utenteLoggato}">
                        <span class="utente-loggato">Benvenuto, <c:out value="${sessionScope.utenteLoggato.nome}" />!</span>
                        
                        <%-- Se è Admin, mostriamo il tasto Dashboard --%>
                        <c:if test="${sessionScope.utenteLoggato.ruolo == 'Admin'}">
                            <a href="AdminServlet" class="link-accesso" style="color: #ff9800; font-weight: bold; margin-left: 10px;">Area Admin</a>
                        </c:if>
                        
                        <a href="LogoutServlet" class="link-accesso" style="margin-left: 10px;">Logout</a>
                    </c:when>
                    <c:otherwise>
                        <span class="utente-loggato">Benvenuto, Ospite!</span>
                        <a href="LoginServlet" class="link-accesso">Accedi / Registrati</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </nav>
    </header>
    
    <main class="contenitore-principale">
        <c:if test="${param.acquisto == 'successo'}">
            <h3 class="messaggio-successo">Ordine completato con successo! Grazie per l'acquisto.</h3>
        </c:if>

        <aside class="colonna-filtri">
            <h3 class="titolo-filtri">FILTRI RICERCA</h3>
            <form action="CatalogoServlet" method="GET">
                <div class="gruppo-filtro">
                    <h4 class="nome-filtro">Marca</h4>
                    <label><input type="checkbox" name="marca" value="Nike"> Nike</label><br>
                    <label><input type="checkbox" name="marca" value="Adidas"> Adidas</label><br>
                    <label><input type="checkbox" name="marca" value="Puma"> Puma</label>
                </div>
                <div class="gruppo-filtro">
                    <h4 class="nome-filtro">Terreno</h4>
                    <label><input type="checkbox" name="terreno" value="AG">AG</label><br>
                    <label><input type="checkbox" name="terreno" value="TF">TF</label>
                </div>
                <button type="submit" class="bottone-verde">Applica Filtri</button>
            </form>
        </aside>
        
        <section class="sezione-catalogo">
            <div class="griglia-prodotti">
                <c:choose>
                    <c:when test="${not empty listaScarpe}">
                        <c:forEach var="scarpa" items="${listaScarpe}">
                            <article class="scheda-prodotto">
                                <a href="DettaglioScarpaServlet?id=${scarpa.idScarpa}" class="link-neutro">
                                   <img src="${pageContext.request.contextPath}/${scarpa.immagine}" alt="<c:out value='${scarpa.modello}' />" class="immagine-scarpa">
                                    <h4 class="nome-prodotto"><c:out value="${scarpa.modello}" /></h4>
                                    <p class="prezzo-prodotto">€ <c:out value="${scarpa.prezzoAttuale}" /></p>
                                </a>
                                <form action="CarrelloServlet" method="POST" style="margin-top: 10px;">
                                    <input type="hidden" name="azione" value="aggiungi">
                                    <input type="hidden" name="id" value="${scarpa.idScarpa}">
                                    <button type="submit" class="bottone-verde">Aggiungi</button>
                                </form>
                            </article>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p class="messaggio-vuoto">Nessuna scarpetta attualmente disponibile nel catalogo.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </main>
</body>
</html>