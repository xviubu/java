package com.xviubu.spitter.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.xviubu.spitter.model.*;
public class Test
{
	public static void main(String[] args)
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/xviubu/spitter/persistence/persistence-context.xml");

		SpitterDao dao = (SpitterDao) ctx.getBean("SpitterDao");
	  	Spitter spitter = dao.getSpitterById((long)2);
		System.out.println(spitter.toString());

		Spitter sp1 = new Spitter();
		sp1.setUsername("test");
		sp1.setPassword("test");
		sp1.setFullname("Jpa test");
		sp1.setEmail("test@test.com");
		sp1.setUpdateByEmail(true);

		dao.addSpitter(sp1);

		
	}
}
