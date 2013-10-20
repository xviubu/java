import java.rmi.*;
import java.net.*;

public class FibonacciServer
{
	public static void main(String[] args)
	{
		try
		{
			FibonacciImpl f = new FibonacciImpl();
			Naming.rebind("fibonacci",f);
			System.out.println("Fibonacci Server ready.");
		}
		catch(RemoteException ex)
		{
			System.out.println("Exception in FibonacciImpl.main: " + ex);
		}
		catch(MalformedURLException ex)
		{
			System.out.println("MalformedURLException " + ex);
		}
	}
}
