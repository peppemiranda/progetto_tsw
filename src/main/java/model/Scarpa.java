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
    
 // 4. Metodi Get e Set
    public int getIdScarpa() {
        return idScarpa;
    }

    public void setIdScarpa(int idScarpa) {
        this.idScarpa = idScarpa;
    }

    
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    
    
    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    
    
    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    
    
    public double getPrezzoAttuale() {
        return prezzoAttuale;
    }

    public void setPrezzoAttuale(double prezzoAttuale) {
        this.prezzoAttuale = prezzoAttuale;
    }

    
    
    public int getPezziMagazzino() {
        return pezziMagazzino;
    }

    public void setPezziMagazzino(int pezziMagazzino) {
        this.pezziMagazzino = pezziMagazzino;
    }
}
