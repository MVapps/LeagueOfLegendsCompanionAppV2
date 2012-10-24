package com.LoLCompanionApp.includes;

public class SkinObject {
	
	private int rank;
	private int championId;
	private String name;
	private String iconPath;
	
	public SkinObject(int championId, int rank, String name, String iconPath)
	{
		this.rank = rank;
		this.championId = championId;
		this.name = name;
		this.iconPath = iconPath;
	}

	public String getIconPath()
	{
		return iconPath;
	}

	public String getName()
	{
		return name;
	}

	public int getRank()
	{
		return rank;
	}

	public int getChampionId()
	{
		return championId;
	}
	
}
