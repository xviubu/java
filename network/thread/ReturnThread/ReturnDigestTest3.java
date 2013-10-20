import java.io.*;
import java.security.*;

public class ReturnDigestTest3
{
	public static void main(String[] args)
	{
		ReturnDigest[] digests = new ReturnDigest[args.length];
		for(int i = 0;i<args.length;i++)
		{
			File f = new File(args[i]);
			digests[i] = new ReturnDigest(f);
			digests[i].start();
		}
		for(int i = 0;i<args.length;i++)
		{
			while(true)
			{
				byte[] digest = digests[i].getDigest();
				if(digest != null)
				{
					StringBuffer result = new StringBuffer(args[i]);
					result.append(" : ");
					for(int j=0;j<digest.length;j++)
					{
						result.append(digest[j] + " ");
					}
				System.out.println(result);
				break;
				}
			}
		}
	}
}
class ReturnDigest extends Thread
{
	public ReturnDigest(File input)
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
			digest = sha.digest();
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

	public byte[] getDigest()
	{
		return digest;
	}

	private File input;
	private byte[] digest;
}

