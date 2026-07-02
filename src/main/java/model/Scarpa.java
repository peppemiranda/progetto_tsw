package model;

public class Scarpa {
	private int idScarpa;
    private String marca;
    private String modello;
    private String terreno;
    private double prezzoAttuale;
    private int pezziMagazzino;
    
 // 2. Costruttore vuoto
    public Scarpa() {
    }

    // 3. Costruttore con parametri
    public Scarpa(int idScarpa, String marca, String modello, String terreno, 
                  double prezzoAttuale, int pezziMagazzino) {
        this.idScarpa = idScarpa;
        this.marca = marca;
        this.modello = modello;
        this.terreno = terreno;
        this.prezzoAttuale = prezzoAttuale;
        this.pezziMagazzino = pezziMagazzino;
    }
}
