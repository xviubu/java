import java.io.*;
import java.security.*;
import java.util.*;


public class ListCallBackDigestTest  implements DigestListener
{
	public static void main(String[] args)
	{
		for(int i = 0;i<args.length;i++)
		{
			File f = new File(args[i]);
			InstanceCallBackDigestTest test = new InstanceCallBackDigestTest(f);
			test.calculateDigest();
		}
	}
	public ListCallBackDigestTest(File input)
	{
		this.input = input;
	}
	public void calculateDigest()
	{
		ListCallBackDigest cb = new ListCallBackDigest(input);
		Thread t = new Thread(cb);
		t.start();
		
	}
	public  void digestCalculated(byte[] digest)
	{
		this.digest = digest;
		System.out.println(this);
	}
	public String toString()
	{
		String result = input.getName() + ": ";
		if(digest != null)
		{
			for(int i = 0;i<digest.length;i++)
			{
				result += digest[i] + " ";
			}
		}
		else
		{
			result += "digest not avaiable";
		}

		return result;
	}
	private File input;
	private byte[] digest;
}
class ListCallBackDigest implements Runnable
{
 	List listenerList = new Vector();

	public ListCallBackDigest(File input)
	{
		this.input = input;
	}
	public synchronized void addDigestListener(DigestListener l)
	{
		listenerList.add(l);
	}
	public synchronized void removeDigestListener(DigestListener l)
	{
		listenerList.remove(l);
	}

	public synchronized void sendDigest(byte[] digest)
	{
		ListIterator iterator = listenerList.listIterator();
		while(iterator.hasNext())
		{
			DigestListener dl = (DigestListener) iterator.next();
			dl.digestCalculated(digest);
		}
	}
	public void run()
	{
		try
		{
			FileInputStream fin = new FileInputStream(input);
			MessageDigest sha = MessageDigest.getInstance("SHA");
			DigestInputStream din = new DigestInputStream(fin,sha);
			int b;
			while((b = din.read()) != -1);
			din.close();
			byte[] digest = sha.digest();
			this.sendDigest(digest);
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
		catch(NoSuchAlgorithmException e)
		{
			System.err.println(e);
		}
	}


	private File input;
}

