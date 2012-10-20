package com.LoLCompanionApp.database;

import android.content.Context;

public class DatabaseMain extends DatabaseHelper {

	public DatabaseMain(Context context) {
		super(context, "gameStats_en_US.sqlite");
	}

	public String fixIconPathName(String str)
	{
		return "img_" + str.replace('-', '_').replace(' ', '_').toLowerCase();
	}
	
}
