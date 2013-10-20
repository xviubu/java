import java.net.*;
import java.io.*;

public class ContentGetter
{
	public static void main(String[] args)
	{
		if(args.length  > 0 )
		{
			try
			{
				URL u = new URL(args[0]);
				try
				{
					Object o = u.getContent();
					System.out.println("I got a " + o.getClass().getName());
				}
				catch(IOException ex)
				{
					System.err.println(ex);
				}
			}
			catch(MalformedURLException ex)
			{
				System.err.println(args[0] + " is not a parseable URL");
			}
		}
	}
}
