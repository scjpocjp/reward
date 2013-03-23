package com.android.reward.lib.database;

import android.content.ContentValues;

/**
 *  Insert query
 */
public class InsertQuery extends Query {


	private ContentValues values;
	
	public InsertQuery ( String tableName, ContentValues values, QueryInterface queryInterface, int id  ) {
		super( tableName, queryInterface, id );
		
		this.values = values;
	}

	/**
	 * getting the content values
	 */
	public ContentValues getContentValues () {
		return values;
	}
	
}

