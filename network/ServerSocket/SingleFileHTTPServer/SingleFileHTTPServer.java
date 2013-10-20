import java.net.*;
import java.io.*;
import java.util.*;

public class SingleFileHTTPServer extends Thread
{
	public static void main(String[] args)
	{
		try
		{
			/*设置内容类型*/
			String contentType = "text/plain";
			if(args[0].endsWith(".html") || args[0].endsWith(".htm"))
			{
				contentType = "text/html";
			}

			/*读取一个文件存入data数组中*/
			InputStream in = new FileInputStream(args[0]);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int b;
			while(( b = in.read()) != -1)
			{
				out.write(b);
			}
			byte[] data = out.toByteArray();
			/*设置端口*/
			int port;
			try
			{
				port = Integer.parseInt(args[1]);
				if(port < 1 || port > 65535)
				{
					port = 80;
				}
			}
			catch(Exception ex)
			{
				port = 80;
			}
			/*设置编码方式*/
			String encoding = "ASCII";
			if(args.length > 2) 
			{
				encoding = args[2];
			}

			Thread t = new SingleFileHTTPServer(data,encoding,contentType,port);
			t.start();


		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			System.out.println("Usage java SingleFileHTTPServer filename port encoding");
		}
		catch(Exception ex)
		{
			System.err.println(ex);
		}
	}
	public SingleFileHTTPServer(String data,String encoding,String MIMEType,int port) throws UnsupportedEncodingException
	{
		this(data.getBytes(encoding),encoding,MIMEType,port);
	}
	public SingleFileHTTPServer(byte[] data,String encoding,String MIMEType,int port) throws UnsupportedEncodingException
	{
		this.content = data;
		this.port = port;
		String header = "HTTP/1.0 200 OK\r\n"
					  + "Server: OneFile 1.0\r\n"
					  + "Content-length: " + this.content.length + "\r\n"
					  + "Content-type: " + MIMEType + "\r\n\r\n";
		this.header = header.getBytes("ASCII");;

	}

	public void run()
	{
		try
		{
			ServerSocket server = new ServerSocket(this.port);
			System.out.println("Accepting connections on port " + server.getLocalPort());
			System.out.println("Data to be sent: ");
			System.out.write(this.content);

			while(true)
			{
				Socket connection = null;
				try
				{
					connection = server.accept();
					OutputStream out = new BufferedOutputStream(connection.getOutputStream());
					InputStream in = new BufferedInputStream(connection.getInputStream());

					StringBuffer request = new StringBuffer(80);
					while(true)
					{
						int c = in.read();
						if(c == '\r' || c == '\n' || c == -1)
							break;
						request.append((char) c);
					}
					if(request.toString().indexOf("HTTP/") != -1)
					{
						out.write(this.header);
					}
					out.write(this.content);
					out.flush();
				}
				catch(IOException ex)
				{

				}
				finally
				{
					if(connection != null)
						connection.close();
				}
			}
		}
		catch(IOException ex)
		{
			System.err.println("Could not start server. Port Occupied");
		}
	}
	private byte[] content;	
	private byte[] header;
	private int port = 80;
}
