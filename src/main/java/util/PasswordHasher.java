package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
	
	//Metodo statico: possiamo usarlo ovunque senza dover creare un oggetto PasswordHasher
    public static String hashPassword(String passwordInChiaro) {
    	
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");	//Indichiamo a Java che vogliamo usare l'algoritmo SHA-512
            
            //Trasformiamo la password in byte (hash)
            byte[] hashedBytes = digest.digest(passwordInChiaro.getBytes(StandardCharsets.UTF_8));
            
            //Convertiamo i byte in una stringa esadecimale leggibile (lunga 128 caratteri)
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
            	
                //L'operazione 0xff & b risolve il problema dei numeri negativi nei byte
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); 	//Aggiunge uno zero iniziale se necessario
                }
                hexString.append(hex);
            }
            
            return hexString.toString(); 	//Restituisce l'hash finale illeggibile

        } catch (NoSuchAlgorithmException e) {
        	
            // Questo scatta solo se il server su cui gira Java non ha lo SHA-512 (impossibile sui sistemi moderni)
            throw new RuntimeException("Errore fatale: Algoritmo di crittografia SHA-512 non trovato nel sistema", e);
        }
    }
}
