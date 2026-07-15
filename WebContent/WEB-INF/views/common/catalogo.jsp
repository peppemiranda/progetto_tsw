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
                <a href="CatalogoServlet" class="titolo-sito">PianetaCalcio</a>
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

</body>
</html>