import java.net.*;

public class ProtocolTester
{
	public static void main(String[] args)
	{
		testProtocol("http://www.adc.org");

		testProtocol("https://www.amazon.com/exec/obidos/order2/");

		testProtocol("ftp://metalab.unc.edu/pub/languages/java/javafaq/");

		testProtocol("mailto:elharo@metalab.unc.edu");

		testProtocol("telnet://dibner.poly.edu/");

		testProtocol("file:///etc/passwd");

		testProtocol("gopher://gopher.anc.org.za/");
		
		testProtocol("ldap://ldap.itd.umich.edu/o=University%20of%20Michigan,c=US?postalAddress");

		testProtocol("jar:http://cafeaulait.org/books/javaio/ioexamples/javaio.jar" + "/com/macfaq/io/StreamCopier.class");

		testProtocol("nfs://utopia.poly.edu/usr/tmp");

		testProtocol("jdbc:mysql://luna.metalab.unc.edu:3306/NEWS");

		testProtocol("rmi://metalab.unc.edu/RenderEngine");

		testProtocol("doc://UsersGuide/release.html");
		testProtocol("netdoc://UsersGuide/release.html");
		testProtocol("systemresource://www.adc.org/+/index.html");
		testProtocol("verbatim:http://www.adc.org/");
	}

	private static void testProtocol(String url)
	{
		try
		{
			URL u = new URL(url);
			System.out.println(u.getProtocol() + " is supported");
		}
		catch(MalformedURLException ex)
		{
			String protocol = url.substring(0,url.indexOf(":"));
			System.out.println(protocol + " is not supported");
		}
	}
}
