import java.net.*;
import java.io.*;

public class RandomPort
{
	public static void main(String[] args)
	{
		try
		{
			ServerSocket server = new ServerSocket(0);
			System.out.println("This server runs on port " + server.getLocalPort());
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
	}
}
