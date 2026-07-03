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
		
        // Inserimento nella tabella con la chiave primaria composta
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
}
