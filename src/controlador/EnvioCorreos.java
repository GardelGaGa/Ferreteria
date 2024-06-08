package controlador;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class EnvioCorreos extends javax.swing.JFrame {

    private static String emailFrom = "topicosequipo17@gmail.com";
    private static String passwordFrom = "oxqy lkgu dxxk yshn";
    private String emailTo;
    private String subject;
    private String content;

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public void createEmail(String emailTo, String rutaArchivoPDF) {
        if (emailTo != null && !emailTo.trim().isEmpty()) {
            subject = "Ticket de venta";
            // Agregar texto personalizado junto a la fecha
            String textoPersonalizado = "Esperamos que estés disfrutando de nuestros servicios. ¡Gracias por tu apoyo continuo!";
            // Cambiar el formato de la fecha a "dd-MM-yyyy"
            content = textoPersonalizado + " " + new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            // Inicializar mProperties
            mProperties = new Properties();

            // Simple mail transfer protocol
            mProperties.put("mail.smtp.host", "smtp.gmail.com");
            mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            mProperties.setProperty("mail.smtp.starttls.enable", "true");
            mProperties.setProperty("mail.smtp.port", "587");
            mProperties.setProperty("mail.smtp.user", emailFrom);
            mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
            mProperties.setProperty("mail.smtp.auth", "true");

            mSession = Session.getDefaultInstance(mProperties);

            try {
                mCorreo = new MimeMessage(mSession);
                mCorreo.setFrom(new InternetAddress(emailFrom));
                mCorreo.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
                mCorreo.setSubject(subject);

                // Crear el cuerpo del mensaje
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(content, "ISO-8859-1", "html");

                // Crear multi-part
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                // Adjuntar el PDF
                MimeBodyPart attachPart = new MimeBodyPart();
                FileDataSource fileDataSource = new FileDataSource(rutaArchivoPDF);
                attachPart.setDataHandler(new DataHandler(fileDataSource));
                attachPart.setFileName(fileDataSource.getName());

                multipart.addBodyPart(attachPart);

                // Asignar multipart al mensaje
                mCorreo.setContent(multipart);

                // Enviar el correo
                sendEmail();

            } catch (AddressException ex) {
                Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error de dirección: " + ex.getMessage());
            } catch (MessagingException ex) {
                Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error de mensajería: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No ingresaste ninguna dirección de correo electrónico. El correo electrónico no será enviado.");
        }
    }

    public void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            JOptionPane.showMessageDialog(null, "Correo enviado correctamente");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Proveedor no encontrado: " + ex.getMessage());
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al enviar el correo: " + ex.getMessage());
        }
    }
}
