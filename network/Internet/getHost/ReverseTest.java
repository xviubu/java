import java.net.*;

public class ReverseTest
{
	public static void main(String[] args)
	{
		try
		{
			InetAddress ia = InetAddress.getByName("127.0.0.1");
			System.out.println(ia.getHostName());
		}
		catch(UnknownHostException ex)
		{
			System.out.println(ex);
		}
	}
}
