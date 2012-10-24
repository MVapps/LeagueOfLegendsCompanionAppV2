package com.LoLCompanionApp.database;

import java.util.ArrayList;
import com.LoLCompanionApp.includes.BaseObject;
import com.LoLCompanionApp.includes.ChampionObject;
import com.LoLCompanionApp.includes.SkillObject;
import com.LoLCompanionApp.includes.SkinObject;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DatabaseMainChampions extends DatabaseMain {

	public DatabaseMainChampions(Context context) {
		super(context);
	}

	public ArrayList<BaseObject> getQuickChampionList() throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database
				.rawQuery(
						"SELECT id,displayName,iconPath FROM champions ORDER BY displayName ASC",
						null);

		// initialize variable
		ArrayList<BaseObject> result = new ArrayList<BaseObject>();

		// go through data and retrieve the name of drinks
		if (cur.moveToFirst()) {
			for (int i = 0; i < cur.getCount(); i += 1) {
				result.add(new BaseObject(cur.getInt(cur.getColumnIndex("id")),
						cur.getString(cur.getColumnIndex("displayName")),
						fixIconPathName(cur.getString(cur
								.getColumnIndex("iconPath"))), 0));
				cur.moveToNext();
			}
		}
		database.close();

		return result;
	}

	public BaseObject getChampionBaseObject(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery(
				"SELECT id,displayName,iconPath FROM champions WHERE id=?",
				new String[] { Integer.toString(id) });

		// initialize variable
		BaseObject result = null;

		// go through data and retrieve the name of drinks
		if (cur.moveToFirst()) {
			result = new BaseObject(cur.getInt(cur.getColumnIndex("id")),
					cur.getString(cur.getColumnIndex("displayName")),
					fixIconPathName(cur.getString(cur
							.getColumnIndex("iconPath"))), 0);
		}
		database.close();

		return result;
	}

	public ChampionObject getChampionObject(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		ChampionObject champion = null;

		// run the query
		Cursor cur = database.rawQuery("SELECT * FROM champions WHERE id=?",
				new String[] { String.valueOf(id) });

		if (cur.moveToFirst()) {
			// initialize variable
			champion = new ChampionObject(cur.getInt(cur.getColumnIndex("id")),
					cur.getString(cur.getColumnIndex("displayName")),
					fixIconPathName(cur.getString(cur
							.getColumnIndex("iconPath"))), 0, cur.getString(cur
							.getColumnIndex("title")), cur.getString(cur
							.getColumnIndex("tags")), cur.getString(cur
							.getColumnIndex("description")), cur.getInt(cur
							.getColumnIndex("healthBase")), cur.getInt(cur
							.getColumnIndex("healthLevel")), cur.getInt(cur
							.getColumnIndex("attackLevel")), cur.getInt(cur
							.getColumnIndex("attackBase")), cur.getInt(cur
							.getColumnIndex("range")), cur.getInt(cur
							.getColumnIndex("moveSpeed")), cur.getInt(cur
							.getColumnIndex("armorBase")), cur.getInt(cur
							.getColumnIndex("armorLevel")), cur.getInt(cur
							.getColumnIndex("manaBase")), cur.getInt(cur
							.getColumnIndex("manaLevel")), cur.getInt(cur
							.getColumnIndex("criticalChangeBxase")),
					cur.getInt(cur.getColumnIndex("criticalChangeLevel")),
					cur.getInt(cur.getColumnIndex("manaRegenBase")),
					cur.getInt(cur.getColumnIndex("manaRegenLevel")),
					cur.getInt(cur.getColumnIndex("healthRegenBase")),
					cur.getInt(cur.getColumnIndex("healthRegenLevel")),
					cur.getInt(cur.getColumnIndex("magicResistBase")),
					cur.getInt(cur.getColumnIndex("magicResistLevel")),
					cur.getInt(cur.getColumnIndex("ratingDefense")),
					cur.getInt(cur.getColumnIndex("ratingMagic")),
					cur.getInt(cur.getColumnIndex("ratingDifficulty")),
					cur.getInt(cur.getColumnIndex("ratingAttack")));
		}
		database.close();

		return champion;
	}

	public ArrayList<SkillObject> getChampionSkills(int id) {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery(
				"SELECT * FROM championAbilities WHERE championId=?",
				new String[] { String.valueOf(id) });

		ArrayList<SkillObject> skills = new ArrayList<SkillObject>();

		if (cur.moveToFirst()) {
			for (int i = 0; i < cur.getCount(); i++) {
				skills.add(new SkillObject(cur.getInt(cur
						.getColumnIndex("championId")), cur.getInt(cur
						.getColumnIndex("rank")), cur.getString(cur
						.getColumnIndex("name")), cur.getString(cur
						.getColumnIndex("cost")), cur.getString(cur
						.getColumnIndex("cooldown")), fixIconPathName(cur
						.getString(cur.getColumnIndex("iconPath"))), cur
						.getInt(cur.getColumnIndex("range")), cur.getString(cur
						.getColumnIndex("effect")), cur.getString(cur
						.getColumnIndex("description")), cur.getString(
						cur.getColumnIndex("hotKey")).charAt(0)));
				cur.moveToNext();
			}
		}
		database.close();

		return skills;
	}

	public ArrayList<SkinObject> getChampionSkins(int id) {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery(
				"SELECT * FROM championSkins WHERE championId=?",
				new String[] { String.valueOf(id) });

		ArrayList<SkinObject> skills = new ArrayList<SkinObject>();

		if (cur.moveToFirst()) {
			for (int i = 0; i < cur.getCount(); i++) {
				skills.add(new SkinObject(cur.getInt(cur
						.getColumnIndex("championId")), cur.getInt(cur
						.getColumnIndex("rank")), cur.getString(cur
						.getColumnIndex("displayName")), fixIconPathName(cur
						.getString(cur.getColumnIndex("portraitPath")))));
				cur.moveToNext();
			}
		}
		database.close();

		return skills;
	}

}