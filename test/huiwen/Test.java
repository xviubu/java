public class Test
{
	public static void main(String[] args)
	{
		String s = System.console().readLine();

		StringBuffer source = new StringBuffer(s);
		source.reverse();
		String rs = new String(source);
		if(s.equals(rs))
		{
			System.out.println("yes");
		}
		else
		{
			System.out.println("no");
		}

	}
}
