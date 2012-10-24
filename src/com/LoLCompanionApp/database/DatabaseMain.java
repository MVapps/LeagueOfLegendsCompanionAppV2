package com.LoLCompanionApp.database;

import android.content.Context;

public class DatabaseMain extends DatabaseHelper {

	public DatabaseMain(Context context) {
		super(context, "gameStats_en_US.sqlite");
	}

	public String fixIconPathName(String str) {
		// Split at .jpg part. Take only name.
		String[] imageName = str.split(".");
		// Format the name before returning
		return "img_" + imageName[0].replace('-', '_').replace(' ', '_').toLowerCase();
	}

}
