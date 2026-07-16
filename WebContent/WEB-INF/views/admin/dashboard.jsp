<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Dashboard Admin - PianetaCalcio</title>

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
                <span class="utente-loggato">
                    Admin: <c:out value="${sessionScope.utenteLoggato.nome}" />
                </span>
                <a href="LogoutServlet" class="link-accesso">Esci dal Pannello</a>
            </div>
        </nav>
    </header>
    
    <main class="contenitore-principale">
        <h2 class="titolo-form">Pannello di Amministrazione</h2>
        
        <div class="contenitore-dashboard">
            
            <article class="scheda-admin">
                <h3 class="titolo-sezione-admin">Gestione Catalogo</h3>
                <p class="testo-admin">Aggiungi, modifica o elimina le scarpette dal database del negozio.</p>
                <form action="AdminServlet" method="GET" class="form-spaziato">
                    <input type="hidden" name="azione" value="gestioneCatalogo">
                    <button type="submit" class="bottone-verde">Vai al Catalogo</button>
                </form>
            </article>
            
            <article class="scheda-admin">
                <h3 class="titolo-sezione-admin">Report Ordini</h3>
                <p class="testo-admin">Visualizza le statistiche e filtra gli acquisti effettuati dai clienti.</p>
                <form action="AdminServlet" method="GET" class="form-spaziato">
                    <input type="hidden" name="azione" value="reportOrdini">
                    <button type="submit" class="bottone-verde">Vai ai Report</button>
                </form>
            </article>
            
        </div>
    </main>
    
    <footer class="footer-sito">
        <div class="contenitore-footer">
            <span class="link-footer">PianetaCalcio - Area Riservata Amministratore</span>
        </div>
    </footer>
    
</body>
</html>