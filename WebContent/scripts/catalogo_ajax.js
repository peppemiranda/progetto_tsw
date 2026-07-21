

document.addEventListener("DOMContentLoaded", function() {
    
    const bottoneFiltra = document.getElementById("bottoneFiltra");
    if (bottoneFiltra) {
        bottoneFiltra.addEventListener("click", function() {
			
            // Recuperiamo tutte le checkbox selezionate
            const marcheSelezionate = Array.from(document.querySelectorAll('input[name="marca"]:checked'))
                                           .map(el => el.value);
										   
            const terreniSelezionati = Array.from(document.querySelectorAll('input[name="terreno"]:checked'))
                                            .map(el => el.value);

            const params = "marca=" + marcheSelezionate.join(",") + "&terreno=" + terreniSelezionati.join(",");
            inviaRichiestaAjax(params);
        });
    }


    const bottoneCerca = document.getElementById("bottoneCerca");
    if (bottoneCerca) {
        bottoneCerca.addEventListener("click", function() {
            const query = document.getElementById("inputRicerca").value;

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

