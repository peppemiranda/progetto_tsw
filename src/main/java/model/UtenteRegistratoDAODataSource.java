package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteRegistratoDAODataSource implements UtenteRegistratoDAO {
	
	public void doSave(UtenteRegistrato utente) throws SQLException {
		
        // Query SQL parametrizzata per evitare SQL Injection
        String query = "INSERT INTO Utente_Registrato (Nome, Cognome, Email, PasswordHash, Ruolo, Indirizzo_Spedizione) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getEmail());
            ps.setString(4, utente.getPasswordHash());
            ps.setString(5, utente.getRuolo());
            ps.setString(6, utente.getIndirizzoSpedizione());
            
            ps.executeUpdate();
        }
    }
}
