import java.io.*;
import java.security.*;

public class InstanceCallBackDigestTest
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
	public InstanceCallBackDigestTest(File input)
	{
		this.input = input;
	}
	public void calculateDigest()
	{
		InstanceCallBackDigest cb = new InstanceCallBackDigest(input,this);
		Thread t = new Thread(cb);
		t.start();
		
	}
	public  void receiveDigest(byte[] digest)
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
class InstanceCallBackDigest implements Runnable
{
	public InstanceCallBackDigest(File input,InstanceCallBackDigestTest callback)
	{
		this.input = input;
		this.callback = callback;
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
			callback.receiveDigest(digest);
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
	private InstanceCallBackDigestTest callback;
}

