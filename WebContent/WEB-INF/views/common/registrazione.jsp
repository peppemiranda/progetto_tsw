<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Visto che file .css abbiamo messo un media quries, per controllare come uno smartphone inquadra la nostra pagina, 
	dobbiamo inserire obbligatoriamente ciò: -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Registrazione - PianetaCalcio</title>

<link rel="stylesheet" type="text/css" href="styles/style.css">		<!-- Collegamento al file .css -->

</head>
<body>

<div class="intestazione">
        <a href="index.jsp" class="titolo-sito">PianetaCalcio</a>
    </div>

    <div class="contenitore-form">
        <h2 class="titolo-form">Crea un Account</h2>
        
        <form action="RegistrazioneServlet" method="POST">
            
            <div class="campo-form">
                <label for="nome" class="etichetta-input">Nome:</label>
                <input type="text" id="nome" name="nome" class="input-testo" required>
            </div>
            
            <div class="campo-form">
                <label for="cognome" class="etichetta-input">Cognome:</label>
                <input type="text" id="cognome" name="cognome" class="input-testo" required>
            </div>
            
            <div class="campo-form">
                <label for="email" class="etichetta-input">Indirizzo Email:</label>
                <input type="email" id="email" name="email" class="input-testo" required>
            </div>
            
            <div class="campo-form">
                <label for="password" class="etichetta-input">Password:</label>
                <input type="password" id="password" name="password" class="input-testo" required>
            </div>
            
            <input type="submit" value="Registrati" class="bottone-verde">
            
        </form>

</body>
</html>