package com.LoLCompanionApp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DatabaseMainItems extends DatabaseMain {

	public DatabaseMainItems(Context context) {
		super(context);
	}

	public String[] getAllItems() throws SQLiteException {
		String[] result = null;

		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery("SELECT name FROM items ORDER BY name ASC", null);

		// initialize variable
		result = new String[cur.getCount()];

		if (cur.moveToFirst()) {
			for (int i = 0; i < result.length; i += 1) {
				result[i] = cur.getString(0);
				cur.moveToNext();
			}
		}
		database.close();

		return result;
	}

	public String[][] getAllItemsWithId() throws SQLiteException {
		String[][] result = null;

		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery("SELECT id,name FROM items ORDER BY name ASC", null);

		// initialize variable
		result = new String[cur.getCount()][2];

		if (cur.moveToFirst()) {
			for (int i = 0; i < result.length; i += 1) {
				result[i][0] = cur.getString(cur.getColumnIndex("id"));
				result[i][1] = cur.getString(cur.getColumnIndex("displayName"));
				cur.moveToNext();
			}
		}
		database.close();

		return result;
	}

	public String[] getAllItemsInCategory(int categoryId) throws SQLiteException {

		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor idCur = database.rawQuery(
				"SELECT itemId FROM itemItemCategories WHERE itemCategoryId=? ORDER BY name ASC",
				new String[] { String.valueOf(categoryId) });

		String itemIds = "";
		int idCount = idCur.getCount();

		if (idCur.moveToFirst()) {
			itemIds = "WHERE id=";
			for (int i = 0; i < idCount; i += 1) {
				itemIds += idCur.getString(0);
				idCur.moveToNext();
				if (i != idCount - 1)
					itemIds += ",";
			}
		}

		String[] result = new String[idCount];

		// run the query
		Cursor itemCur = database.rawQuery("SELECT name FROM items ? ORDER BY price ASC",
				new String[] { itemIds });

		if (itemCur.moveToFirst()) {
			for (int i = 0; i < idCount; i += 1) {
				result[i] = itemCur.getString(0);
				itemCur.moveToNext();
			}
		}

		database.close();

		return result;
	}

	public String[][] getAllItemsInCategoryWithId(int categoryId) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor idCur = database.rawQuery(
				"SELECT itemId FROM itemItemCategories WHERE itemCategoryId=? ORDER BY name ASC",
				new String[] { String.valueOf(categoryId) });

		String itemIds = "";
		int idCount = idCur.getCount();

		if (idCur.moveToFirst()) {
			itemIds = "WHERE id=";
			for (int i = 0; i < idCount; i += 1) {
				itemIds += idCur.getString(0);
				idCur.moveToNext();
				if (i != idCount - 1)
					itemIds += ",";
			}
		}

		String[][] result = new String[idCount][2];

		// run the query
		Cursor itemCur = database.rawQuery("SELECT name FROM items ? ORDER BY price ASC",
				new String[] { itemIds });

		if (itemCur.moveToFirst()) {
			for (int i = 0; i < idCount; i += 1) {
				result[i][0] = itemCur.getString(itemCur.getColumnIndex("id"));
				result[i][1] = itemCur.getString(itemCur.getColumnIndex("name"));
				itemCur.moveToNext();
			}
		}

		database.close();

		return result;
	}

	public int[] getItemRecipie(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery(
				"SELECT recipieItemId FROM itemRecipies WHERE buildsToItemId=?",
				new String[] { Integer.toString(id) });

		// initialize variable
		int[] result = new int[cur.getCount()];

		if (cur.moveToFirst()) {
			for (int i = 0; i < result.length; i += 1) {
				result[i] = cur.getInt(0);
				cur.moveToNext();
			}
		}
		database.close();

		return result;
	}

	public String getItemDescription(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery("SELECT description FROM items WHERE id=?",
				new String[] { Integer.toString(id) });

		// initialize variable
		String result = "";

		if (cur.moveToFirst()) {
			result = cur.getString(0);
		}
		database.close();

		return result;
	}

	public String getItemName(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery("SELECT name FROM items WHERE id=?",
				new String[] { Integer.toString(id) });

		// initialize variable
		String result = "";

		if (cur.moveToFirst()) {
			result = cur.getString(0);
		}
		database.close();

		return result;
	}

	public String getItemId(String name) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database
				.rawQuery("SELECT name FROM items WHERE id='?'", new String[] { name });

		// initialize variable
		String result = "";

		if (cur.moveToFirst()) {
			result = cur.getString(0);
		}
		database.close();

		return result;
	}

	public int getItemPrice(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery("SELECT price FROM items WHERE id=?",
				new String[] { Integer.toString(id) });

		// initialize variable
		int result = 0;

		if (cur.moveToFirst()) {
			result = cur.getInt(0);
		}
		database.close();

		return result;
	}

	public String getItemIconPath(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery("SELECT iconPath FROM items WHERE id=?",
				new String[] { Integer.toString(id) });

		// initialize variable
		String result = "";

		if (cur.moveToFirst()) {
			result = cur.getString(0);
		}
		database.close();

		return "img_" + result.toLowerCase().toLowerCase();
	}

	public String[] getItemStats(int id) throws SQLiteException {
		String result[] = new String[28];

		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery("SELECT * FROM champions WHERE id=?",
				new String[] { String.valueOf(id) });

		// go through data and retrieve the name of drinks
		if (cur.moveToFirst()) {
			// initialize variable
			result[0] = cur.getString(cur.getColumnIndex("flatHPPoolMod"));
			result[1] = cur.getString(cur.getColumnIndex("flatMPPoolMod"));
			result[2] = cur.getString(cur.getColumnIndex("percentHPPoolMod"));
			result[3] = cur.getString(cur.getColumnIndex("percentMPPoolMod"));
			result[4] = cur.getString(cur.getColumnIndex("flatHPRegenMod"));
			result[5] = cur.getString(cur.getColumnIndex("percentHPRegenMod"));
			result[6] = cur.getString(cur.getColumnIndex("flatMPRegenMod"));
			result[7] = cur.getString(cur.getColumnIndex("percentMPRegenMod"));
			result[8] = cur.getString(cur.getColumnIndex("flatArmorMod"));
			result[9] = cur.getString(cur.getColumnIndex("percentArmorMod"));
			result[10] = cur.getString(cur.getColumnIndex("flatAttackDamageMod"));
			result[11] = cur.getString(cur.getColumnIndex("percentAttackDamageMod"));
			result[12] = cur.getString(cur.getColumnIndex("flatAbilityPowerMod"));
			result[13] = cur.getString(cur.getColumnIndex("percentAbilityPowerMod"));
			result[14] = cur.getString(cur.getColumnIndex("flatMovementSpeedMod"));
			result[15] = cur.getString(cur.getColumnIndex("percentMovementSpeedMod"));
			result[16] = cur.getString(cur.getColumnIndex("flatAttackSpeedMod"));
			result[17] = cur.getString(cur.getColumnIndex("percentAttackSpeedMod"));
			result[18] = cur.getString(cur.getColumnIndex("flatDodgeMod"));
			result[19] = cur.getString(cur.getColumnIndex("percentDodgeMod"));
			result[20] = cur.getString(cur.getColumnIndex("flatCritChanceMod"));
			result[21] = cur.getString(cur.getColumnIndex("percentCritChanceMod"));
			result[22] = cur.getString(cur.getColumnIndex("flatCritDamageMod"));
			result[23] = cur.getString(cur.getColumnIndex("percentCritDamageMod"));
			result[24] = cur.getString(cur.getColumnIndex("flatMagicResistMod"));
			result[25] = cur.getString(cur.getColumnIndex("percentMagicResistMod"));
			result[26] = cur.getString(cur.getColumnIndex("flatEXPBonus"));
			result[27] = cur.getString(cur.getColumnIndex("percentEXPBonus"));
		}
		database.close();

		return result;
	}

	public int[] getRecipiesContainingItem(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery(
				"SELECT buildsToItemId FROM itemRecipies WHERE recipieItemId=?",
				new String[] { Integer.toString(id) });

		// initialize variable
		int[] result = new int[cur.getCount()];

		if (cur.moveToFirst()) {
			for (int i = 0; i < result.length; i += 1) {
				result[i] = cur.getInt(0);
				cur.moveToNext();
			}
		}
		database.close();

		return result;
	}

}
