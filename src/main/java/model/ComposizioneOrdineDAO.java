package model;

import java.sql.SQLException;
import java.util.Collection;

public interface ComposizioneOrdineDAO {
    
    // Salva una singola riga dell'ordine (una scarpa acquistata)
    void doSave(ComposizioneOrdine composizione) throws SQLException;
    
    // Recupera tutte le scarpe all'interno di un ordine specifico (per vedere i dettagli nello storico)
    Collection<ComposizioneOrdine> doRetrieveByOrdine(int idOrdine) throws SQLException;
}