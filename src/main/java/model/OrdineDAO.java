package model;

import java.sql.SQLException;

import java.util.Collection;

public interface OrdineDAO {
    
    // Salva il nuovo ordine nel database
    void doSave(Ordine ordine) throws SQLException;
    
    // Recupera tutto lo storico ordini di un singolo utente
    Collection<Ordine> doRetrieveByUtente(int idUtente) throws SQLException;
    
    // Per leggere i nuovi ordini
    public Collection<Ordine> doRetrieveAll() throws SQLException;
    
    // Per far filtrare all'Admin gli ordini per data e per cliente
    Collection<Ordine> doRetrieveByFiltri(String data, String idCliente) throws SQLException;
}