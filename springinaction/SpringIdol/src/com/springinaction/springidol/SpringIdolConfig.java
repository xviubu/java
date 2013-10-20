package com.springinaction.springidol;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class SpringIdolConfig 
{
	@Bean
	public Performer duke()
	{
		return new Juggler();
	}
	@Bean 
	public Performer duke15()
	{
		return new Juggler(15);
	}
	@Bean
	public Instrumentalist lishan()
	{
		Instrumentalist lishan =  new Instrumentalist();
		lishan.setSong("草 你 妈");
		return lishan;
	}
	@Bean
	public Poem sonnet29()
	{
		return new Sonnet29();
	}
	@Bean
	public PoeticJuggler poeticduke()
	{
		return new PoeticJuggler(sonnet29());
	}
}
