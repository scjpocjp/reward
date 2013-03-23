package com.android.reward.lib.database;


/**
 * Raw query 
 * 
 */
public class RawQuery extends Query {


	private String sqlQuery;
	
	public RawQuery ( String sqlQuery, QueryInterface queryInterface, int id  ) {
		super( queryInterface, id );
		
		this.sqlQuery = sqlQuery;
	}
	
	
	// returning the query 
	public String getSQLQuery () {
		return sqlQuery;
	}
	
}
