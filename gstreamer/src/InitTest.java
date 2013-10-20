import org.gstreamer.*;

public class InitTest
{
	public static void main(String[] args)
	{
		args = Gst.init("InitTest",args);
		Version version = Gst.getVersion();
		String nanoStr = "";
		if(version.getNano() == 1)
		{
			nanoStr = "(CVS)";
		}
		else if(version.getNano() >= 2)
		{
			nanoStr = " (Pre-release)";
		}

		System.out.printf("Gstreamer version %d.%d.%d%s initialized!",version.getMajor(),version.getMinor(),version.getMicro(),nanoStr);
		Gst.deinit();
	}
}
