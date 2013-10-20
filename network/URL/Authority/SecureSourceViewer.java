import java.net.*;
import java.io.*;

public class SecureSourceViewer
{
	public static void main(String[] args)
	{
		Authenticator.setDefault(new DialogAuthenticator());
		for(int i = 0;i < args.length;i++)
		{
			try
			{
				URL u = new URL(args[i]);
				InputStream in = u.openStream();
				in = new BufferedInputStream(in);
				Reader r = new InputStreamReader(in);
				int c;
				while((c = r.read()) != -1)
				{
					System.out.print((char) c);
				}
			}
			catch(MalformedURLException ex)
			{
				System.err.println(args[0] + " is not a parseable URL");
			}
			catch(IOException ex)
			{
				System.err.println(ex);
			}
			System.out.println();
		}
		System.exit(0);
	}
}
