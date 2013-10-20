package com.springinaction.springidol;

public class Volunteer implements Thinker
{
	public void thinkOfSomething(String thoughts)
	{
		this.thoughts = thoughts;
	}

	public String getThoughts()
	{
		return thoughts;
	}
	private String thoughts;
}
