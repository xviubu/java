
public class Test
{
	public static void main(String[] args)
	{
	
		String v1 = new String("hello");
		String v2 = new String("helloab");
		switch(StringUtil(v1,v2))
		{
			case 0 : 
				System.out.println("V1 == V2");
				break;
			case 1:
				System.out.println("V1 > V2");
				break;
			case -1:
				System.out.println("V1 < V2");
				break;
		}
	}

	public static int StringUtil(String v1 ,String v2)
	{
		int len = v1.length() <= v2.length() ? v1.length() : v2.length();
		for(int i = 0; i< len;i++)
		{
			if(v1.charAt(i) > v2.charAt(i))
			{
				return 1;
			}
			else if(v1.charAt(i) < v2.charAt(i))
			{
				return -1;
			}
			else
			{
				i++;
			}
		}
		if(v1.length() > v2.length())
		{
			return 1;
		}
		else if(v1.length() < v2.length())
		{
			return -1;
		}
		else
		{
			return 0;
		}

	}
}
