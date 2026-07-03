package model;

import java.sql.SQLException;

public interface UtenteRegistratoDAO {
    
    // Registrazione di un nuovo utente
    void doSave(UtenteRegistrato utente) throws SQLException;
    
    // Ricerca di un utente per il Login
    UtenteRegistrato doRetrieveByEmailAndPassword(String email, String password) throws SQLException;
}