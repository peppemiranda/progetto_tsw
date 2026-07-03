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
	
	@Override
    public UtenteRegistrato doRetrieveByEmailAndPassword(String email, String password) throws SQLException {
        String query = "SELECT * FROM Utente_Registrato WHERE Email = ? AND PasswordHash = ?";
        UtenteRegistrato utente = null;
        
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, email);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    utente = new UtenteRegistrato();
                    utente.setIdUtente(rs.getInt("ID_Utente"));
                    utente.setNome(rs.getString("Nome"));
                    utente.setCognome(rs.getString("Cognome"));
                    utente.setEmail(rs.getString("Email"));
                    utente.setPasswordHash(rs.getString("PasswordHash"));
                    utente.setRuolo(rs.getString("Ruolo"));
                    utente.setIndirizzoSpedizione(rs.getString("Indirizzo_Spedizione"));
                }
            }
        }
        return utente;
    }
}
