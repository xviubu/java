import java.io.*;

public class SafeBufferedReader extends BufferedReader
{
	public SafeBufferedReader(Reader in)
	{
		this(in,1024);
	}
	public SafeBufferedReader(Reader in,int bufferSize)
	{
		super(in,bufferSize);
	}

	public String readLine() throws IOException
	{
		StringBuffer sb = new StringBuffer("");
		while(true)
		{
			int c = this.read();
			if(c == -1)
			{
				if(sb.equals(""))
					return null;
				return sb.toString();
			}
			else if(c == '\n')
			{
				if(lookingForLineFeed)
				{
					lookingForLineFeed = false;
					continue;
				}
				else
				{
					return sb.toString();
				}
			}
			else if(c == '\r')
			{
				lookingForLineFeed = true;
				return sb.toString();
			}
			else
			{
				lookingForLineFeed = false;
				sb.append((char)c);
			}
		}

	}
	private boolean lookingForLineFeed = false;
}
