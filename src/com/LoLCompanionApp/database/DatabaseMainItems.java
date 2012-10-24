package com.LoLCompanionApp.database;

import com.LoLCompanionApp.includes.BaseObject;
import com.LoLCompanionApp.includes.ItemObject;
import com.LoLCompanionApp.includes.ItemRecipieObject;
import com.LoLCompanionApp.includes.SkillObject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DatabaseMainItems extends DatabaseMain {

	public DatabaseMainItems(Context context) {
		super(context);
	}

	public BaseObject[] getQuickItemList() throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery(
				"SELECT id,name,iconPath,price FROM items ORDER BY price ASC",
				null);

		// initialize variable
		BaseObject[] result = new BaseObject[cur.getCount()];

		// go through data
		if (cur.moveToFirst()) {
			for (int i = 0; i < result.length; i += 1) {
				result[i] = new BaseObject(
						cur.getInt(cur.getColumnIndex("id")), cur.getString(cur
								.getColumnIndex("displayName")),
						fixIconPathName(cur.getString(cur
								.getColumnIndex("iconPath"))), cur.getInt(cur
								.getColumnIndex("price")));
				cur.moveToNext();
			}
		}
		database.close();

		return result;
	}

	private BaseObject getBaseItemObject(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database
				.rawQuery(
						"SELECT id,name,iconPath,price FROM items WHERE id=? ORDER BY price ASC",
						new String[] { Integer.toString(id) });

		// initialize variable
		BaseObject result = null;

		// go through data
		if (cur.moveToFirst()) {
			result = new BaseObject(cur.getInt(cur.getColumnIndex("id")),
					cur.getString(cur.getColumnIndex("displayName")),
					fixIconPathName(cur.getString(cur
							.getColumnIndex("iconPath"))), cur.getInt(cur
							.getColumnIndex("price")));
		}
		database.close();

		return result;
	}

	public ItemObject[] getAllItemsInCategory(int categoryId)
			throws SQLiteException {

		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor idCur = database
				.rawQuery(
						"SELECT itemId FROM itemItemCategories WHERE itemCategoryId=? ORDER BY price ASC",
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

		ItemObject[] result = new ItemObject[idCount];

		if (itemIds != "") {

			// run the query
			Cursor itemCur = database.rawQuery(
					"SELECT name FROM items ? ORDER BY price ASC",
					new String[] { itemIds });

			if (itemCur.moveToFirst()) {
				for (int i = 0; i < idCount; i += 1) {
					result[i] = new ItemObject(
							itemCur.getInt(itemCur.getColumnIndex("id")),
							itemCur.getString(itemCur.getColumnIndex("name")),
							itemCur.getString(itemCur
									.getColumnIndex("iconPath")),
							itemCur.getInt(itemCur.getColumnIndex("price")),
							itemCur.getString(itemCur
									.getColumnIndex("description")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatHPPoolMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatMPPoolMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentHPPoolMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentMPPoolMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatHPRegenMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentHPRegenMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatMPRegenMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentMPRegenMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentArmorMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatAttackDamageMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentAttackDamageMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatAbilityPowerMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentAbilityPowerMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatMovementSpeedMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentMovementSpeedMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatAttackSpeedMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentAttackSpeedMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatDodgeMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentDodgeMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatCritChanceMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentCritChanceMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatCritDamageMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentCritDamageMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatMagicResistMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("percentMagicResistMod")),
							itemCur.getInt(itemCur
									.getColumnIndex("flatEXPBonus")), itemCur
									.getInt(itemCur
											.getColumnIndex("percentEXPBonus")));
					itemCur.moveToNext();
				}
			}
		}

		database.close();

		return result;
	}

	public ItemRecipieObject getItemRecipie(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor idCur = database
				.rawQuery(
						"SELECT recipieItemId FROM itemRecipies WHERE buildsToItemId=?",
						new String[] { Integer.toString(id) });

		// initialize variable
		BaseObject[] requiredItems = new BaseObject[idCur.getCount()];

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

		if (itemIds != "") {
			// run the query
			Cursor cur = database
					.rawQuery(
							"SELECT id,name,iconPath,price FROM items ? ORDER BY price ASC",
							new String[] { itemIds });

			// go through data
			if (cur.moveToFirst()) {
				for (int i = 0; i < requiredItems.length; i += 1) {
					requiredItems[i] = new BaseObject(cur.getInt(cur
							.getColumnIndex("id")), cur.getString(cur
							.getColumnIndex("displayName")),
							fixIconPathName(cur.getString(cur
									.getColumnIndex("iconPath"))),
							cur.getInt(cur.getColumnIndex("price")));
					cur.moveToNext();
				}
			}
		}

		database.close();

		return new ItemRecipieObject(getBaseItemObject(id), requiredItems);
	}

	public ItemObject getItemStats(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database.rawQuery("SELECT * FROM items WHERE id=?",
				new String[] { Integer.toString(id) });

		ItemObject item;

		if (cur.moveToFirst()) {
			item = new ItemObject(cur.getInt(cur.getColumnIndex("id")),
					cur.getString(cur.getColumnIndex("name")),
					cur.getString(cur.getColumnIndex("iconPath")),
					cur.getInt(cur.getColumnIndex("price")), cur.getString(cur
							.getColumnIndex("description")), cur.getInt(cur
							.getColumnIndex("flatHPPoolMod")), cur.getInt(cur
							.getColumnIndex("flatMPPoolMod")), cur.getInt(cur
							.getColumnIndex("percentHPPoolMod")),
					cur.getInt(cur.getColumnIndex("percentMPPoolMod")),
					cur.getInt(cur.getColumnIndex("flatHPRegenMod")),
					cur.getInt(cur.getColumnIndex("percentHPRegenMod")),
					cur.getInt(cur.getColumnIndex("flatMPRegenMod")),
					cur.getInt(cur.getColumnIndex("percentMPRegenMod")),
					cur.getInt(cur.getColumnIndex("flatArmorMod")),
					cur.getInt(cur.getColumnIndex("percentArmorMod")),
					cur.getInt(cur.getColumnIndex("flatAttackDamageMod")),
					cur.getInt(cur.getColumnIndex("percentAttackDamageMod")),
					cur.getInt(cur.getColumnIndex("flatAbilityPowerMod")),
					cur.getInt(cur.getColumnIndex("percentAbilityPowerMod")),
					cur.getInt(cur.getColumnIndex("flatMovementSpeedMod")),
					cur.getInt(cur.getColumnIndex("percentMovementSpeedMod")),
					cur.getInt(cur.getColumnIndex("flatAttackSpeedMod")),
					cur.getInt(cur.getColumnIndex("percentAttackSpeedMod")),
					cur.getInt(cur.getColumnIndex("flatDodgeMod")),
					cur.getInt(cur.getColumnIndex("percentDodgeMod")),
					cur.getInt(cur.getColumnIndex("flatCritChanceMod")),
					cur.getInt(cur.getColumnIndex("percentCritChanceMod")),
					cur.getInt(cur.getColumnIndex("flatCritDamageMod")),
					cur.getInt(cur.getColumnIndex("percentCritDamageMod")),
					cur.getInt(cur.getColumnIndex("flatMagicResistMod")),
					cur.getInt(cur.getColumnIndex("percentMagicResistMod")),
					cur.getInt(cur.getColumnIndex("flatEXPBonus")),
					cur.getInt(cur.getColumnIndex("percentEXPBonus")));
		}
		
		database.close();

		return item;
	}

	public int[] getRecipiesContainingItem(int id) throws SQLiteException {
		SQLiteDatabase database = getReadableDatabase();

		// run the query
		Cursor cur = database
				.rawQuery(
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
