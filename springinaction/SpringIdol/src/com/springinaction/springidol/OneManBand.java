package com.springinaction.springidol;
import java.util.Collection;

public class OneManBand implements Performer
{
	public OneManBand()
	{

	}
	public void perform() throws PerformanceException
	{
		for(Instrument instrument : instruments)
		{
			instrument.play();
		}
	}

	public void setInstruments(Collection<Instrument> instruments)
	{
		this.instruments = instruments;
	}
	private Collection<Instrument> instruments;
}
