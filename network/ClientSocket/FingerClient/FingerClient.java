import java.net.*;
import java.io.*;

public class FingerClient
{
	public static void main(String[] args)
	{
		String hostname = "localhost";
		try
		{
			hostname = args[0];
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			hostname = "localhost";
		}

		Socket connection = null;
		try
		{
			connection = new Socket(hostname,DEFAULT_PORT);
			Writer out = new OutputStreamWriter(connection.getOutputStream(),"8859_1");
			for(int i = 1;i < args.length;i++)
			{
				out.write(args[i] + " ");
			}
			out.write("\r\n");
			out.flush();
			InputStream raw = connection.getInputStream();
			BufferedInputStream bin = new BufferedInputStream(raw);
			InputStreamReader in = new InputStreamReader(bin,"8859_1");
			int c;
			while((c = in.read()) != -1)
			{
				if((c >= 32 && c <= 127 ) || c == '\t' || c == '\r' || c == '\n')
				{
					System.out.write(c);
				}
			}

		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
		finally
		{
			try
			{
				
				if(connection != null)
				{
					connection.close();
				}

			}
			catch(IOException ex)
			{

			}
		}
	}
	public final static int DEFAULT_PORT = 79;
}
