package model;

import java.sql.SQLException;
import java.util.Collection;

public interface ScarpaDAO {
    
    // Recupera una singola scarpa dato il suo ID (per la pagina dettaglio)
    Scarpa doRetrieveByKey(int idScarpa) throws SQLException;
    
    // Recupera l'intero catalogo di scarpe (per la griglia prodotti)
    Collection<Scarpa> doRetrieveAll() throws SQLException;
    
    //Metodi per l'admin
    void doSave(Scarpa scarpa) throws SQLException;
    
    void doUpdate(Scarpa scarpa) throws SQLException;
    
    void doDelete(int idScarpa) throws SQLException;
}
