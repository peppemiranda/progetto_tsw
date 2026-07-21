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
		
        String query = "INSERT INTO Ordine (ID_Utente, Totale_Ordine) VALUES (?, ?)";

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, ordine.getIdUtente());
            ps.setDouble(2, ordine.getTotaleOrdine());
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    ordine.setIdOrdine(rs.getInt(1));
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
                ordine.setDataAcquisto(rs.getDate("Data_Acquisto"));
                
                ordini.add(ordine);
            }
        }
        return ordini;
    }

}
