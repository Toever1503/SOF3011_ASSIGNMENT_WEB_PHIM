package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public static boolean sendMail(String emailAdress, String subject, String content) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.host", "smtp.mailtrap.io");
		props.setProperty("mail.smtp.port", "465");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("0845954f4600a5", "8252158432ddf2");
			}
		};
		
		Session session = Session.getDefaultInstance(props, auth);
		MimeMessage mine = new MimeMessage(session);
		try {
			mine.setFrom(new InternetAddress("Online999@gmail.com"));
			mine.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAdress));
			mine.setSubject(subject);
			mine.setText(content);
			Transport.send(mine);
			
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
