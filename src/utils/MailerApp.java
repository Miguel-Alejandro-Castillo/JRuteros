package utils;

import java.security.Security;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailerApp {

	public synchronized static void sendMail(String subject, String body,
			String recipients) throws Exception {
		sendMail(subject, body, "jruteros.lp@gmail.com", recipients);
	}

	public synchronized static void sendMail(String subject, String body,
			String sender, String recipients) throws Exception {

		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"jruteros.lp@gmail.com", "#JR@22ers");
					}
				});

		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(sender));
		//message.setSender(new InternetAddress(sender));
		message.setSubject(subject);
		//message.setContent(body, "text/plain");
		message.setText(body);
		
		/*
		if (recipients.indexOf(',') > 0)
			message.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse(recipients));
		else
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					recipients));				

		Transport.send(message);
		*/
		
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com", Integer.valueOf("465"),
						  "jruteros.lp@gmail.com", "#JR@22ers");

		Address[] addr = new Address[1];
		for (int i = 0; i < 1; i++)
		{
		    addr[i] = new InternetAddress(recipients);
		}

		transport.sendMessage(message, addr);

	}

	public static void main(String args[]) throws Exception {
		sendMail(
			"Email Tests",
			"Mail sent by a 63 line Java code copied from: http://forums.sun.com/thread.jspa?threadID=668779",
			"jruteros.lp@gmail.com",
			"miguelcastillo1992lp@gmail.com");
	}

}
