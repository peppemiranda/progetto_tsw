<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Gestione Catalogo - PianetaCalcio</title>

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
                <a href="AdminServlet" class="link-accesso">Torna alla Dashboard</a>
            </div>
        </nav>
    </header>
    
    <main class="contenitore-principale">
        <h2 class="titolo-form">Gestione Catalogo Scarpe</h2>

        <!--Form di inserimento/modifica -->
        
        <section class="sezione-form-admin">
            <h3>${not empty scarpaDaModificare ? 'Modifica Scarpa' : 'Aggiungi Nuova Scarpa'}</h3>
            
            <form action="AdminServlet" method="POST" class="form-registrazione">
                <input type="hidden" name="operazione" value="${not empty scarpaDaModificare ? 'modifica' : 'aggiungi'}">
                
                <c:if test="${not empty scarpaDaModificare}">
                    <input type="hidden" name="idScarpa" value="${scarpaDaModificare.idScarpa}">
                </c:if>

                <div class="gruppo-input">
                    <label>Marca:</label>
                    <input type="text" name="marca" value="${scarpaDaModificare.marca}" required>
                </div>
                
                <div class="gruppo-input">
                    <label>Modello:</label>
                    <input type="text" name="modello" value="${scarpaDaModificare.modello}" required>
                </div>
                
                <div class="gruppo-input">
                    <label>Terreno (es. FG, SG, TF):</label>
                    <input type="text" name="terreno" value="${scarpaDaModificare.terreno}" required>
                </div>
                
                <div class="gruppo-input">
                    <label>Prezzo (Usa il punto per i decimali):</label>
                    <input type="number" step="0.01" name="prezzo" value="${scarpaDaModificare.prezzoAttuale}" required>
                </div>
                
                <div class="gruppo-input">
                    <label>Pezzi in Magazzino:</label>
                    <input type="number" name="pezziMagazzino" value="${scarpaDaModificare.pezziMagazzino}" required>
                </div>
                
                <div class="gruppo-input">
                    <label>Percorso Immagine (es. images/scarpe/1.png):</label>
                    <input type="text" name="immagine" value="${scarpaDaModificare.immagine}" required>
                </div>

                <button type="submit" class="bottone-verde">
                    ${not empty scarpaDaModificare ? 'Aggiorna Scarpa' : 'Aggiungi al Catalogo'}
                </button>
            </form>
        </section>

</body>
</html>