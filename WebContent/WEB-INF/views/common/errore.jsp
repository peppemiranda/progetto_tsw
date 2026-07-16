<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Errore di Sistema</title>

<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>

	<header class="intestazione-sito">
        <nav class="barra-navigazione">
            <a href="CatalogoServlet" class="titolo-sito">PianetaCalcio</a>
        </nav>
    </header>
    
    <main class="contenitore-principale">
        <h1 style="color: red; margin-bottom: 20px;">Ops! Qualcosa è andato storto.</h1>
        <p>
            Si è verificato un errore tecnico temporaneo<br>
            Non preoccuparti, il tuo carrello e i tuoi dati sono al sicuro.
        </p>
        
        <form action="CatalogoServlet" method="GET">
            <button type="submit" class="bottone-verde">Torna alla Home</button>
        </form>
    </main>

</body>
</html>