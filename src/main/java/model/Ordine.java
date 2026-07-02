package model;

import java.sql.Date; //Importazione per far dialogare Java e MySQL sulle date

public class Ordine {

	private int idOrdine;
    private int idUtente; // Questa è la nostra Chiave Esterna (FK) verso l'utente
    private Date dataAcquisto;
    private double totaleOrdine;
}
