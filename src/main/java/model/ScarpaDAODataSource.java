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
                    scarpa.setImmagine(rs.getString("Immagine"));  //Lettura immagine
                }
            }
        }
        return scarpa;
    }
	
	
	@Override
    public Collection<Scarpa> doRetrieveAll() throws SQLException {
        String query = "SELECT * FROM Scarpa";
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
                scarpa.setImmagine(rs.getString("Immagine"));  //Lettura immagine
                
                catalogo.add(scarpa); // Aggiunge la scarpa alla lista del catalogo
            }
        }
        return catalogo;
    }
	
	//Salvare una nuova scarpa
    @Override
    public void doSave(Scarpa scarpa) throws SQLException {
        String query = "INSERT INTO Scarpa (Marca, Modello, Terreno, Prezzo_Attuale, Pezzi_Magazzino, Immagine) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, scarpa.getMarca());
            ps.setString(2, scarpa.getModello());
            ps.setString(3, scarpa.getTerreno() != null ? scarpa.getTerreno() : "FG"); // allback a FG se nullo
            ps.setDouble(4, scarpa.getPrezzoAttuale());
            ps.setInt(5, scarpa.getPezziMagazzino() > 0 ? scarpa.getPezziMagazzino() : 10); //Default 10 pezzi se non specificato
            ps.setString(6, scarpa.getImmagine());  //Salvataggio immagine
            
            ps.executeUpdate();
        }
    }
    
    //Aggiornare i dati di una scarpa già esistente
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
            ps.setInt(6, scarpa.getIdScarpa());
            ps.setString(7, scarpa.getImmagine());  //Aggiornamento immagine
            
            ps.executeUpdate();
        }
    }
    
    //Eliminare una scarpa dal catalogo
    @Override
    public void doDelete(int idScarpa) throws SQLException {
        String query = "DELETE FROM Scarpa WHERE ID_Scarpa = ?";

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, idScarpa);
            ps.executeUpdate();
        }
    }
    
    @Override
    public Collection<Scarpa> doRetrieveByFilter(String marca, String terreno) throws SQLException {
    	String query = "SELECT * FROM Scarpa WHERE 1=1";
        if (marca != null && !marca.isEmpty()) query += " AND Marca = ?";
        if (terreno != null && !terreno.isEmpty()) query += " AND Terreno = ?";
        
        Collection<Scarpa> catalogo = new LinkedList<>();
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            int i = 1;
            if (marca != null && !marca.isEmpty()) ps.setString(i++, marca);
            if (terreno != null && !terreno.isEmpty()) ps.setString(i++, terreno);
            
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
