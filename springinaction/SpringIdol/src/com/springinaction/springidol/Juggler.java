package com.springinaction.springidol;

public class Juggler implements Performer
{
	public Juggler()
	{

	}
	public Juggler(int beanBags)
	{
		this.beanBags = beanBags;
	}
	public void perform() throws PerformanceException
	{
		System.out.println("JUGGLER " + beanBags + " BEANBAGS");
	}
	private int beanBags = 3;
}
