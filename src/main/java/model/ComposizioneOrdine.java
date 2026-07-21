package model;

public class ComposizioneOrdine {
	
	private int idOrdine;
    private int idScarpa;
    private double prezzoAcquisto;
    private int quantitaScelta;

    public ComposizioneOrdine() {
    }

    public ComposizioneOrdine(int idOrdine, int idScarpa, double prezzoAcquisto, int quantitaScelta) {
        this.idOrdine = idOrdine;
        this.idScarpa = idScarpa;
        this.prezzoAcquisto = prezzoAcquisto;
        this.quantitaScelta = quantitaScelta;
    }
    
    //Metodi Get e Set
    
    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    
    
    public int getIdScarpa() {
        return idScarpa;
    }

    public void setIdScarpa(int idScarpa) {
        this.idScarpa = idScarpa;
    }

    
    
    public double getPrezzoAcquisto() {
        return prezzoAcquisto;
    }

    public void setPrezzoAcquisto(double prezzoAcquisto) {
        this.prezzoAcquisto = prezzoAcquisto;
    }

    
    
    public int getQuantitaScelta() {
        return quantitaScelta;
    }

    public void setQuantitaScelta(int quantitaScelta) {
        this.quantitaScelta = quantitaScelta;
    }
    
    
    //Metodo toString(Per il debug)
    public String toString() {
        return "ComposizioneOrdine [idOrdine=" + idOrdine + ", idScarpa=" + idScarpa 
                + ", prezzoAcquisto=" + prezzoAcquisto + ", quantitaScelta=" + quantitaScelta + "]";
    }
}
