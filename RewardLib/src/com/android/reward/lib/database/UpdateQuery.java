package com.android.reward.lib.database;

import android.content.ContentValues;

/**
 * Update Query 
 */
public class UpdateQuery extends Query {

	private String whereClause;
	private ContentValues values;

	public UpdateQuery ( String tableName, String whereClause, ContentValues values,  QueryInterface queryInterface, int id ) {
		super( tableName, queryInterface, id );
		this.whereClause = whereClause;
		this.values = values;
	}
	
	// returning the where clause
	public String getWhereClause () {
		return whereClause;
	}

	// returning the content values
	public ContentValues getContentValues () {
		return values;
	}
	
}
