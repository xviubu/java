import java.io.*;
import java.security.*;

public class CallBackDigestTest
{
	public static void main(String[] args)
	{
		for(int i = 0;i<args.length;i++)
		{
			File f = new File(args[i]);
			CallBackDigest cb = new CallBackDigest(f);
			Thread t = new Thread(cb);
			t.start();
		}
	}
	public static void receiveDigest(byte[] digest,String name)
	{
		StringBuffer result = new StringBuffer(name);
		result.append(" : ");
		for(int j=0;j<digest.length;j++)
		{
			result.append(digest[j] + " ");
		}
		System.out.println(result);
	}
}
class CallBackDigest implements Runnable
{
	public CallBackDigest(File input)
	{
		this.input = input;
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
			CallBackDigestTest1.receiveDigest(digest,input.getName());
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

