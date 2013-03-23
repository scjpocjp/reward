package com.android.reward.lib.database;


public class DatabaseUtil {

	private static DatabaseUtil databaseUtil;

	private DatabaseUtil() {
		this.databaseUtil = this;
	}

	public static DatabaseUtil getInstance() {
		if (databaseUtil == null) {
			new DatabaseUtil();
		}
		return databaseUtil;
	}
}