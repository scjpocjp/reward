package com.android.reward.lib.database;


/**
 *  Delete query
 */
public class DeleteQuery extends Query {

	private String whereClause;
	
	public DeleteQuery ( String tableName, String whereClause,  QueryInterface queryInterface, int id) {
		super( tableName, queryInterface , id);
		this.whereClause = whereClause;
	}
	
	
	/**
	 * getting the where clause
	 */
	public String getWhereClause () {
		return whereClause;
	}

}
