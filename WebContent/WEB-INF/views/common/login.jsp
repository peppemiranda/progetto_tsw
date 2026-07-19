<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Accedi - PianetaCalcio</title>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>

	<header class="intestazione">
        <a href="CatalogoServlet" class="titolo-sito">PianetaCalcio</a>
    </header>
    
    <main class="contenitore-form">
        <h2 class="titolo-form">Accesso Utente</h2>
        
        <c:if test="${not empty param.errore}">
            <p class="messaggio-errore">Credenziali non valide. Riprova.</p>
        </c:if>
        
        <form action="LoginServlet" method="POST">
            <div class="campo-form">
                <label for="email" class="etichetta-input">Indirizzo Email:</label>
                <input type="email" id="email" name="email" class="input-testo" required>
            </div>
            
            <div class="campo-form">
                <label for="password" class="etichetta-input">Password:</label>
                <input type="password" id="password" name="password" class="input-testo" required>
            </div>
            
            <input type="submit" value="Entra nel sito" class="bottone-verde">
        </form>
        
        <div class="testo-alternativo">
            Non hai ancora un account?
            <a href="RegistrazioneServlet" class="link-verde">Registrati ora</a>
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