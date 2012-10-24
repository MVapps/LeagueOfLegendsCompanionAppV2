package com.LoLCompanionApp.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.LoLCompanionApp.includes.CounterObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;

public class DatabaseExtra extends DatabaseHelper {

	final String USER_COUNTER_TABLE = "usercounteredby";
	final String DEFAULT_COUNTER_TABLE = "defaultcounteredby";
	final String BACKUP_PATH, BACKUP_USER_FILE, BACKUP_DEFAULT_FILE;
	final Context context;

	public DatabaseExtra(Context context) {
		super(context, "extrainfo.sqlite");
		this.context = context;

		// set backup variables
		BACKUP_PATH = Environment.getExternalStorageDirectory().toString()
				+ File.separator + "LoLCompanionAppBackup" + File.separator;
		BACKUP_USER_FILE = "backup_" + USER_COUNTER_TABLE + ".txt";
		BACKUP_DEFAULT_FILE = "backup_" + DEFAULT_COUNTER_TABLE + ".txt";
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			// backup the counters
			backupUserCounters();
			backupDefaultCounters();
			// upgrade the databases
			super.onUpgrade(db, oldVersion, newVersion);
			// import the user counters
			importUserCounters();
			importDefaultCounters();
		} catch (SQLiteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CounterObject> getCounteredByChampions(int id) {

		return getCounterChampions(id, "champid", "counterid");
	}

	public ArrayList<CounterObject> getCounteringChampions(int id) {

		return getCounterChampions(id, "counterid", "champid");
	}

	private ArrayList<CounterObject> getCounterChampions(int id,
			String searchColumn, String champColumn) {
		// get champions that counter the chosen champion
		SQLiteDatabase database = getReadableDatabase();

		// create cursor array to hold the two result sets (ne for default
		// values and one for user values)
		Cursor[] curArray = {
				database.rawQuery("SELECT * FROM ? WHERE ?=?",
						new String[] { USER_COUNTER_TABLE, searchColumn,
								Integer.toString(id) }),
				database.rawQuery(
						"SELECT * FROM ? WHERE ?=? AND visible='true'",
						new String[] { DEFAULT_COUNTER_TABLE, searchColumn,
								Integer.toString(id) }) };

		ArrayList<CounterObject> results = new ArrayList<CounterObject>();

		// go through array of cursors and put in values.
		for (int j = 0; j < curArray.length; j += 1) {
			// go through the data and fill the array
			if (curArray[j].moveToFirst()) {
				for (int i = 0; i < curArray[j].getCount(); i += 1) {

					CounterObject.CounterType type;
					// first pass is user table, second is default table.
					if (j == 0) {
						type = CounterObject.CounterType.user;
					} else {
						type = CounterObject.CounterType.original;
					}

					results.add(new CounterObject(
							curArray[j].getInt(curArray[j]
									.getColumnIndex(champColumn)),
							curArray[j].getString(curArray[j]
									.getColumnIndex("description")),
							curArray[j].getString(curArray[j]
									.getColumnIndex("role")),
							curArray[j].getString(curArray[j]
									.getColumnIndex("tips")),
							curArray[j].getInt(curArray[j].getColumnIndex("id")),
							type));

					// move to next row
					curArray[j].moveToNext();
				}
			}
		}

		database.close();

		return results;
	}

	public void deleteAllUserCounters() throws SQLiteException {
		SQLiteDatabase database = getWritableDatabase();

		// clear all rows from table
		database.delete(USER_COUNTER_TABLE, null, null);

		database.close();
	}

	public void restoreDefaultCounters() throws SQLiteException {
		// delete all user counters
		deleteAllUserCounters();

		SQLiteDatabase database = getWritableDatabase();

		// create new values for default database
		ContentValues values = new ContentValues();
		values.put("visible", "true");

		// update the database with new values. (make rows disappear)
		database.update(DEFAULT_COUNTER_TABLE, values, null, null);

		database.close();
	}

	public boolean deleteCounter(String id, String type) throws SQLiteException {
		SQLiteDatabase database = getWritableDatabase();

		// if deleting a row that is added by the user
		if (type.equals("user")) {
			try {
				database.delete(USER_COUNTER_TABLE, "id=?", new String[] { id });
				database.close();
				return true;
			} catch (SQLiteException e) {
				e.printStackTrace();
			}
		}
		// else deleting a default row. Only hide default rows.
		else {
			// create new values
			ContentValues values = new ContentValues();
			values.put("visible", "false");

			try {
				// update the database with new values. (make rows disappear)
				database.update(DEFAULT_COUNTER_TABLE, values, "id=?",
						new String[] { id });
				database.close();
				return true;
			} catch (SQLiteException e) {
				e.printStackTrace();
			}
		}

		database.close();
		return false;
	}

	public void restoreDefaultToVisible(String id) {
		SQLiteDatabase database = getWritableDatabase();

		// create new values
		ContentValues values = new ContentValues();
		values.put("visible", "true");

		try {
			// update the database with new values. (make rows appear)
			database.update(DEFAULT_COUNTER_TABLE, values, "id=?",
					new String[] { id });
		} catch (SQLiteException e) {
			e.printStackTrace();
		}

		database.close();
	}

	public void addNewCounter(String counter, String champ, String role,
			String tips, String description) throws SQLiteException {
		SQLiteDatabase database = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("champid", champ);
		values.put("counterid", counter);
		values.put("role", role);
		values.put("tips", tips);
		values.put("description", description);

		// insert the new drink into the database
		database.insert(USER_COUNTER_TABLE, null, values);

		database.close();
	}

	public void backupUserCounters() throws IOException, SQLiteException {
		// get path and create new backup file
		File userPath = new File(BACKUP_PATH);
		userPath.mkdirs();

		File userFile = new File(BACKUP_PATH + BACKUP_USER_FILE);
		userFile.createNewFile();

		// new file writer
		FileWriter fileUserWriter = new FileWriter(userFile);

		SQLiteDatabase database = getReadableDatabase();

		// get database cursors
		Cursor curUser = database.rawQuery("SELECT * FROM ?",
				new String[] { USER_COUNTER_TABLE });

		// write the user files
		// write down the actual information written by the user into a file in
		// order to restore it in the future
		if (curUser.moveToFirst()) {
			// get the names of columns in the database
			String[] dbColumns = curUser.getColumnNames();
			String row = "";
			fileUserWriter.write("");

			// get the rows to add into the database
			// start at 1 to skip id column
			for (int i = 1; i < dbColumns.length; i += 1) {
				row += dbColumns[i];
				// if i is not at the last pass, add a delimener
				if ((i + 1) < dbColumns.length) {
					row += "|";
				}
			}

			// append the first row that has column names
			fileUserWriter.append(row + "\n");

			while (!curUser.isAfterLast()) {
				// reset row string
				row = "";

				// populate string with data
				for (int i = 1; i < dbColumns.length; i += 1) {
					row += curUser.getString(curUser
							.getColumnIndex(dbColumns[i]));
					// if not at last pass, add deliminer
					if ((i + 1) < dbColumns.length) {
						row += "|";
					}
				}
				// append to the file
				fileUserWriter.append(row + "\n");

				// go to next line
				curUser.moveToNext();
			}
			// flush and close the writer
			fileUserWriter.flush();
			fileUserWriter.close();
		}

		database.close();
	}

	public void backupDefaultCounters() throws IOException, SQLiteException {
		// get path and create new backup file
		File defaultPath = new File(BACKUP_PATH);
		defaultPath.mkdirs();

		File defaultFile = new File(BACKUP_PATH + BACKUP_DEFAULT_FILE);
		defaultFile.createNewFile();

		// create file writers
		FileWriter fileDefaultWriter = new FileWriter(defaultFile);

		SQLiteDatabase database = getReadableDatabase();

		Cursor curDefault = database.rawQuery(
				"SELECT * FROM ? WHERE visible='false'",
				new String[] { DEFAULT_COUNTER_TABLE });

		// write the the default file.
		// write down all ids of the files that have been marked as
		// "deleted by the user" to persist the changes in a new version
		if (curDefault.moveToFirst()) {
			String row;
			fileDefaultWriter.write("");

			while (!curDefault.isAfterLast()) {
				// get the id of the row that is "deleted"
				row = curDefault.getString(curDefault.getColumnIndex("id"));

				// append id to file
				fileDefaultWriter.append(row);

				// if not the last row, append with deliminer ','
				if (!curDefault.isLast()) {
					fileDefaultWriter.append(",");
				}

				curDefault.moveToNext();
			}
			// flush and close the writer
			fileDefaultWriter.flush();
			fileDefaultWriter.close();
		}

		database.close();
	}

	public void importDefaultCounters() throws SQLiteException, IOException {
		try {
			File defaultFile = new File(BACKUP_PATH + BACKUP_DEFAULT_FILE);
			BufferedReader buffreader = new BufferedReader(new FileReader(
					defaultFile));

			// read the data (all in one line because was appended)
			String[] defaultIds = buffreader.readLine().split(",");

			for (String id : defaultIds) {
				restoreDefaultToVisible(id);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void importUserCounters() throws IOException, SQLiteException {
		try {
			File userFile = new File(BACKUP_PATH + BACKUP_USER_FILE);
			BufferedReader buffreader = new BufferedReader(new FileReader(
					userFile));

			// read first line (columns)
			String line = buffreader.readLine();

			// read the rest of the lines to get the data
			line = buffreader.readLine();
			while (!line.equals("")) {
				String[] data = line.split("\\|");

				// add the counter data into the database
				addNewCounter(data[1], data[0], data[3], data[4], data[2]);

				// read next line
				line = buffreader.readLine();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getBackupPath() {
		return BACKUP_PATH;
	}

	public String getCreatureGroup(String creature) {
		String result = "";

		SQLiteDatabase database = getReadableDatabase();

		// run the query and get result
		Cursor cur = database.rawQuery(
				"SELECT creaturegroup FROM spawns WHERE creature=?",
				new String[] { creature });
		if (cur.moveToFirst()) {
			result = cur.getString(0);
		}

		database.close();

		return result;
	}

	public long getCreatureInitialSpawnTime(String creature) {
		long result = 0;

		SQLiteDatabase database = getReadableDatabase();

		// run the query and get result
		Cursor cur = database.rawQuery(
				"SELECT initialspawn FROM spawns WHERE creature=?",
				new String[] { creature });
		if (cur.moveToFirst()) {
			result = cur.getLong(0);
		}

		database.close();

		return result;
	}

	public long getCreatureRespawnTime(String creature) {
		long result = 0;

		SQLiteDatabase database = getReadableDatabase();

		// run the query and get result
		Cursor cur = database.rawQuery(
				"SELECT respawn FROM spawns WHERE creature=?",
				new String[] { creature });
		if (cur.moveToFirst()) {
			result = cur.getLong(0);
		}

		database.close();

		return result;
	}

	public String getDefaultCreatureOrder() {
		return getDefaultValue("creature order");
	}

	public String getDefaultNotificationType() {
		return getDefaultValue("notification");
	}

	private String getDefaultValue(String type) {
		String result = "";

		SQLiteDatabase database = getReadableDatabase();

		// run the query and get result
		Cursor cur = database.rawQuery(
				"SELECT value FROM settings WHERE setting=?",
				new String[] { type });
		if (cur.moveToFirst()) {
			result = cur.getString(0);
		}

		database.close();

		return result;
	}

}
