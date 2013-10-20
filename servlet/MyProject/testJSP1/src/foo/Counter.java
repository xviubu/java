package foo;

public class Counter
{
	public static synchronized int getCount()
	{
		count++;
		return count;
	}
	private static int count;
}
