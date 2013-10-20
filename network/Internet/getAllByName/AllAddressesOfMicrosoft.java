import java.net.*;

public class AllAddressesOfMicrosoft
{
	public static void main(String[] args)
	{
		try
		{
			InetAddress[] addresses = InetAddress.getAllByName("www.microsoft.com");
			for(int i=0;i<addresses.length;i++)
			{
				System.out.println(addresses[i]);
			}
		}
		catch(UnknownHostException ex)
		{
			System.out.println("Cound not find www.microsoft.com");
		}
	}
}
