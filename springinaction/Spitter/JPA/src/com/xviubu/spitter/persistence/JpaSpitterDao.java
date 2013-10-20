package com.xviubu.spitter.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xviubu.spitter.model.Spitter;
import com.xviubu.spitter.model.Spittle;

@Repository("SpitterDao")
@Transactional
public class JpaSpitterDao implements SpitterDao
{
	public void addSpitter(Spitter spitter)
	{
		em.persist(spitter);
	}

	public Spitter getSpitterById(long Id)
	{
		return em.find(Spitter.class,Id);
	}
	
	public void saveSpitter(Spitter spitter)
	{
		em.merge(spitter);
	}

	@PersistenceContext
	private EntityManager em;
	private static final String RECENT_SPITTLES = "SELECT s FROM Spittle s";
	private static final String ALL_SPITTLERS = "SELECT s FROM Spitter s";
	private static final String SPITTER_FOR_USERNAME = "SELECT s FROM Spitter s WHERE s.username = :username";
	private static final String SPITTLES_BY_USERNAME = "SELECT s FROM Spittle s WHERE s.spitter.username = :username";
}
