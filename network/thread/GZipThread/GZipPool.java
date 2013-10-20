import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.util.concurrent.*;

public class GZipPool
{
	public static void main(String[] args)
	{
		ExecutorService pool = Executors.newCachedThreadPool();		
		
		GZipThread[] threads = new GZipThread[100];
		for(int i=0;i<threads.length;i++)
		{
			if(args[i] != null)
			{
				threads[i] = new GZipThread(new File(args[i]),pool);
				Future<?> result = pool.submit(threads[i]);
			}
			else
			{
				break;
			}
		}

		pool.shutdown();
	}
}

class GZipThread implements Runnable
{
	public GZipThread(File inFile,ExecutorService pool)
	{
		this.pool = pool;	
		this.inFile = inFile;
	}

	public void run()
	{
		try
		{
			FileInputStream fin = new FileInputStream(inFile);
			BufferedInputStream bin = new BufferedInputStream(fin);

			File outFile = new File(inFile.getName() + ".gz");
			if(!outFile.exists())
			{
				FileOutputStream fout = new FileOutputStream(outFile);
				GZIPOutputStream gout = new GZIPOutputStream(fout);
				BufferedOutputStream bout = new BufferedOutputStream(gout);

				int b;
				while((b = bin.read()) != -1)
					bout.write(b);
				bout.flush();
				bout.close();
				bin.close();
			}

		}
		catch(IOException e)
		{
			System.err.println(e);
		}
	}

	private ExecutorService pool;
	private File inFile;
}

