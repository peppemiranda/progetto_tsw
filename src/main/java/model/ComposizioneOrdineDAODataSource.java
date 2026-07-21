package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ComposizioneOrdineDAODataSource implements ComposizioneOrdineDAO {
	
	@Override
    public void doSave(ComposizioneOrdine composizione) throws SQLException {
		
        String query = "INSERT INTO Composizione_Ordine (ID_Ordine, ID_Scarpa, Prezzo_Acquisto, Quantita_Scelta) VALUES (?, ?, ?, ?)";
        
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, composizione.getIdOrdine());
            ps.setInt(2, composizione.getIdScarpa());
            ps.setDouble(3, composizione.getPrezzoAcquisto());
            ps.setInt(4, composizione.getQuantitaScelta());
            
            ps.executeUpdate();
        }
    }
	
	
	@Override
    public Collection<ComposizioneOrdine> doRetrieveByOrdine(int idOrdine) throws SQLException {
        String query = "SELECT * FROM Composizione_Ordine WHERE ID_Ordine = ?";
        Collection<ComposizioneOrdine> composizioni = new LinkedList<>();

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, idOrdine);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ComposizioneOrdine comp = new ComposizioneOrdine();
                    comp.setIdOrdine(rs.getInt("ID_Ordine"));
                    comp.setIdScarpa(rs.getInt("ID_Scarpa"));
                    comp.setPrezzoAcquisto(rs.getDouble("Prezzo_Acquisto"));
                    comp.setQuantitaScelta(rs.getInt("Quantita_Scelta"));
                    
                    composizioni.add(comp);
                }
            }
        }
        return composizioni;
    }
}
