import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class AllHeaderClient
{
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			System.err.println("Usage: java AllHeaderClient protocol://username@host/foldername");
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
				Enumeration headers = messages[i].getAllHeaders();
				while(headers.hasMoreElements())
				{
					Header h =(Header) headers.nextElement();
					System.out.println(h.getName() + ": " + h.getValue());
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
