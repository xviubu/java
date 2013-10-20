import java.net.*;

public class Myaddress
{
	public static void main(String[] args)
	{
		try
		{
			InetAddress me = InetAddress.getLocalHost();
			String dottedQuad = me.getHostAddress();
			System.out.println("My address is " + dottedQuad);
		}
		catch(UnknownHostException ex)
		{
			System.out.println("I'm sorry. I don't know my own address.");
		}
	}
}
