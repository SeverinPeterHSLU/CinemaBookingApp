package ch.hslu.sweng.group3;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendReservationMail {

    public SendReservationMail(String recipient, int reservationNumber, int reservedSeats){

        final String from = "SwengHsluTeam3@gmail.com"; // from address. As this is using Gmail SMTP.
        final String password = "Knoblauch4ever!"; // password for from mail address.

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "true");


        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            Properties properties;
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);

            }
        });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
                message.setSubject("Reservation Confirmation");

                String msg = "Dear Customer, we have created a reservation. <br /><br />" +
                        "Your reservation ID is: " + reservationNumber + "<br />" +
                        "The number of seats that you have reserved is: " + reservedSeats +"<br /><br />" +
                        "Best regards <br />" +
                        "Your local movie theater! ";

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(msg, "text/html");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);

                message.setContent(multipart);
                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

