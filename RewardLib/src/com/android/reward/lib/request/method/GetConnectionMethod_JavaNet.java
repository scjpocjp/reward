package com.android.reward.lib.request.method;


import java.io.IOException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;

import android.os.Bundle;

import com.android.reward.lib.util.Constants;
import com.android.reward.lib.util.PreferenceManagerUtil;
import com.android.reward.lib.util.Print;

public class GetConnectionMethod_JavaNet {


	private String url;
	private List < NameValuePair > paramNameValuePair; 

	private int STATUS_CODE	=	-1;

	private HttpURLConnection urlConnection;


	public GetConnectionMethod_JavaNet ( String url, List< Header > header, String authenticator, List < NameValuePair > paramNameValuePair ) {

		String locale = Constants.appInstance.getResources().getConfiguration().locale.getLanguage();
		Constants.LANGUAGE	=	locale;

		PreferenceManagerUtil preferenceManagerUtil = new PreferenceManagerUtil();
		preferenceManagerUtil.set( "locale" , Constants.LANGUAGE );

		this.url = url;
		this.paramNameValuePair = paramNameValuePair;
	}


	public GetConnectionMethod_JavaNet ( String url, List< Header > header, String authenticator, List < NameValuePair > paramNameValuePair,String fromFragment, Bundle refreshData  ) {

		String locale = Constants.appInstance.getResources().getConfiguration().locale.getLanguage();

		Constants.LANGUAGE	=	locale;

		PreferenceManagerUtil preferenceManagerUtil = new PreferenceManagerUtil();
		preferenceManagerUtil.set( "locale" , Constants.LANGUAGE );

		this.url = url;
		this.paramNameValuePair = paramNameValuePair;
	}



	public GetConnectionMethod_JavaNet ( String url, List< Header > header, String authenticator, List < NameValuePair > paramNameValuePair,String fromFragment, int parseType ) {

		String locale = Constants.appInstance.getResources().getConfiguration().locale.getLanguage();
		Constants.LANGUAGE	=	locale;

		PreferenceManagerUtil preferenceManagerUtil = new PreferenceManagerUtil();
		preferenceManagerUtil.set( "locale" , Constants.LANGUAGE );

		this.url = url;
		this.paramNameValuePair = paramNameValuePair;
	}

	/**
	 * setting the headers 
	 */
	private void setHeaders () {

		//		urlConnection.addRequestProperty(  Constants.API_VERSION_KEY, Constants.API_VERSION );



	}

	/**
	 * adding params at the end of the url  
	 */
	private String formUrlRequest () {
		String urlStr = "";
		if ( paramNameValuePair != null && paramNameValuePair.size() > 0 ) {
			urlStr = "?";
			int size = paramNameValuePair.size();
			for ( int i = 0; i < size; i++ ) {
				NameValuePair nameValPair = paramNameValuePair.get( i );
				urlStr += nameValPair.getName() + "=" + nameValPair.getValue()  + "&";
				nameValPair = null;
			}
			urlStr = urlStr.substring( 0 , urlStr.length() - 1);
			paramNameValuePair = null;
		}
		return urlStr;
	}


	/**
	 *  getting the headers, etags , cache time and response data 
	 */
	private InputStream processData ( Map < String, List < String > > map, InputStream in ) {

		InputStream inputStream = in;


		if ( map != null && map.containsKey( "content-encoding" ) ) {

			List<String> contentEncoding = map.get( "content-encoding" );

			if (contentEncoding != null && contentEncoding.get(0).equalsIgnoreCase("gzip")) {
				try {
					if ( inputStream != null ) {
						inputStream  = new GZIPInputStream  ( in );
					}

				} catch ( Exception e ) {

				}
			}	
		}

		return inputStream;
	}


	public Object send ( int returnType ) {

		URL url = null;
		try {

			url = new URL(this.url + formUrlRequest () );

			if ( urlConnection == null ) {
				urlConnection = (HttpURLConnection) url.openConnection();//Without Proxy
			}


			setHeaders();

			InputStream inputStream	= null;





			try {
				STATUS_CODE	=	urlConnection.getResponseCode();
			} catch (Exception e) {
				if (e.getMessage().contains("authentication challenge")) {
					STATUS_CODE = HttpURLConnection.HTTP_UNAUTHORIZED;

				} 
			}




			//Check for the status , if 401 server has returned MAC credentials 



			if(STATUS_CODE == 401 || STATUS_CODE == 403){


				inputStream = urlConnection.getErrorStream();

			}else{
				inputStream	=	urlConnection.getInputStream();
			}



			inputStream = processData( urlConnection.getHeaderFields(), inputStream );



			switch ( returnType ) {

			case Constants.TYPE_BYTE : 		return	null;
			case Constants.TYPE_STREAM:		return	null;
			case Constants.TYPE_STRINGBUFFER : 	





				if ( inputStream != null) {
					/*BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream),8 * 1024);
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) {

						sb.append(line + "\n");
						}*/

					StringBuilder sb = new StringBuilder();
					byte[] b = new byte[8192];
					String test;
					for (int i; (i = inputStream.read(b)) != -1;) {
						test	=new String(b, 0, i);	
						sb.append(test);
					}



					inputStream.close();
					inputStream = null;

					/*reader.close();
					reader = null;
//					 */


					//					 





					Print.getInstance().show( " SB============================================"+ sb );



					return	new StringBuffer ( sb );




				}
			}

			return null;
		} catch ( UnknownHostException e ) {
			STATUS_CODE = 001;
			Print.getInstance().show( e );

		} catch (IOException e) {
			Print.getInstance().show( e );
			STATUS_CODE = 001;


		} catch ( Exception e ) {
			Print.getInstance().show( e );
		}catch (Error e) {
			Print.getInstance().show( e );
		}

		finally {

			try {
				// release the memory
				if(urlConnection!=null) {


					urlConnection.disconnect();
					urlConnection = null;
				}

				url = null;
			}catch (Exception e) {
				//Logs.show( e );
			}
		}

		return null;
	}


	public int getStateCode(){

		return STATUS_CODE;
	}


}


