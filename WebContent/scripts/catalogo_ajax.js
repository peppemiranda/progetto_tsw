

document.addEventListener("DOMContentLoaded", function() {
    const bottone = document.getElementById("bottoneFiltra");
    
    bottone.addEventListener("click", function() {
        // Recuperiamo i filtri selezionati
        const marcheSelezionate = Array.from(document.querySelectorAll('input[name="marca"]:checked'))
                                       .map(el => el.value);
        const terreniSelezionati = Array.from(document.querySelectorAll('input[name="terreno"]:checked'))
                                        .map(el => el.value);

        // Prepariamo i parametri per la richiesta
        const params = "marca=" + marcheSelezionate.join(",") + "&terreno=" + terreniSelezionati.join(",");
        
        inviaRichiestaAjax(params);
    });
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
    griglia.innerHTML = ""; // Puliamo la griglia[cite: 3]
    
    scarpe.forEach(scarpa => {
        // Creiamo dinamicamente l'HTML per ogni scarpa[cite: 3]
        const articolo = document.createElement("article");
        articolo.className = "scheda-prodotto";
        articolo.innerHTML = `
            <a href="DettaglioScarpaServlet?id=${scarpa.idScarpa}" class="link-neutro">
                <img src="images/${scarpa.immagine}" alt="${scarpa.modello}" class="immagine-scarpa">
                <h4 class="nome-prodotto">${scarpa.modello}</h4>
                <p class="prezzo-prodotto">€ ${scarpa.prezzoAttuale}</p>
            </a>
        `;
        griglia.appendChild(articolo);
    });
}

