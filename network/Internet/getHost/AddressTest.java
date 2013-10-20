import java.net.*;

public class AddressTest
{
	public static void main(String[] args) throws UnknownHostException
	{
		InetAddress ia = InetAddress.getByName(args[0]);
		int result = getVersion(ia);
		switch(result)
		{
			case 4:
				System.out.println("Ipv4");
				break;
			case 6:
				System.out.println("Ipv6");
				break;
			case -1:
				System.out.println("Not Ip address");
				break;
		}
	}
	public static int getVersion(InetAddress ia)
	{
		byte[] address = ia.getAddress();
		if(address.length == 4)
			return 4;
		else if(address.length == 16)
			return 6;
		else
			return -1;
	}
}
