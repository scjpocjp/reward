package com.android.reward.lib.request.method;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import com.android.reward.lib.database.DatabaseUtil;
import com.android.reward.lib.util.Print;

public class PutConnectionMethod {


	private String url;
	private HttpURLConnection urlConnection;
	private int STATUS_CODE	=	-1;
	private String requestData;

	public PutConnectionMethod ( String url, String requestData  ) {

		this.url = url;
		this.requestData = requestData;
	}



	/**
	 * setting the headers 
	 */
	private void setHeaders () {


		//	urlConnection.addRequestProperty(  Constants.API_VERSION_KEY, Constants.API_VERSION );
	}



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
					//Logs.show(e);
				}
			}	
		}

		return inputStream;
	}

	public Object send() {

		URL url  = null;

		try{
			//This dummy field is to solve the 411 error thrown from server.

			url = new URL(this.url);

			if ( urlConnection == null ) {
				urlConnection = (HttpURLConnection) url.openConnection();//Without Proxy
			}

			urlConnection.setDoOutput(true);

			setHeaders();

			urlConnection.setRequestMethod("PUT");
			if ( requestData == null ) {
				requestData = " Resource content ";
			}
			urlConnection.setRequestProperty("Content-Length",""+requestData.length());
			urlConnection.getOutputStream().write(requestData.getBytes() );

			InputStream inputStream = urlConnection.getInputStream();

			try {
				STATUS_CODE	=	urlConnection.getResponseCode();
			} catch (Exception e) {
				if (e.getMessage().contains("authentication challenge")) {
					STATUS_CODE = HttpURLConnection.HTTP_UNAUTHORIZED;

				} 
			}
			inputStream = processData( urlConnection.getHeaderFields(), inputStream );



			if ( inputStream != null)  {
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream),8 * 1024);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}

				reader.close();
				reader = null;
				inputStream .close();
				inputStream = null;
				return new StringBuffer( sb.toString() );
			}

		} catch ( Exception e ) {
			Print.getInstance().show( e );
		} finally {
			try{
				// release the memory
				if(urlConnection!=null) {
					urlConnection.disconnect();
					urlConnection = null;
				}


				url = null;
			}catch (Exception e) {
				Print.getInstance().show( e );
			}
		}
		return null;
	}


	// getting the status code
	public int getStateCode(){
		return STATUS_CODE;
	}
}
