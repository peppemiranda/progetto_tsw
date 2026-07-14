<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Visto che file .css abbiamo messo un media quries, per controllare come uno smartphone inquadra la nostra pagina, 
	dobbiamo inserire obbligatoriamente ciò: -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Accedi - PianetaCalcio</title>
    
<link rel="stylesheet" type="text/css" href="../../styles/style.css">	<!-- Collegamento al file .css -->
</head>
<body>

<div class="intestazione">
        <a href="index.jsp" class="titolo-sito">PianetaCalcio</a>
    </div>

    <div class="contenitore-form">
        <h2 class="titolo-form">Accesso Utente</h2>
        
        <form action="LoginServlet" method="POST">
            
            <div class="campo-form">
                <label for="email" class="etichetta-input">Indirizzo Email:</label>
                <input type="text" id="email" name="email" class="input-testo">
            </div>
            
            <div class="campo-form">
                <label for="password" class="etichetta-input">Password:</label>
                <input type="password" id="password" name="password" class="input-testo">
            </div>
            
            <input type="submit" value="Entra nel sito" class="bottone-verde">
            
        </form>
        
        <div class="testo-alternativo">
            Non hai ancora un account? <a href="registrazione.jsp" class="link-verde">Registrati ora</a>
        </div>
    </div>

</body>
</html>