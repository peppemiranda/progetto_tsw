package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 		//Per recuperare l'ID generato
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class OrdineDAODataSource implements OrdineDAO {
	
	@Override
    public void doSave(Ordine ordine) throws SQLException {
		
        // Non inseriamo Data_Acquisto perché MySQL lo fa in automatico con CURRENT_TIMESTAMP
        String query = "INSERT INTO Ordine (ID_Utente, Totale_Ordine) VALUES (?, ?)";

        // Usiamo Statement.RETURN_GENERATED_KEYS per farci restituire l'ID appena creato
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, ordine.getIdUtente());
            ps.setDouble(2, ordine.getTotaleOrdine());
            
            ps.executeUpdate();
            
            // Recuperiamo l'ID generato automaticamente da MySQL e lo mettiamo nel nostro oggetto Java
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    ordine.setIdOrdine(rs.getInt(1)); // 1 è la prima (e unica) colonna restituita, ovvero l'ID
                }
            }
        }
    }
	
	@Override
    public Collection<Ordine> doRetrieveByUtente(int idUtente) throws SQLException {
        String query = "SELECT * FROM Ordine WHERE ID_Utente = ?";
        Collection<Ordine> storicoOrdini = new LinkedList<>();

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, idUtente);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ordine ordine = new Ordine();
                    ordine.setIdOrdine(rs.getInt("ID_Ordine"));
                    ordine.setIdUtente(rs.getInt("ID_Utente"));
                    ordine.setDataAcquisto(rs.getDate("Data_Acquisto"));
                    ordine.setTotaleOrdine(rs.getDouble("Totale_Ordine"));
                    
                    storicoOrdini.add(ordine);
                }
            }
        }
        return storicoOrdini;
    }
	
	// Metodo aggiunto per estrarre tutti gli ordini dal database
    public Collection<Ordine> doRetrieveAll() throws SQLException {
        String query = "SELECT * FROM Ordine";
        Collection<Ordine> ordini = new ArrayList<>();
        
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                model.Ordine ordine = new model.Ordine();
                ordine.setIdOrdine(rs.getInt("ID_Ordine"));
                ordine.setIdUtente(rs.getInt("ID_Utente"));
                ordine.setTotaleOrdine(rs.getDouble("Totale_Ordine"));
                ordini.add(ordine);
            }
        }
        return ordini;
    }

}
