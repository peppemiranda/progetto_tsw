package model;

import java.sql.Date; //Importazione per far dialogare Java e MySQL sulle date

public class Ordine {

	private int idOrdine;
    private int idUtente; // Questa è la nostra Chiave Esterna (FK) verso l'utente
    private Date dataAcquisto;
    private double totaleOrdine;
    
    // Costruttore vuoto
    public Ordine() {
    }

    //Costruttore con parametri
    public Ordine(int idOrdine, int idUtente, Date dataAcquisto, double totaleOrdine) {
        this.idOrdine = idOrdine;
        this.idUtente = idUtente;
        this.dataAcquisto = dataAcquisto;
        this.totaleOrdine = totaleOrdine;
    }
}
