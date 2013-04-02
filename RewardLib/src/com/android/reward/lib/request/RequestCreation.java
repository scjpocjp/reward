package com.android.reward.lib.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;

import android.os.Message;

import com.android.reward.lib.app.RewardLibApplication;
import com.android.reward.lib.exception.RequestRepeatException;
import com.android.reward.lib.parser.SAXParsing;
import com.android.reward.lib.util.Constants;
import com.android.reward.lib.util.Keys;
import com.android.reward.lib.util.Print;

public class RequestCreation {


	public RequestCreation () {

	}

	/**
	 *  Getting the banners
	 */
	public void getBanners () {

		Print.getInstance().show( " getBanners --------------------------" );

		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.BANNER_URL, null, Constants.POST_METHOD, null );
				try {
					final Object result = request.send();
					final SAXParsing parsing = new SAXParsing ( result, SAXParsing.BANNER_PARSER );

					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = parsing.getStatus();
							if ( parsing.getStatus() == -1 ) {
								msg.obj = parsing.getReason();
							}
							Constants.appInstance.callUpdateOnFragments( msg );
						}

					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}

			}

		};
		RewardLibApplication.getThreadPoolExecutor().execute( r );
	}

	/**
	 * Getting the account points 
	 * 
	 */
	public void getAccountPoints () {

		Print.getInstance().show( " getAccountPoints --------------------------" );

		final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
		paramNameValuePair.add(  new NameValuePair( "emailId", Constants.emailId ) );


		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.ACCOUNT_POINTS_URL, null, Constants.POST_METHOD, paramNameValuePair );
				try {
					final Object result = request.send();
					final SAXParsing parsing = new SAXParsing ( result, SAXParsing.ACC_POINTS_PARSER );

					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = parsing.getStatus();
							if ( parsing.getStatus() == -1 ) {
								msg.obj = parsing.getReason();
							}
							Constants.appInstance.callUpdateOnFragments( msg );
						}
					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}
			}
		};
		RewardLibApplication.getThreadPoolExecutor().execute( r );
	}


	/**
	 * Getting the historical orders 
	 * 
	 */
	public void getHistoricalOrders () {

		Print.getInstance().show( " getHistoricalOrders --------------------------" );

		final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
		paramNameValuePair.add(  new NameValuePair( "emailId", Constants.emailId ) );


		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.HIS_ORDER_URL, null, Constants.POST_METHOD, paramNameValuePair );
				try {
					final Object result = request.send();
					final SAXParsing parsing = new SAXParsing ( result, SAXParsing.HIS_ORDER_PARSER );

					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = parsing.getStatus();
							if ( parsing.getStatus() == -1 ) {
								msg.obj = parsing.getReason();
							}
							Constants.appInstance.callUpdateOnFragments( msg );
						}
					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}
			}
		};
		RewardLibApplication.getThreadPoolExecutor().execute( r );
	}

	/**
	 * Get the order details 
	 * 
	 */
	public void getOrderDetails ( final String vOrderId ) {

		Print.getInstance().show( " getOrderDetails --------------------------" );

		final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
		paramNameValuePair.add(  new NameValuePair( "emailId", Constants.emailId ) );
		paramNameValuePair.add(  new NameValuePair( "orderid", vOrderId ) );


		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.ORDER_DETAILS_URL, null, Constants.POST_METHOD, paramNameValuePair );
				try {
					final Object result = request.send();
					final SAXParsing parsing = new SAXParsing ( result, SAXParsing.ORDER_DETAIL_PARSER );

					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = parsing.getStatus();
							if ( parsing.getStatus() == -1 ) {
								msg.obj = parsing.getReason();
							}
							Constants.appInstance.callUpdateOnFragments( msg );
						}
					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}
			}
		};
		RewardLibApplication.getThreadPoolExecutor().execute( r );
	}


	/**
	 * Get the historical points 
	 * 
	 */
	public void getHistoricalPoints ( ) {

		Print.getInstance().show( " getHistoricalPoints --------------------------" );

		final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
		paramNameValuePair.add(  new NameValuePair( "emailId", Constants.emailId ) );

		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.HIS_POINTS_URL, null, Constants.POST_METHOD, paramNameValuePair );
				try {
					final Object result = request.send();
					final SAXParsing parsing = new SAXParsing ( result, SAXParsing.HIS_POINTS_PARSER );

					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = parsing.getStatus();
							if ( parsing.getStatus() == -1 ) {
								msg.obj = parsing.getReason();
							}
							Constants.appInstance.callUpdateOnFragments( msg );
						}
					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}
			}
		};
		RewardLibApplication.getThreadPoolExecutor().execute( r );
	}


	/**
	 * Saving the shipping address 
	 * 
	 */
	public void setShippingAddress ( final String vCompany, final String vFirstName, final String vLastName, final String vPhoneNum, final String vAddr1, final String vAddr2, final String vCity, final String vZip, 
			final String vCountryId, final String vZoneId ) {

		Print.getInstance().show( " setShippingAddress --------------------------" );

		final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
		paramNameValuePair.add(  new NameValuePair( "company", vCompany ) );
		paramNameValuePair.add(  new NameValuePair( "firstname", vFirstName ) );
		paramNameValuePair.add(  new NameValuePair( "lastname", vLastName ) );
		paramNameValuePair.add(  new NameValuePair( "email", Constants.emailId ) );
		paramNameValuePair.add(  new NameValuePair( "phone", vPhoneNum ) );
		paramNameValuePair.add(  new NameValuePair( "address1", vAddr1 ) );
		paramNameValuePair.add(  new NameValuePair( "address2", vAddr2 ) );
		paramNameValuePair.add(  new NameValuePair( "city", vCity ) );
		paramNameValuePair.add(  new NameValuePair( "zip", vZip ) );
		paramNameValuePair.add(  new NameValuePair( "country_id", vCountryId ) );
		paramNameValuePair.add(  new NameValuePair( "zone_id", vZoneId
				) );


		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.SHIPPING_ADDR_URL, null, Constants.POST_METHOD, paramNameValuePair );
				try {
					final Object result = request.send();
					final SAXParsing parsing = new SAXParsing ( result, SAXParsing.SHIPPING_ADDR_PARSER );

					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = parsing.getStatus();
							if ( parsing.getStatus() == -1 ) {
								msg.obj = parsing.getReason();
							}
							Constants.appInstance.callUpdateOnFragments( msg );
						}
					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}
			}
		};
		RewardLibApplication.getThreadPoolExecutor().execute( r );
	}
	
	/**
	 * Get earn points
	 * 
	 */
	public void getEarnPoints ( ) {

		Print.getInstance().show( " getEarnPoints --------------------------" );

		final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
		paramNameValuePair.add(  new NameValuePair( "emailId", Constants.emailId ) );
		
		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.EARN_PTS_URL, null, Constants.POST_METHOD, paramNameValuePair );
				try {
					final Object result = request.send();
					final SAXParsing parsing = new SAXParsing ( result, SAXParsing.EARN_PARSER );

					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = parsing.getStatus();
							if ( parsing.getStatus() == -1 ) {
								msg.obj = parsing.getReason();
							}
							Constants.appInstance.callUpdateOnFragments( msg );
						}
					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}
			}
		};
		RewardLibApplication.getThreadPoolExecutor().execute( r );
	}
	
	/**
	 * Get burn points
	 * 
	 */
	public void getBurnPoints ( ) {

		Print.getInstance().show( " getBurnPoints --------------------------" );

		final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
		paramNameValuePair.add(  new NameValuePair( "emailId", Constants.emailId ) );
		
		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.BURN_PTS_URL, null, Constants.POST_METHOD, paramNameValuePair );
				try {
					final Object result = request.send();
					final SAXParsing parsing = new SAXParsing ( result, SAXParsing.BURN_PARSER );

					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = parsing.getStatus();
							if ( parsing.getStatus() == -1 ) {
								msg.obj = parsing.getReason();
							}
							Constants.appInstance.callUpdateOnFragments( msg );
						}
					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}
			}
		};
		RewardLibApplication.getThreadPoolExecutor().execute( r );
	}
	
	
	/**
	 * Get shopping category
	 * 
	 */
	public void getShoppingCategory ( ) {

		Print.getInstance().show( " getShoppingCategory --------------------------" );

		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.SHOPPING_CAT_URL, null, Constants.POST_METHOD, null );
				try {
					final Object result = request.send();
					final SAXParsing parsing = new SAXParsing ( result, SAXParsing.SHOPPING_CAT_PARSER );

					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = parsing.getStatus();
							if ( parsing.getStatus() == -1 ) {
								msg.obj = parsing.getReason();
							}
							Constants.appInstance.callUpdateOnFragments( msg );
						}
					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}
			}
		};
		RewardLibApplication.getThreadPoolExecutor().execute( r );
	}
	
	
	/**
	 * Get product
	 * 
	 */
	public void getProducts ( final String vCategoryId ) {

		Print.getInstance().show( " getProducts --------------------------" );

		final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
		paramNameValuePair.add(  new NameValuePair( "catid", vCategoryId ) );
		
		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.PRODUCT_URL, null, Constants.POST_METHOD, paramNameValuePair );
				try {
					final Object result = request.send();
					final SAXParsing parsing = new SAXParsing ( result, SAXParsing.PRODUCT_PARSER );

					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = parsing.getStatus();
							if ( parsing.getStatus() == -1 ) {
								msg.obj = parsing.getReason();
							}
							Constants.appInstance.callUpdateOnFragments( msg );
						}
					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}
			}
		};
		RewardLibApplication.getThreadPoolExecutor().execute( r );
	}
	
	
	/**
	 * Get product search 
	 * 
	 */
	public void getProductSearch ( final String vTerm ) {

		Print.getInstance().show( " getProductSearch --------------------------" );

		final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
		paramNameValuePair.add(  new NameValuePair( "term",  vTerm ) );
		
		
		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.PRODUCT_SEARCH_URL, null, Constants.POST_METHOD, paramNameValuePair );
				try {
					final Object result = request.send();
					final SAXParsing parsing = new SAXParsing ( result, SAXParsing.PRODUCT_SEARCH_PARSER );

					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = parsing.getStatus();
							if ( parsing.getStatus() == -1 ) {
								msg.obj = parsing.getReason();
							}
							Constants.appInstance.callUpdateOnFragments( msg );
						}
					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}
			}
		};
		RewardLibApplication.getThreadPoolExecutor().execute( r );
	}

}
