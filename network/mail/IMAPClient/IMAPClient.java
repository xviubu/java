import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.*;

public class IMAPClient
{
	public static void main(String[] args)
	{
		Properties props = new Properties();
		
		String host = "imap.gmail.com";
		String username = "xviubu";
		String password = "910801910801";
		String provider = "imaps";

		try
		{
			Session session = Session.getDefaultInstance(props,null);
			Store store = session.getStore(provider);
			store.connect(host,username,password);

			Folder inbox = store.getFolder("INBOX");
			if(inbox == null)
			{
				System.out.println("NO INBOX");
				System.exit(1);
			}
			inbox.open(Folder.READ_ONLY);

			Message[] messages = inbox.getMessages();
			for(int i = 0;i < messages.length;i++)
			{
				System.out.println("---------------- Message " + (i+1) + "---------------");
				messages[i].writeTo(System.out);
			}

			inbox.close(false);
			store.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
