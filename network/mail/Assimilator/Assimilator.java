import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Assimilator
{
	public static void main(String[] args)
	{
		try
		{
			Properties props = new Properties();
			props.put("mail.smtp.host","localhost");

			Session mailConnection = Session.getInstance(props,null);
			Message msg  = new MimeMessage(mailConnection);

			Address from= new InternetAddress("951172900@qq.com");
			Address to = new InternetAddress("xviubu@gmail.com");

			msg.setContent("This is the java mail test !","text/plain");
			msg.setFrom(from);
			msg.setRecipient(Message.RecipientType.TO,to);
			msg.setSubject("You must shock !");

			Transport.send(msg);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
