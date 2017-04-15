package model.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptUtils {

    /**
     * Método para encriptar una cadena de texto en SHA-512 (http://stackoverflow.com/questions/33085493/hash-a-password-with-sha-512-in-java)
     * @param string Cadena de texto a encriptar
     * @return Cadena de texto encriptada
     */
    public static String sha512Crypt(String string) {
        String cryptedString = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(string.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            cryptedString = sb.toString();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return cryptedString;
    }
}