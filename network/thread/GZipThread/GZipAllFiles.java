import java.io.*;
import java.util.*;
import java.util.zip.*;

public class GZipAllFiles
{
	public static void main(String[] args)
	{
		Vector pool = new Vector();
		GZipThread[] threads = new GZipThread[THREAD_COUNT];

		for(int i=0;i<threads.length;i++)
		{
			threads[i] = new GZipThread(pool);
			threads[i].start();
		}

		int totalFiles = 0;
		for(int i=0;i<args.length;i++)
		{
			File f = new File(args[i]);
			if(f.exists())
			{
				if(f.isDirectory())
				{
					File[] files = f.listFiles();
					for(int j=0;j<files.length;j++)
					{
						if(!files[j].isDirectory())
						{
							totalFiles++;
							synchronized(pool)
							{
								pool.add(0,files[j]);
								pool.notifyAll();
							}
						}
					}
				}
				else
				{
					totalFiles++;
					synchronized(pool)
					{
						pool.add(0,f);
						pool.notifyAll();
					}
				}
			}
		}

		filesToBeCompressed = totalFiles;

		for(int i=0;i<threads.length;i++)
		{
			threads[i].interrupt();
		}
	}

	public static int getNumberOfFilesToBeCompressed()
	{
		return filesToBeCompressed;
	}
	public static final int THREAD_COUNT = 4;
	private static int filesToBeCompressed = -1;
}


class GZipThread extends Thread
{
	public GZipThread(List pool)
	{
		this.pool = pool;
	}
	private static synchronized void incrementFilesComperssed()
	{
		filesCompressed++;
	}

	public void run()
	{
		while(filesCompressed != GZipAllFiles.getNumberOfFilesToBeCompressed())
		{
			File input = null;
			synchronized(pool)
			{
				while(pool.isEmpty())
				{
					if(filesCompressed == GZipAllFiles.getNumberOfFilesToBeCompressed())
					{
						System.out.println("Thread ending");
						return;
					}
					try
					{
						pool.wait();
					}
					catch(InterruptedException ex)
					{

					}
				}

				input = (File) pool.remove(pool.size()-1);
				incrementFilesComperssed();
			}

		if(!input.getName().endsWith(".gz"))
		{
			try
			{
				InputStream in = new FileInputStream(input);
				in = new BufferedInputStream(in);
				File output = new File(input.getParent(),input.getName()+".gz");
				if(!output.exists())
				{
					OutputStream out = new FileOutputStream(output);
					out = new GZIPOutputStream(out);
					out = new BufferedOutputStream(out);
					int b;
					while((b=in.read()) != -1)
						out.write(b);
					out.flush();
					out.close();
					in.close();
				}
			}
			catch(IOException e)
			{
				System.err.println(e);
			}
		}
		}
	}
	private List pool;
	private static int filesCompressed = 0;

}
