import java.io.*;
import java.util.*;
import java.net.*;
import com.xviubu.SafeBufferedReader;

public class PooledWeblog
{
	public static void main(String[] args)
	{
		try
		{
			PooledWeblog tw = new PooledWeblog(new FileInputStream(args[0]),System.out,100);
			tw.processLogFile();
		}
		catch(FileNotFoundException ex)
		{
			System.err.println("Usage: java PooledWeblog logfilename");
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			System.err.println("Usage: java PooledWeblog logfilename");
		}
		catch(Exception ex)
		{
			System.err.println(ex);
			ex.printStackTrace();
		}
	}
	public PooledWeblog(InputStream in,OutputStream out,int numberOfThreads)
	{
		this.in = new SafeBufferedReader(new InputStreamReader(in));	
		this.out = new BufferedWriter(new OutputStreamWriter(out));
		this.numberOfThreads = numberOfThreads;
	}

	public boolean isFinished()
	{
		return this.finished;
	}

	public int getNumberOfThreads()
	{
		return numberOfThreads;
	}
	
	public void processLogFile()
	{
		for(int i = 0;i < numberOfThreads;i++)
		{
			Thread t = new LookupThread(entries,this);
			t.start();
		}
		try
		{
			String entry = in.readLine();
			while(entry != null)
			{
				if(entries.size() > numberOfThreads)
				{
					try
					{
						Thread.sleep((long) (1000.0/numberOfThreads));
					}
					catch(InterruptedException ex)
					{

					}
					continue;
				}

				synchronized(entries)
				{
					entries.add(0,entry);
					entries.notifyAll();
				}

				entry = in.readLine();
				Thread.yield();
			}
		}
		catch(IOException e)
		{
			System.out.println("Exception: " + e);
		}
		this.finished = true;
		synchronized(entries)
		{
			entries.notifyAll();
		}
	}

	public void log(String entry) throws IOException
	{
		out.write(entry + System.getProperty("line.separator","\r\n"));
		out.flush();
	}




	private BufferedReader in;
	private BufferedWriter out;
	private int numberOfThreads;
	private List entries = Collections.synchronizedList(new LinkedList());
	private boolean finished = false;
	private int test = 0;
}

class LookupThread extends Thread
{
	public LookupThread(List entries,PooledWeblog log)
	{
		this.entries = entries;
		this.log = log;
	}
	
	public void run()
	{
		String entry;
		while(true)
		{
			synchronized(entries)
			{
				while(entries.size() == 0)
				{
					if(log.isFinished())
						return;
					try
					{
						entries.wait();
					}
					catch(InterruptedException ex)
					{

					}
				}
				entry = (String) entries.remove(entries.size() -1);
			}
			int index = entry.indexOf(' ',0);
			String remoteHost = entry.substring(0,index);
			String theRest = entry.substring(index,entry.length());

			try
			{
				remoteHost = InetAddress.getByName(remoteHost).getHostName();
			}
			catch(Exception ex)
			{

			}

			try
			{
				log.log(remoteHost + theRest);
			}
			catch(IOException ex)
			{

			}
			this.yield();
		}
	}

	private List entries;
	PooledWeblog log;
}
