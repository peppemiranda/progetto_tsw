package model;

public class UtenteRegistrato {
    
    private int idUtente;
    private String nome;
    private String cognome;
    private String email;
    private String passwordHash;
    private String ruolo;
    private String indirizzoSpedizione;


// Costruttore vuoto 
public UtenteRegistrato() {
}

// Costruttore con parametri
public UtenteRegistrato(int idUtente, String nome, String cognome, String email, String passwordHash, String ruolo, String indirizzoSpedizione) {
    this.idUtente = idUtente;
    this.nome = nome;
    this.cognome = cognome;
    this.email = email;
    this.passwordHash = passwordHash;
    this.ruolo = ruolo;
    this.indirizzoSpedizione = indirizzoSpedizione;
 	}

}
