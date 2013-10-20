import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class HeaderClient
{
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			System.err.println("Usage: java HeaderClient protocol://username@host/foldername");
			return;
		}
		URLName server = new URLName(args[0]);
		try
		{
			Session session = Session.getDefaultInstance(new Properties(),new MailAuthenticator(server.getUsername()));

			Folder folder = session.getFolder(server);
			if(folder == null)
			{
				System.out.println("Folder " + server.getFile() + " not found");
				System.exit(1);
			}

			folder.open(Folder.READ_ONLY);

			Message[] messages = folder.getMessages();

			for(int i = 0; i< messages.length;i++)
			{
				System.out.println("----------------- Message " + (i+1) + "--------------");
				String from = InternetAddress.toString(messages[i].getFrom());
				if(from != null)
				{
					System.out.println("From: " + from);
				}

				String replyTo =  InternetAddress.toString(messages[i].getReplyTo());
				if(replyTo != null)
				{
					System.out.println("ReplyTo: " + replyTo);
				}

				String to = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.TO));
				if(to != null)
				{
					System.out.println("To: " + to);
				}

				String cc = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.CC));
				if(cc != null)
				{
					System.out.println("Cc: " + cc);
				}

				String bcc = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.BCC));
				if(bcc != null)
				{
					System.out.println("Bcc: " + bcc);
				}

				String subject = messages[i].getSubject();
				if(subject != null)
				{
					System.out.println("Subject: " + subject);
				}

				Date sent = messages[i].getSentDate();
				if(sent != null)
				{
					System.out.println("Send: " + sent);
				}

				Date received = messages[i].getReceivedDate();
				if(received != null)
				{
					System.out.println("Received: " + received);
				}
				System.out.println();
			}

			folder.close(false);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		System.exit(0);
	}
}
