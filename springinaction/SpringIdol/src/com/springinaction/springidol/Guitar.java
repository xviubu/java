package com.springinaction.springidol;

import com.springinaction.springidol.qualifiers.StringedInstrument;
import org.springframework.stereotype.Component;

//@StringedInstrument
//@Component
public class Guitar implements Instrument
{
	public Guitar()
	{

	}

	public void play()
	{
		System.out.println("PIPA PIPA PIPA");
	}
}
