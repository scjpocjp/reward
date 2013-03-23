package com.android.reward.lib.request.method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.NameValuePair;

import com.android.reward.lib.util.Print;

public class PostConnectionMethod_JaveNet {

	private String url;

	private String requestData;
	private int STATUS_CODE = -1;

	private List < NameValuePair > headerNameValuePair;
	private List < NameValuePair > paramNameValuePair;




	public PostConnectionMethod_JaveNet(String url, List < NameValuePair > headerNameValuePair, List < NameValuePair > paramNameValuePair  ) {

		this.url = url;
		this.headerNameValuePair = headerNameValuePair;
		this.paramNameValuePair = paramNameValuePair;
	}


	/**
	 * setting the headers 
	 */
	private void setHeaders (URLConnection urlConnection) {

		if ( headerNameValuePair != null && headerNameValuePair.size() > 0 ) {
			int len = headerNameValuePair.size();
			for ( int i = 0; i < len; i++ ) {
				NameValuePair nameValuePair = headerNameValuePair.get( i );
				urlConnection.addRequestProperty(  nameValuePair.getName(), nameValuePair.getValue() );
			}
		}


	}



	private InputStream processData ( Map < String, List < String > > map, InputStream in ) {

		InputStream inputStream = in;

		if ( map != null && map.containsKey( "content-encoding" ) ) {

			List<String> contentEncoding = map.get( "content-encoding" );

			if (contentEncoding != null && contentEncoding.get(0).equalsIgnoreCase("gzip")) {
				try {	
					if ( inputStream != null) {
						inputStream  = new GZIPInputStream  ( in );
					}

				} catch ( Exception e ) {
				}
			}	
		}


		return inputStream;
	}

	public Object send() {


		HttpURLConnection urlConnection = null;

		URL url  = null;
		try{
			url = new URL ( this.url );

			if ( urlConnection == null ) {
				urlConnection = (HttpURLConnection) url.openConnection();//Without Proxy
			}

			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");

			setHeaders(urlConnection);
			
			requestData = formUrlRequest();
				
			Print.getInstance().show( "requestData ---------------------------------"+ requestData );
			
			urlConnection.setRequestProperty("Content-Length",""+requestData.toString().getBytes().length);
			urlConnection.getOutputStream().write(requestData.toString().getBytes());


			InputStream inputStream = null;


			try {
				STATUS_CODE	=	urlConnection.getResponseCode();
			} catch (Exception e) {
				Print.getInstance().show(e);

				if (e.getMessage().contains("authentication challenge")) {
					STATUS_CODE = HttpURLConnection.HTTP_UNAUTHORIZED;

				} 
			}





			if(STATUS_CODE == 401){

				inputStream = urlConnection.getErrorStream();



			}else{

				inputStream = urlConnection.getInputStream();
			}


			inputStream = processData( urlConnection.getHeaderFields(), inputStream );




			if (  inputStream != null ) {
				BufferedReader reader = new BufferedReader(new InputStreamReader( inputStream ),8 * 1024 );
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ( ( line = reader.readLine())  != null ) {
					sb.append(line + "\n");
				}
				reader.close();
				reader = null;
				inputStream.close();
				inputStream = null;

				Print.getInstance().show( "SBB ========================================="+ sb .toString() );

				return new StringBuffer(sb.toString());
			}

		} catch ( UnknownHostException e ) {
			Print.getInstance().show(e);
		} catch (IOException e) {
			Print.getInstance().show(e);
		} catch ( Exception e ) {
			Print.getInstance().show(e);
		} finally {
			try{
				// release the memory
				if(urlConnection!=null) {

					try {
						STATUS_CODE	=	urlConnection.getResponseCode();
					} catch (Exception e) {
						if (e.getMessage().contains("authentication challenge")) {
							STATUS_CODE = HttpURLConnection.HTTP_UNAUTHORIZED;

						} 
					}
					urlConnection.disconnect();
					urlConnection = null;
				}

				url = null;
			} catch ( Exception e ) {
				Print.getInstance().show(e);
			}
		}
		return null;
	}
	
	
	/**
	 * adding params at the end of the url  
	 */
	private String formUrlRequest () {
		String urlStr = "";
		if ( paramNameValuePair != null && paramNameValuePair.size() > 0 ) {
			urlStr = "";
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




	public int getStateCode() {
		return STATUS_CODE;
	}

}
