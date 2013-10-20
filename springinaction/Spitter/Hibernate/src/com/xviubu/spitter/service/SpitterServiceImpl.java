package com.xviubu.spitter.service;

import com.xviubu.spitter.model.*;
import com.xviubu.spitter.persistence.*;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component("spitterService")
public class SpitterServiceImpl implements SpitterService
{
	public void setSpitterDao(SpitterDao spitterDao)
	{
		this.spitterDao = spitterDao;
	}

	public void addSpittle(Spittle spittle)
	{
		spitterDao.addSpittle(spittle);
	}

	@Autowired
	private SpitterDao spitterDao;
}
