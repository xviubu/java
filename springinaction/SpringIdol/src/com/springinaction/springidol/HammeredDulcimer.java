package com.springinaction.springidol;

import com.springinaction.springidol.qualifiers.StringedInstrument;
import com.springinaction.springidol.qualifiers.Strummed;
import org.springframework.stereotype.Component;

//@StringedInstrument
//@Strummed
//@Component
public class HammeredDulcimer implements Instrument
{
	public HammeredDulcimer()
	{

	}

	public void play()
	{
		System.out.println("YANGQING YANGQING YANGQING");
	}
}
