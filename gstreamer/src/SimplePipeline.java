import org.gstreamer.*;


public class SimplePipeline
{
	public static void main(String[] args)
	{
		args = Gst.init("SimplePipeline",args);
		Pipeline pipe = new Pipeline("SimplePipeline");
		Element src = ElementFactory.make("fakesrc","Source");
		Element sink = ElementFactory.make("fakesink","Destination");
		pipe.addMany(src,sink);
		src.link(sink);
		pipe.setState(State.PLAYING);
		Gst.main();
		pipe.setState(State.NULL);
	}
}

