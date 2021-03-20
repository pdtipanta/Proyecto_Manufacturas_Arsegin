/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
/**
 *
 * @author David
 */
public class Email {
    final String username = "dpoliu@gmail.com";
    final String password = "olnezyqelrghrygl";
    
    public void envio_Correo( String correo, String codigo ){
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication( username, password );
                    }
                });
        try {
 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress( username ));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse( correo ) );
            message.setSubject( "Recuperacion de clave de usuario" );
            message.setText( "SISTEMA MANUFACTURAS ARSEGIN\n Codigo de recuperacion: " + codigo );
            Transport.send(message);
 
        } catch (Exception  e) {
            JOptionPane.showMessageDialog(null, "El correo electronico registrado es inaccesible", "Correo electronico", JOptionPane.WARNING_MESSAGE);
        }
    }
}
