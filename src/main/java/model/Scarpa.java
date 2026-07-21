package model;

public class Scarpa {
	private int idScarpa;
    private String marca;
    private String modello;
    private String terreno;
    private double prezzoAttuale;
    private int pezziMagazzino;
    private String immagine;
    
    
    public Scarpa() {
    }


    public Scarpa(int idScarpa, String marca, String modello, String terreno, 
                  double prezzoAttuale, int pezziMagazzino, String immagine) {
        this.idScarpa = idScarpa;
        this.marca = marca;
        this.modello = modello;
        this.terreno = terreno;
        this.prezzoAttuale = prezzoAttuale;
        this.pezziMagazzino = pezziMagazzino;
        this.immagine = immagine;
    }
    
    //Metodi Get e Set
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
    
    
   
    public String getImmagine() {
    	return immagine;
    }
    
    public void setImmagine(String immagine) {
    	this.immagine = immagine;
    }
    
    
    //Metodo toString (per il Debug)
    public String toString() {
        return "Scarpa [idScarpa=" + idScarpa + ", marca=" + marca + ", modello=" + modello 
                + ", terreno=" + terreno + ", prezzoAttuale=" + prezzoAttuale 
                + ", pezziMagazzino=" + pezziMagazzino + "]";
    }
}
