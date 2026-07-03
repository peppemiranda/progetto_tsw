package model;

import java.sql.SQLException;
import java.util.Collection;

public interface OrdineDAO {
    
    // Salva il nuovo ordine nel database
    void doSave(Ordine ordine) throws SQLException;
    
    // Recupera tutto lo storico ordini di un singolo utente
    Collection<Ordine> doRetrieveByUtente(int idUtente) throws SQLException;
}