<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registrazione - PianetaCalcio</title>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>

	<header class="intestazione">
        <a href="CatalogoServlet" class="titolo-sito">PianetaCalcio</a>
    </header>
    
    <main class="contenitore-form">
        <h2 class="titolo-form">Crea un Account</h2>
        
        <c:if test="${param.errore == 'email_esistente'}">
            <p class="messaggio-errore">Questa email è già registrata. Usa un'altra email o accedi.</p>
        </c:if>
        
        <form action="RegistrazioneServlet" method="POST" onsubmit="return validaRegistrazione(this);">
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
                <label for="password" class="etichetta-input">Password (minimo 8 caratteri):</label>
                <input type="password" id="password" name="password" class="input-testo" minlength="8" required>
            </div>
            
            <div class="campo-form">
                <label for="indirizzo" class="etichetta-input">Indirizzo di Spedizione:</label>
                <input type="text" id="indirizzo" name="indirizzo" class="input-testo" required>
            </div>
            
            <input type="submit" value="Registrati" class="bottone-verde">
        </form>
        
        <div class="testo-alternativo">
            Hai già un account?
            <a href="LoginServlet" class="link-verde">Accedi qui</a>
        </div>
    </main>

    <footer class="footer-sito">
        <div class="contenitore-footer">
            <span class="link-footer"><a href="#">Contatti</a></span>
        </div>
    </footer>
	<script src="${pageContext.request.contextPath}/scripts/validazione.js"></script>
</body>
</html>