
DROP DATABASE IF EXISTS pianetacalcio;

-- Creazione del database e selezione
CREATE DATABASE IF NOT EXISTS pianetacalcio;
USE pianetacalcio;

-- 1. Tabella UTENTE REGISTRATO
CREATE TABLE Utente_Registrato (
    ID_Utente INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(50) NOT NULL,
    Cognome VARCHAR(50) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE, -- Non possono esistere due utenti con la stessa email
    PasswordHash VARCHAR(255) NOT NULL, -- Per evitare gli attacchi hacker, il server Java lo trasformerà in una stringa crittografata
    Ruolo VARCHAR(20) NOT NULL, -- Qui scriveremo 'Admin' o 'User'
    Indirizzo_Spedizione VARCHAR(255) NOT NULL
);

-- 2. Tabella SCARPA (Aggiornata con colonna Immagine)
CREATE TABLE Scarpa (
    ID_Scarpa INT AUTO_INCREMENT PRIMARY KEY,
    Marca VARCHAR(50) NOT NULL,
    Modello VARCHAR(100) NOT NULL,
    Terreno VARCHAR(10) NOT NULL, -- Es: 'FG', 'SG', 'TF'
    Prezzo_Attuale DECIMAL(10,2) NOT NULL, -- Significa che il numero può avere un massimo di 10 cifre totali, di cui esattamente 2 dopo la virgola(i centesimi)
    Pezzi_Magazzino INT NOT NULL,
    Immagine VARCHAR(255) -- Nuova colonna per gestire le foto
);

-- 3. Tabella ORDINE
CREATE TABLE Ordine (
    ID_Ordine INT AUTO_INCREMENT PRIMARY KEY,
    ID_Utente INT NOT NULL,
    Data_Acquisto DATETIME DEFAULT CURRENT_TIMESTAMP,
    Totale_Ordine DECIMAL(10,2) NOT NULL,	-- Totale_Ordine è calcolato, ma in un e-commerce si salva "fisicamente" per velocità di lettura nello storico
    FOREIGN KEY (ID_Utente) REFERENCES Utente_Registrato(ID_Utente) 
    ON DELETE CASCADE ON UPDATE CASCADE		-- Se eliminiamo dal database un utente, eliminiamo automaticamente anche tutti i suoi ordini
);

-- 4. Tabella COMPOSIZIONE_ORDINE
CREATE TABLE Composizione_Ordine (
    ID_Ordine INT NOT NULL,
    ID_Scarpa INT NOT NULL,
    Prezzo_Acquisto DECIMAL(10,2) NOT NULL,
    Quantita_Scelta INT NOT NULL,
    PRIMARY KEY (ID_Ordine, ID_Scarpa), -- Chiave composta
    FOREIGN KEY (ID_Ordine) REFERENCES Ordine(ID_Ordine) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ID_Scarpa) REFERENCES Scarpa(ID_Scarpa) ON DELETE CASCADE ON UPDATE CASCADE
);