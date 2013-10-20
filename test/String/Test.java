import java.util.*;
public class Test
{
	public static void main(String[] args)
	{
		String s1 = new String("hello");
		String s2 = new String("hello");

		String s3 = "hello";
		String s4 = "hello";

		char[] c1 = {'h','e','l','l','o'};
		char[] c2 = {'h','e','l','l','o'};

		char[] c3 = new char[]{'h','e','l','l','o'};
		char[] c4 = new char[]{'h','e','l','l','o'};
		System.out.println(s1 == s2); //false
		System.out.println(s1.equals(s2));//true
		System.out.println(s1.compareTo(s2));//0

		System.out.println(s3 == s4);//true
		System.out.println(s3.equals(s4));//true
		System.out.println(s3.compareTo(s4));//0

		System.out.println(c1 == c2);//false
		System.out.println(c1.equals(c2));//false

		System.out.println(c3 == c4);//false
		System.out.println(c3.equals(c4));//false

		System.out.println(s1 == s3); //false
		System.out.println(s1.equals(s3)); //true

		System.out.println(c1 == c3);//false
		System.out.println(c1.equals(c3));//false

		System.out.println(s1.equals(c1));//false
		System.out.println(s1.equals(c3));//false

		System.out.println(s3.equals(c1));//false
		System.out.println(s3.equals(c3));//false

		System.out.println(Arrays.equals(c1,c2));//true
		System.out.println(Arrays.equals(c3,c4));//true
		System.out.println(Arrays.equals(c1,c3));//true

	}
}
