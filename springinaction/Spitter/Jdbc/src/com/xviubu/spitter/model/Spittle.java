package com.xviubu.spitter.model;

import java.util.Date;

public class Spittle
{
	public Spittle()
	{
		this.spitter = new Spitter();
		this.spitter.setId((long)1);
	}

	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}

	public Date getWhen()
	{
		return when;
	}
	public void setId(Date when)
	{
		this.when = when;
	}

	public Spitter getSpitter()
	{
		return spitter;
	}
	public void setSpitter(Spitter spitter)
	{
		this.spitter = spitter;
	}

	private long id;
	private Spitter spitter;
	private String text;
	private Date when;
}
