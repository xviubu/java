import java.util.*;
public class PrintYesterday
{
	public static void main(String[] args)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		System.out.println(cal.getTime());
		System.out.println(new Date(new Date().getTime()-(1000*60*60*24)));

	}
}
