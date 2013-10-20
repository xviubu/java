package com.springinaction.springidol;

import java.util.Map;

public class OneManBandx implements Performer
{
	public OneManBandx()
	{

	}
	public void perform() throws PerformanceException
	{
		for(String key : instruments.keySet())
		{
			System.out.print(key + " :");
			Instrument instrument = instruments.get(key);
			instrument.play();
		}
	}
	public void setInstruments(Map<String,Instrument> instruments)
	{
		this.instruments = instruments;
	}

	private Map<String,Instrument> instruments;
}
