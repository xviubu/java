package com.xviubu.spitter.persistence;

import java.util.*;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xviubu.spitter.model.*;

@Repository
public class HibernateSpitterDao implements SpitterDao
{
	@Autowired
	public HibernateSpitterDao(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession()
	{
		return sessionFactory.openSession();
	}

	public void addSpitter(Spitter spitter)
	{
		currentSession().save(spitter);
	}

	public Spitter getSpitterById(long id)
	{
		return (Spitter) currentSession().get(Spitter.class,id);
	}

	public void saveSpitter(Spitter spitter)
	{
		currentSession().update(spitter);
	}

	public Spittle getSpittleById(long id)
	{
		return (Spittle) currentSession().get(Spittle.class,id);
	}

	public void addSpittle(Spittle spittle)
	{
		currentSession().save(spittle);
	}
	public void saveSpittle(Spittle spittle)
	{
		currentSession().update(spittle);
	}

	private SessionFactory sessionFactory;
}
