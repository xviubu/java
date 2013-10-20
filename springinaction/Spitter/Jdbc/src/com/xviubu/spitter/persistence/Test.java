package com.xviubu.spitter.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.xviubu.spitter.model.*;
import com.xviubu.spitter.service.*;
public class Test
{
	public static void main(String[] args)
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/xviubu/spitter/persistence/dataSource-SimpleJdbcTemplate.xml");

		SpitterService service = (SpitterService) ctx.getBean("spitterService");
/*	  	Spitter spitter = dao.getSpitterById((long)1);
		System.out.println(spitter.toString());
*/
		Spitter sp1 = new Spitter();
		sp1.setUsername("xviubu");
		sp1.setPassword("xxxxxx");
		sp1.setFullname("lishan");
		sp1.setEmail("xviubu@gmail.com");
		sp1.setUpdateByEmail(false);
		sp1.setId((long)6);

		service.saveSpitter(sp1);

		
	}
}
