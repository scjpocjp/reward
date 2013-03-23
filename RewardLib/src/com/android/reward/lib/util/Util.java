package com.android.reward.lib.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.reward.lib.request.HttpRequest;

public class Util {

	private static List < HttpRequest > list = new ArrayList < HttpRequest > ();

	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


	/**
	 *  Validating email
	 */
	public boolean isValidEmail ( String email ) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher( email );


		return matcher.matches();
	}


	/**
	 * copies the data from inputstream into outputstream
	 * 
	 * @param InputStream
	 *            -- the inputstream from which data needs to be copied.
	 * @param OutputStream
	 *            -- the outputstream to which the data from inputstream needsto
	 *            be copied.
	 * @throws IOException
	 *             -- In case if error occured during copying of streams or
	 *             accessibility.
	 */
	public static void copyStreams(InputStream is, OutputStream out) throws IOException {

		// the byte array with the size of byte which determines how much data
		try {
			// needs to be copied in 1 round.
			byte[] buffer = new byte[1024];

			int length;
			while ((length = is.read(buffer)) > 0) {

				// copying into outputstream
				out.write(buffer, 0, length);
			}


			// closing the output and input streams.
			out.flush();
			out.close();
			is.close();

			out = null;
			is = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//Logs.show(e);
		}

	}

	/**
	 * To check the connection before starting download
	 */
	public static boolean isInternetAvailable() {

		try {
			// getting the instance of connectivity manager
			ConnectivityManager cm = (ConnectivityManager) Constants.appInstance.getSystemService(Context.CONNECTIVITY_SERVICE);

			// checking for active network info
			NetworkInfo networkInfo = cm.getActiveNetworkInfo();
			if (networkInfo == null) {
				return false;
			} else {
				return networkInfo.isConnectedOrConnecting();
			}
		} catch (Exception e) {
			Print.getInstance().show(e);
		}

		return false;
	}
	
	
	public static void removeHttRequest ( HttpRequest obj ) {
		try {
			list.remove( obj );
		} catch ( Exception e) {

		}
	}
	public static List < HttpRequest > getHttpRequestList () {
		return list;
	}

	public static void addHttRequest ( HttpRequest command ) {
		try {
			list.add( command );
		} catch ( Exception e) {

		}
	}
	
	/**
	 * for showing any toast message 
	 */ 
	public static void showToast ( final String toastMessage ) {

		if ( Constants.handler != null ) {

			Constants.handler.post( new Runnable () {

				@Override
				public void run() {

					try {
						Toast.makeText( Constants.appInstance.getBaseContext(), toastMessage, Toast.LENGTH_SHORT ).show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//Logs.show ( e );
					}
				}

			});
		}
	}





}
