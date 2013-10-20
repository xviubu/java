package com.springinaction.springidol;
import org.aspectj.lang.annotation.*;

@Aspect
public class Magician implements MindReader
{
	private String thoughts;
	@Pointcut("execution(*  com.springinaction.springidol.Thinker.thinkOfSomething(String)) && args(thoughts)")
		public void thinking(String thoughts)
		{

		}

	@Before("thinking(thoughts)")
	public void interceptThoughts(String thoughts)
	{
		System.out.println("Intercepting volunteer's thoughts");
		this.thoughts = thoughts;
		System.out.println(thoughts);
	}

	public String getThoughts()
	{
		return thoughts;
	}
}
