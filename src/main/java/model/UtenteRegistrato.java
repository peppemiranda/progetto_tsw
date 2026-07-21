package model;

public class UtenteRegistrato {
    
    private int idUtente;
    private String nome;
    private String cognome;
    private String email;
    private String passwordHash;
    private String ruolo;
    private String indirizzoSpedizione;



    public UtenteRegistrato() {
    }


	public UtenteRegistrato(int idUtente, String nome, String cognome, String email, String passwordHash, String ruolo, 
			String indirizzoSpedizione) {
    this.idUtente = idUtente;
    this.nome = nome;
    this.cognome = cognome;
    this.email = email;
    this.passwordHash = passwordHash;
    this.ruolo = ruolo;
    this.indirizzoSpedizione = indirizzoSpedizione;
 		}


	//Metodi get e set

	public int getIdUtente() {
		return idUtente;
		}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
		}
	
	

	public String getNome() {
		return nome;
		}

	public void setNome(String nome) {
		this.nome = nome;
		}

	
	
	public String getCognome() {
		return cognome;
		}

	public void setCognome(String cognome) {
		this.cognome = cognome;
		}

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		}

	
	
	public String getPasswordHash() {
		return passwordHash;
		}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
		}

	
	
	public String getRuolo() {
		return ruolo;
		}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
		}

	
	
	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
		}

	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
		}
	
	//Metodo toString(Utile per il debug in console)
	public String toString() {
        return "UtenteRegistrato [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome 
                + ", email=" + email + ", passwordHash=" + passwordHash + ", ruolo=" + ruolo 
                + ", indirizzoSpedizione=" + indirizzoSpedizione + "]";
    }
}
