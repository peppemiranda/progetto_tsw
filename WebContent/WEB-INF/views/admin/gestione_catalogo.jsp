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
                    <label>Terreno:</label>
                    <input type="text" name="terreno" value="${scarpaDaModificare.terreno}" required>
                </div>
                
                <div class="gruppo-input">
                    <label>Prezzo:</label>
                    <input type="number" step="0.01" name="prezzo" value="${scarpaDaModificare.prezzoAttuale}" required>
                </div>
                
                <div class="gruppo-input">
                    <label>Pezzi in Magazzino:</label>
                    <input type="number" name="pezziMagazzino" value="${scarpaDaModificare.pezziMagazzino}" required>
                </div>
                
                <div class="gruppo-input">
                    <label>Percorso Immagine:</label>
                    <input type="text" name="immagine" value="${scarpaDaModificare.immagine}" required>
                </div>

                <button type="submit" class="bottone-verde">
                    ${not empty scarpaDaModificare ? 'Aggiorna Scarpa' : 'Aggiungi al Catalogo'}
                </button>
            </form>
        </section>
        
        
        <section class="sezione-tabella-admin">
            <h3>Catalogo Attuale</h3>
            <table class="tabella-admin">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Foto</th>
                        <th>Marca e Modello</th>
                        <th>Terreno</th>
                        <th>Prezzo</th>
                        <th>Q.tà</th>
                        <th>Azioni</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${listaScarpe}">
                        <tr>
                            <td>${s.idScarpa}</td>
                            <td><img src="${pageContext.request.contextPath}/${s.immagine}" alt="Foto" class="img-tabella-admin"></td>
                            <td>${s.marca} ${s.modello}</td>
                            <td>${s.terreno}</td>
                            <td>€ ${s.prezzoAttuale}</td>
                            <td>${s.pezziMagazzino}</td> 
                            
                            <td>

                                <form action="AdminServlet" method="GET" class="form-inline">
                                    <input type="hidden" name="azione" value="gestioneCatalogo">
                                    <input type="hidden" name="idModifica" value="${s.idScarpa}">
                                    <button type="submit" class="btn-modifica">Modifica</button>
                                </form>
                                 

                                <form action="AdminServlet" method="POST" class="form-inline">
                                    <input type="hidden" name="operazione" value="elimina">
                                    <input type="hidden" name="idScarpa" value="${s.idScarpa}">
                                    <button type="submit" class="btn-elimina" onclick="return confirm('Sicuro di voler eliminare questa scarpa?');">Elimina</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

    </main>

</body>
</html>