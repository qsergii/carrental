package com.epam.carrental;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Class for sending email using external server
 */
public class Mail {
    private static final Session session;
    private static String from;
    private static final Logger log = LogManager.getLogger(Mail.class);

    static {
        Properties props = new Properties();
        props.put("mail.smtp.host", System.getenv("smtp_server"));
        props.put("mail.smtp.port", System.getenv("smtp_port"));
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", System.getenv("smtp_server"));
        props.put("mail.smtp.auth", true);

        String username = System.getenv("smtp_username");
        String password = System.getenv("smtp_password");

        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        from = username;
    }

    public static void send(String receiver, String subject, String msg) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        message.setSubject(subject, "UTF-8");
        message.setHeader("Content-Type", "text/plain; charset=UTF-8");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        try {
            Transport.send(message);
        } catch (MessagingException e) {
            log.error("Can't send email: " + Logging.makeDescription(e));
        }
    }

    public void addAttachment(Multipart multipart) throws MessagingException, IOException {
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(new File("path/to/file"));
        multipart.addBodyPart(attachmentBodyPart);
        String msgStyled = "This is my <b style='color:red;'>bold-red email</b> using JavaMailer";
    }
}
