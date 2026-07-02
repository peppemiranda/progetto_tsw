package model;

public class ComposizioneOrdine {
	
	private int idOrdine;
    private int idScarpa;
    private double prezzoAcquisto;
    private int quantitaScelta;

    // Costruttore vuoto
    public ComposizioneOrdine() {
    }

    //Costruttore con parametri
    public ComposizioneOrdine(int idOrdine, int idScarpa, double prezzoAcquisto, int quantitaScelta) {
        this.idOrdine = idOrdine;
        this.idScarpa = idScarpa;
        this.prezzoAcquisto = prezzoAcquisto;
        this.quantitaScelta = quantitaScelta;
    }
}
