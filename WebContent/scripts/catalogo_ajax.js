

document.addEventListener("DOMContentLoaded", function() {
    
    // 1. GESTIONE BOTTONE FILTRI (Lato sinistro)
    const bottoneFiltra = document.getElementById("bottoneFiltra");
    if (bottoneFiltra) {
        bottoneFiltra.addEventListener("click", function() {
            // Recuperiamo tutte le checkbox selezionate e uniamo i valori con una virgola (es: "Nike,Puma")
            const marcheSelezionate = Array.from(document.querySelectorAll('input[name="marca"]:checked'))
                                           .map(el => el.value);
            const terreniSelezionati = Array.from(document.querySelectorAll('input[name="terreno"]:checked'))
                                            .map(el => el.value);

            const params = "marca=" + marcheSelezionate.join(",") + "&terreno=" + terreniSelezionati.join(",");
            inviaRichiestaAjax(params);
        });
    }

    // 2. GESTIONE BOTTONE RICERCA (Barra di navigazione in alto)
    const bottoneCerca = document.getElementById("bottoneCerca");
    if (bottoneCerca) {
        bottoneCerca.addEventListener("click", function() {
            const query = document.getElementById("inputRicerca").value;
            // encodeURIComponent "pulisce" il testo per l'URL (es: gestisce gli spazi)
            inviaRichiestaAjax("q=" + encodeURIComponent(query));
        });
    }
});


function inviaRichiestaAjax(params) {
    const xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            // Qui riceveremo il JSON dal server
            const scarpe = JSON.parse(this.responseText);
            aggiornaGriglia(scarpe);
        }
    };
    
    xhr.open("GET", "CatalogoServlet?azione=filtra&" + params, true);
    xhr.send();
}


function aggiornaGriglia(scarpe) {
	
	const griglia = document.querySelector(".griglia-prodotti");
	    griglia.innerHTML = ""; 

	    scarpe.forEach(scarpa => {
	        const articolo = document.createElement("article");
	        articolo.className = "scheda-prodotto";
	        
	        // Usa la variabile globale 'contextPath' definita nella JSP
	        articolo.innerHTML = `
	            <a href="DettaglioScarpaServlet?id=${scarpa.idScarpa}" class="link-neutro">
	                <img src="${contextPath}/${scarpa.immagine}" alt="${scarpa.modello}" class="immagine-scarpa">
	                <h4 class="nome-prodotto">${scarpa.modello}</h4>
	                <p class="prezzo-prodotto">€ ${scarpa.prezzoAttuale}</p>
	            </a>
	        `;
	        griglia.appendChild(articolo);
	    });
}

