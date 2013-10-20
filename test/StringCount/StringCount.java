import java.lang.Character;
public class StringCount
{
	public static void main(String[] args)
	{
		String s = "avs123d3ed&*%%&3122dff33fd2";
		int dc = 0,ac = 0 ,oc = 0;

		for(int i = 0; i < s.length();i++)
		{
			if(Character.isDigit(s.charAt(i)))
			{
				dc++;
			}
			else if(Character.isAlphabetic(s.charAt(i)))
			{
				ac++;
			}
			else
			{
				oc++;
			}
		}

		System.out.println("numCount = " + dc);
		System.out.println("alpCount = " + ac);
		System.out.println("othCount = " + oc);
	}


}
