<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Checkout - PianetaCalcio </title>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>

	<header class="intestazione-sito">
        <a href="CatalogoServlet" class="titolo-sito">PianetaCalcio</a>
    </header>
    
    <main class="contenitore-form">
        <h2 class="titolo-form">Dettagli di Spedizione e Pagamento</h2>
        
        <c:if test="${param.errore == 'campi_vuoti'}">
            <p style="color: red; text-align: center; font-weight: bold;">Tutti i campi sono obbligatori. Per favore, compila il modulo interamente.</p>
        </c:if>
        
        <form action="ConfermaOrdineServlet" method="POST">
            
            <div class="campo-form">
                <label for="indirizzo" class="etichetta-input">Indirizzo di Spedizione:</label>
                <input type="text" id="indirizzo" name="indirizzo" class="input-testo" required>
            </div>
            
            <div class="campo-form">
                <label for="citta" class="etichetta-input">Città:</label>
                <input type="text" id="citta" name="citta" class="input-testo" required>
            </div>
            
            <div class="campo-form">
                <label for="carta" class="etichetta-input">Numero Carta di Credito (16 cifre):</label>
                <input type="text" id="carta" name="carta" class="input-testo" maxlength="16" pattern="\d{16}" title="Devi inserire esattamente 16 numeri" required>
            </div>
            
            <input type="submit" value="Conferma Ordine e Paga" class="bottone-verde">
        </form>
    </main>

</body>
</html>