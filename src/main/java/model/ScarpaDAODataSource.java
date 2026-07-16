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
                
                catalogo.add(scarpa); // Aggiunge la scarpa alla lista del catalogo
            }
        }
        return catalogo;
    }
	
	//Salvare una nuova scarpa
    @Override
    public void doSave(Scarpa scarpa) throws SQLException {
        String query = "INSERT INTO Scarpa (Marca, Modello, Terreno, Prezzo_Attuale, Pezzi_Magazzino) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, scarpa.getMarca());
            ps.setString(2, scarpa.getModello());
            ps.setString(3, scarpa.getTerreno() != null ? scarpa.getTerreno() : "FG"); // allback a FG se nullo
            ps.setDouble(4, scarpa.getPrezzoAttuale());
            ps.setInt(5, scarpa.getPezziMagazzino() > 0 ? scarpa.getPezziMagazzino() : 10); //Default 10 pezzi se non specificato
            
            ps.executeUpdate();
        }
    }

}
