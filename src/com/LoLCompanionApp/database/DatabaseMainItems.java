package com.LoLCompanionApp.database;

import android.content.Context;
import android.database.sqlite.SQLiteException;

public class DatabaseMainItems extends DatabaseMain {

	public DatabaseMainItems(Context context) {
		super(context);
	}

	public String[] getAllItems() throws SQLiteException
	{
		return new String[1];
	}

	public String[] getAllItemsInCategory() throws SQLiteException
	{
		return new String[1];
	}

	public String[] getAllItemsInCategoryWithId() throws SQLiteException
	{
		return new String[1];
	}
	
	public String[][] getAllItemsWithId() throws SQLiteException
	{
		return new String[1][1];
	}
	
	public String[] getItemRecipie() throws SQLiteException
	{
		return new String[1];
	}
	
	public String[] getItemStats() throws SQLiteException
	{
		return new String[1];
	}
	
	public String[] getRecipiesContainingItem() throws SQLiteException
	{
		return new String[1];
	}
	
}
