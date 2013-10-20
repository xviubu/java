package com.springinaction.springidol;

public class PoeticJuggler extends Juggler
{
	public PoeticJuggler(Poem poem)
	{
		super();
		this.poem = poem;
	}

	public PoeticJuggler(int beanbags , Poem poem)
	{
		super(beanbags);
		this.poem = poem;
	}

	public void perform() throws PerformanceException
	{
		super.perform();
		System.out.println("While reciting ...");
		poem.recite();
	}

	private Poem poem;
}
