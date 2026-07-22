package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ScarpaDAODataSource implements ScarpaDAO {
	
	@Override
    public Scarpa doRetrieveByKey(int idScarpa) throws SQLException {
        String query = "SELECT * FROM Scarpa WHERE ID_Scarpa = ?";
        Scarpa scarpa = null;

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, idScarpa);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    scarpa = new Scarpa();
                    scarpa.setIdScarpa(rs.getInt("ID_Scarpa"));
                    scarpa.setMarca(rs.getString("Marca"));
                    scarpa.setModello(rs.getString("Modello"));
                    scarpa.setTerreno(rs.getString("Terreno"));
                    scarpa.setPrezzoAttuale(rs.getDouble("Prezzo_Attuale"));
                    scarpa.setPezziMagazzino(rs.getInt("Pezzi_Magazzino"));
                    scarpa.setImmagine(rs.getString("Immagine"));
                }
            }
        }
        return scarpa;
    }
	
	
	@Override
    public Collection<Scarpa> doRetrieveAll() throws SQLException {
		String query = "SELECT * FROM Scarpa WHERE Cancellata = 0";
        Collection<Scarpa> catalogo = new LinkedList<>();

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Scarpa scarpa = new Scarpa();
                scarpa.setIdScarpa(rs.getInt("ID_Scarpa"));
                scarpa.setMarca(rs.getString("Marca"));
                scarpa.setModello(rs.getString("Modello"));
                scarpa.setTerreno(rs.getString("Terreno"));
                scarpa.setPrezzoAttuale(rs.getDouble("Prezzo_Attuale"));
                scarpa.setPezziMagazzino(rs.getInt("Pezzi_Magazzino"));
                scarpa.setImmagine(rs.getString("Immagine"));
                
                catalogo.add(scarpa); 
            }
        }
        return catalogo;
    }
	
    @Override
    public void doSave(Scarpa scarpa) throws SQLException {
        String query = "INSERT INTO Scarpa (Marca, Modello, Terreno, Prezzo_Attuale, Pezzi_Magazzino, Immagine) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, scarpa.getMarca());
            ps.setString(2, scarpa.getModello());
            ps.setString(3, scarpa.getTerreno() != null ? scarpa.getTerreno() : "FG"); 
            ps.setDouble(4, scarpa.getPrezzoAttuale());
            ps.setInt(5, scarpa.getPezziMagazzino() > 0 ? scarpa.getPezziMagazzino() : 10);
            ps.setString(6, scarpa.getImmagine()); 
            
            ps.executeUpdate();
        }
    }
    
    @Override
    public void doUpdate(Scarpa scarpa) throws SQLException {
    	String query = "UPDATE Scarpa SET Marca = ?, Modello = ?, Terreno = ?, Prezzo_Attuale = ?, Pezzi_Magazzino = ?, Immagine = ? WHERE ID_Scarpa = ?";

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, scarpa.getMarca());
            ps.setString(2, scarpa.getModello());
            ps.setString(3, scarpa.getTerreno() != null ? scarpa.getTerreno() : "FG");
            ps.setDouble(4, scarpa.getPrezzoAttuale());
            ps.setInt(5, scarpa.getPezziMagazzino());
            ps.setString(6, scarpa.getImmagine());
            ps.setInt(7, scarpa.getIdScarpa());
            
            ps.executeUpdate();
        }
    }
    

    @Override
    public void doDelete(int idScarpa) throws SQLException {
    	
    	String query = "UPDATE Scarpa SET Cancellata = 1 WHERE ID_Scarpa = ?";

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idScarpa);
            ps.executeUpdate();
        }
    }
    
    @Override
    public Collection<Scarpa> doRetrieveByFilter(String marca, String terreno, String q) throws SQLException {
    	
    	String query = "SELECT * FROM Scarpa WHERE Cancellata = 0";
        
        if (marca != null && !marca.isEmpty()) query += " AND FIND_IN_SET(Marca, ?) > 0";
        if (terreno != null && !terreno.isEmpty()) query += " AND FIND_IN_SET(Terreno, ?) > 0";
        if (q != null && !q.isEmpty()) query += " AND Modello LIKE ?";
        
        Collection<Scarpa> catalogo = new LinkedList<>();
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            int i = 1;
            if (marca != null && !marca.isEmpty()) ps.setString(i++, marca);
            if (terreno != null && !terreno.isEmpty()) ps.setString(i++, terreno);
            if (q != null && !q.isEmpty()) ps.setString(i++, "%" + q + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Scarpa s = new Scarpa();
                    s.setIdScarpa(rs.getInt("ID_Scarpa"));
                    s.setMarca(rs.getString("Marca"));
                    s.setModello(rs.getString("Modello"));
                    s.setTerreno(rs.getString("Terreno"));
                    s.setPrezzoAttuale(rs.getDouble("Prezzo_Attuale"));
                    s.setImmagine(rs.getString("Immagine"));
                    catalogo.add(s);
                }
            }
        }
        return catalogo;
    }

}
