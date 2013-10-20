package com.springinaction.springidol;

public class Sonnet29 implements Poem
{
	public Sonnet29()
	{

	}

	public void recite()
	{
		for(int i = 0;i < LINES.length;i++)
		{
			System.out.println(LINES[i]);
		}
	}
	private static String[] LINES = {"Hello a","Hello b","Hello c","Hello d"};

}
