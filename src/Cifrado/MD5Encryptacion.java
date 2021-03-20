package Cifrado;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryptacion {
    
    public static String encrypt( String entrada ) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String texto_Cifrado = "";
        try{ 
            MessageDigest msd = MessageDigest.getInstance( "MD5" );
            byte[] entradaTextBytes = entrada.getBytes("UTF-8");
            byte[] salidaTxtBytes = msd.digest( entradaTextBytes );
        
            BigInteger bigInt = new BigInteger(1, salidaTxtBytes);
            texto_Cifrado = bigInt.toString(16);
        }catch( NoSuchAlgorithmException e1 ){
            
        }catch( UnsupportedEncodingException e2 ){
        }
        return texto_Cifrado;
    } 
}
