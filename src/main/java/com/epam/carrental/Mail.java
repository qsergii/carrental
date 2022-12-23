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
    private static final String username = System.getenv("smtp_username");
    private static final String password = System.getenv("smtp_password");

    static {
        Properties props = new Properties();
        props.put("mail.smtp.host", System.getenv("smtp_server"));
        props.put("mail.smtp.port", System.getenv("smtp_port"));
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", System.getenv("smtp_server"));
        props.put("mail.smtp.auth", true);

        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public static void send(String receiver, String subject, String msg) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
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
