import java.net.*;
import java.io.*;

public class HostLookup
{
	public static void main(String[] args)
	{
		/* 处理命令行参数*/
		if(args.length > 0)
		{
			for(int i=0;i<args.length;i++)
			{
				System.out.println(lookup(args[i]));
			}
		}
		/*使用交互式*/
		else
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter names and IP address. Enter \"exit\" to quit.");
			try
			{
				while(true)
				{
					String host = in.readLine();
					if(host.equalsIgnoreCase("exit") || host.equalsIgnoreCase("quit"))
					{
						break;
					}
					System.out.println(lookup(host));
				}
			}
			catch(IOException ex)
			{
				System.err.println(ex);
			}
		}
	}

	private static String lookup(String host)
	{
		InetAddress node;
		try
		{
			node = InetAddress.getByName(host);
		}
		catch(UnknownHostException ex)
		{
			return "Cannot find host " + host;
		}

		if(isHostname(host))
		{
			return node.getHostAddress(); //获取主机对应的IP地址
		}
		else
		{
			return node.getHostName();  //获取IP对应的主机名
		}
	}

	private static boolean isHostname(String host)
	{
		if(host.indexOf(':') != -1)
			return false;
		char[] ca = host.toCharArray();

		for(int i = 0; i < ca.length ;i++)
		{
			if(!Character.isDigit(ca[i]))
			{
				if(ca[i] != '.')
				{
					return true;
				}
			}
		}
		return false;
	}
}
