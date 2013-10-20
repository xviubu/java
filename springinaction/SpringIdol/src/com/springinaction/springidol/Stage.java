package com.springinaction.springidol;

public class Stage
{
	private Stage()
	{

	}

	private static class StageSingletOnHolder
	{
		static Stage instance = new Stage();
	}

	public static  Stage getInstance()
	{
		return StageSingletOnHolder.instance;
	}
}
