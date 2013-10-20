import java.net.*;
import java.io.*;

public class ClientTester
{
	public static void main(String[] args)
	{
		int port;
		try
		{
			port = Integer.parseInt(args[0]);
		}
		catch(Exception ex)
		{
			port = 0;
		}

		try
		{
			ServerSocket server = new ServerSocket(port,1);	
			System.out.println("Listening for connections on port " + server.getLocalPort());

			while(true)
			{
				Socket connection = server.accept();
				try
				{
					System.out.println("Connection established with " + connection);
					Thread input = new InputThread(connection.getInputStream());
					input.start();
					Thread output = new OutputThread(connection.getOutputStream());
					output.start();

					try
					{
						input.join();
						output.join();
					}
					catch(InterruptedException ex)
					{

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
							connection.close();
					}
					catch(IOException ex)
					{

					}
				}
			}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}

class InputThread extends Thread
{
	public InputThread(InputStream in)
	{
		this.in = in;
	}

	public void run()
	{
		try
		{
			while(true)
			{
				int i = in.read();
				if(i == -1) break;
				System.out.write(i);
			}
		}
		catch(SocketException ex)
		{

		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}

		try
		{
			in.close();
		}
		catch(IOException ex)
		{

		}
	}
	private	InputStream in;
}

class OutputThread extends Thread
{
	public OutputThread(OutputStream out )
	{
		this.out = new OutputStreamWriter(out);
	}

	public void run()
	{
		String line;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			while(true)
			{
				line = in.readLine();
				if(line.equals("."))
					break;
				out.write(line + "\r\n");
				out.flush();
			}
		}
		catch(IOException ex)
		{

		}
		try
		{
			out.close();
		}
		catch(IOException ex)
		{

		}
	}
	private Writer out;
}
