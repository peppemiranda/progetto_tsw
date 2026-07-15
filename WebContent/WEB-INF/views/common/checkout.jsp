<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                <label for="carta" class="etichetta-input">Numero Carta di Credito:</label>
                <input type="text" id="carta" name="carta" class="input-testo" maxlength="16" required>
            </div>
            
            <input type="submit" value="Conferma Ordine e Paga" class="bottone-verde">
        </form>
    </main>

</body>
</html>