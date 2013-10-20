import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.*;

public class AllPartsClient
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
				Object body = messages[i].getContent();
				if(body instanceof Multipart)
				{
					processMultipart((Multipart) body);
				}
				else
				{
					processPart(messages[i]);
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

	public static void processMultipart(Multipart mp) throws MessagingException
	{
		for(int i=0;i<mp.getCount();i++)
		{
			processPart(mp.getBodyPart(i));
		}
	}

	public static void processPart(Part p)
	{
		try
		{
			String fileName = p.getFileName();
			String disposition = p.getDisposition();
			String contentType = p.getContentType();
			if(contentType.toLowerCase().startsWith("multipart/"))
			{
				processMultipart((Multipart)p.getContent());
			}
			else if(fileName == null && (Part.ATTACHMENT.equalsIgnoreCase(disposition) || !contentType.equalsIgnoreCase("text/plain")))
			{
				fileName = File.createTempFile("attachment",".txt").getName();
			}

			if(fileName == null)
			{
				p.writeTo(System.out);
			}
			else
			{
				File f = new File(fileName);
				for(int i = 1;f.exists();i++)
				{
					String newName = fileName + " "+i;
					f = new File(newName);
				}
				OutputStream out = new BufferedOutputStream(new FileOutputStream(f));
				InputStream in = new BufferedInputStream(p.getInputStream());
				int b;
				while((b = in.read())!=-1) 
				{
					out.write(b);
				}
				out.flush();
				out.close();
				in.close();
			}
		}
		catch(Exception ex)
		{
			System.err.println(ex);
			ex.printStackTrace();
		}
	}
}
