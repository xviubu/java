import java.io.*;

public class Test
{
	public static void main(String[] args) throws IOException
	{
		rmdir(args[0]);
	}

	public static void rmdir(String fileName) throws IOException
	{
		File f = new File(fileName);
		if(f.exists() && f.isDirectory())
		{
				for(File file : f.listFiles())
				{
					if(file.isDirectory() && file.listFiles().length != 0) 
					{
						rmdir(file.getCanonicalPath());
					}
					file.delete();
				}
		}
		f.delete();
	}
}
