package com.gadwala.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	private final static String USERNAME = "gadwalasystem03@gmail.com";
	private final static String PASSWORD = "01142901596";
	
	public static boolean send(String email, String firstname, String msg) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USERNAME, PASSWORD);
					}
				  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			
			message.setSubject("Gadwala Account Activation");
			message.setText("Dear " + firstname + ", " + msg);

			Transport.send(message);
			System.out.println("Email sent");
			return true;
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
