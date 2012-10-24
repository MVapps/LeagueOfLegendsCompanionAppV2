package com.LoLCompanionApp.includes;

public class BaseObject {
	
	private int id;
	private String name;
	private String iconPath;
	private int price;
	
	public BaseObject (int id, String name, String iconPath,int price)
	{
		this.id = id;
		this.iconPath = iconPath;
		this.name = name;
		this.price = price;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public int getPrice()
	{
		return this.price;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getIconPath()
	{
		return this.iconPath;
	}

}
