

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