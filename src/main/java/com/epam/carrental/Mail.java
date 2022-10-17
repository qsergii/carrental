package com.epam.carrental;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Class for sending email using external server
 */
public class Mail {

    private static final Session session;

    static {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", AppSettings.PROPERTIES.getProperty("mail.server"));
        prop.put("mail.smtp.port", AppSettings.PROPERTIES.getProperty("mail.port"));
        prop.put("mail.smtp.ssl.trust", AppSettings.PROPERTIES.getProperty("mail.server"));

        session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private static final String username = AppSettings.PROPERTIES.getProperty("mail.username");
    private static final String password = AppSettings.PROPERTIES.getProperty("mail.password");

    public static void send(String receiver, String subject, String msg) throws MessagingException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("renar@qsergey.tk"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

    }

    public void addAttachment(Multipart multipart) throws MessagingException, IOException {

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(new File("path/to/file"));
        multipart.addBodyPart(attachmentBodyPart);
        String msgStyled = "This is my <b style='color:red;'>bold-red email</b> using JavaMailer";
    }
}
