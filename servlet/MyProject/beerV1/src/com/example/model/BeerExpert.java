package com.example.model;

import java.util.*;

public class BeerExpert
{
	public List getBrants(String color)
	{
		List brants = new ArrayList();
		if(color.equals("amber"))
		{
			brants.add("Jack Amber");
			brants.add("Red Moose");
		}
		else
		{
			brants.add("Jail Pale Ale");
			brants.add("Gout Stout");
		}

		return brants;
	}
}
