package com.springinaction.springidol;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Test
{
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("com/springinaction/springidol/spring-idol.xml");


//		Stage theStage = (Stage) context.getBean("stage");

//		Auditorium audi = (Auditorium) context.getBean("auditorium");

		Performer performer = (Performer) context.getBean("kenny");
		try
		{
			performer.perform();
		}
		catch(PerformanceException ex)
		{
			ex.printStackTrace();
		}
	  	Contestant contestant= (Contestant) performer;
		contestant.receiveAward();
		Thinker t = (Thinker) context.getBean("volunteer");
		t.thinkOfSomething("猜猜我在想什么");

	}
}
