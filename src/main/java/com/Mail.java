package com;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.model.Order;

public class Mail {
    private final String PORT = "2525";
    private final String HOST = "smtp.mailtrap.io";
    private final String USERNAME = "50d230c87d2d72";
    private final String PASSWORD = "036233c9c6062d";
    private final String EMAIL = "admin@food.com";

    private final boolean AUTH = true;
    private final boolean STARTTLS = true;

    public void send(Order order) throws AddressException, MessagingException, IOException {
        Message msg = new MimeMessage(setSession(setProperties()));

        msg.setSentDate(new Date());
        msg.setSubject("You're order placed successfully");

        msg.setFrom(new InternetAddress(EMAIL, false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(order.getEmail()));

        msg.setContent(getContent(order), "text/html");

        Transport.send(msg);
    }

    private Object getContent(Order order) {

        Timestamp ts = order.getCreated_at();
        Date date = new Date();
        date.setTime(ts.getTime());
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy hh.mm.ss a").format(date);

        return "<html>"
                + "<body>"
                + "<h1>Hello <b>" + order.getName() + "</b></h1>"
                + "<p>Your order ID is <b>#" + order.getId() + "</b></p>"
                + "<p>Your order place at <b>" + formattedDate + "</b></p>"
                + "<p>Your order total price is " + order.getTotal() + "</p>"
                + "<p>Your order details:</p>"
                + "<table>"
                + "<tr>"
                + "<th>Product</th>"
                + "<th>Quantity</th>"
                + "<th>Price</th>" +
                order.getOrderItems().stream().map(item -> "<tr>"
                        + "<td>" + item.getProduct().getName() + "</td>"
                        + "<td>" + item.getQuantity() + "</td>"
                        + "<td>" + item.getSubTotal() + "</td>"
                        + "</tr>").reduce("", String::concat)
                + "</table>"
                + "</body>"
                + "</html>";
    }

    private Session setSession(Properties props) {
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        return session;
    }

    private Properties setProperties() {
        Properties props = new Properties();

        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.auth", AUTH);
        props.put("mail.smtp.starttls.enable", STARTTLS);

        return props;
    }
}