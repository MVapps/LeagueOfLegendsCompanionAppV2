package com.LoLCompanionApp.database;

import android.content.Context;

public class DatabaseMain extends DatabaseHelper {

	public DatabaseMain(Context context) {
		super(context, "gameStats_en_US.sqlite");
	}
	
}
