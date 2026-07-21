package model;

import java.sql.Date; 

public class Ordine {

	private int idOrdine;
    private int idUtente; // Questa è la nostra Chiave Esterna (FK) verso l'utente
    private Date dataAcquisto;
    private double totaleOrdine;
    

    public Ordine() {
    }

    public Ordine(int idOrdine, int idUtente, Date dataAcquisto, double totaleOrdine) {
        this.idOrdine = idOrdine;
        this.idUtente = idUtente;
        this.dataAcquisto = dataAcquisto;
        this.totaleOrdine = totaleOrdine;
    }
    
    
    //Metodi Get e Set
   
    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    
    
    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    
    
    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    
    
    public double getTotaleOrdine() {
        return totaleOrdine;
    }

    public void setTotaleOrdine(double totaleOrdine) {
        this.totaleOrdine = totaleOrdine;
    }
    
    
    //Metodo toString (Per il debug)
    public String toString() {
        return "Ordine [idOrdine=" + idOrdine + ", idUtente=" + idUtente 
                + ", dataAcquisto=" + dataAcquisto + ", totaleOrdine=" + totaleOrdine + "]";
    }
}
