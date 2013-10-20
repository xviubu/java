package com.xviubu.spitter.service;

import com.xviubu.spitter.model.*;
import com.xviubu.spitter.persistence.*;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;

public class SpitterServiceImpl implements SpitterService
{
	public void setSpitterDao(SpitterDao spitterDao)
	{
		this.spitterDao = spitterDao;
	}
	public void setTransactionTemplate(TransactionTemplate txTemplate)
	{
		this.txTemplate = txTemplate;
	}

	public void saveSpitter(final Spitter spitter)
	{
		txTemplate.execute(new TransactionCallback<Void>()
							{
								public Void doInTransaction(TransactionStatus txStatus)	
								{
									try
									{
										spitterDao.saveSpitter(spitter);
									}
									catch(RuntimeException e)
									{
										txStatus.setRollbackOnly();
										throw e;
									}
									return null;
								}
							});	
	}

	private SpitterDao spitterDao;
	private TransactionTemplate txTemplate;
}
