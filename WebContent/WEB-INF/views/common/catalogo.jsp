<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
                <a href="CatalogoServlet">
                    <img src="images/logo.png" alt="PianetaCalcio Logo" class="immagine-logo">
                </a>
            </div>
            
            <div class="barra-ricerca">
                <form action="CatalogoServlet" method="GET">
                    <input type="text" name="q" placeholder="Cerca scarpette..." class="input-ricerca" required>
                    <button type="submit" class="bottone-verde">Cerca</button>
                </form>
            </div>
            
            <div class="carrello-info">
                <span class="icona-carrello">
                    <img src="images/carrello.png" alt="Carrello" class="immagine-icona-carrello"> Cart: 0
                </span>
                
                <span class="utente-loggato">
                    Benvenuto, <c:out value="${sessionScope.utenteLoggato.nome}" default="Ospite" />!
                </span>
                
                <a href="LoginServlet" class="link-accesso">Accedi / Registrati</a>
            </div>
        </nav>
    </header>
    
    <main class="contenitore-principale">
        
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
                    <label><input type="checkbox" name="terreno" value="FG"> FG (Compatto)</label><br>
                    <label><input type="checkbox" name="terreno" value="SG"> SG (Morbido)</label>
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
                                <img src="images/default.png" alt="<c:out value='${scarpa.nome}' />" class="immagine-scarpa">
                                
                                <h4 class="nome-prodotto"><c:out value="${scarpa.nome}" /></h4>
                                <p class="prezzo-prodotto">€ <c:out value="${scarpa.prezzo}" /></p>
                                
                                <button class="bottone-verde">Aggiungi</button>
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
    
    <footer class="footer-sito">
        <div class="contenitore-footer">
            <span class="link-footer"><a href="#">Chi siamo</a></span>
            <span class="link-footer"><a href="#">Condizioni di vendita</a></span>
            <span class="link-footer"><a href="#">Contatti</a></span>
        </div>
    </footer>
    
</body>
</html>