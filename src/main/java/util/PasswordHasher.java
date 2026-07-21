package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
	
    public static String hashPassword(String passwordInChiaro) {
    	
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            
            byte[] hashedBytes = digest.digest(passwordInChiaro.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
            	
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
        	
            // Questo scatta solo se il server su cui gira Java non ha lo SHA-512 (impossibile sui sistemi moderni)
            throw new RuntimeException("Errore fatale: Algoritmo di crittografia SHA-512 non trovato nel sistema", e);
        }
    }
}
