package com.android.reward.lib.parser;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.android.reward.lib.util.Print;

public class SAXParsing {

	public static final int LOGIN_PARSER = 0;
	public static final int REGISTER_PARSER = LOGIN_PARSER + 1;
	public static final int FORGOT_PASSWORD_PARSER = REGISTER_PARSER + 1;
	public static final int BANNER_PARSER = FORGOT_PASSWORD_PARSER + 1;
	public static final int ACC_POINTS_PARSER = BANNER_PARSER + 1;
	public static final int HIS_ORDER_PARSER = ACC_POINTS_PARSER + 1;
	public static final int ORDER_DETAIL_PARSER = HIS_ORDER_PARSER + 1;
	public static final int HIS_POINTS_PARSER = ORDER_DETAIL_PARSER + 1;
	public static final int SHIPPING_ADDR_PARSER = HIS_POINTS_PARSER + 1;
	public static final int BURN_PARSER = SHIPPING_ADDR_PARSER + 1;
	public static final int EARN_PARSER = BURN_PARSER + 1;
	public static final int SHOPPING_CAT_PARSER = EARN_PARSER + 1;
	public static final int PRODUCT_PARSER = SHOPPING_CAT_PARSER + 1;
	public static final int PRODUCT_SEARCH_PARSER = PRODUCT_PARSER + 1;
	public static final int CHANGE_PASSWORD_PARSER = PRODUCT_SEARCH_PARSER + 1;



	private SAXParser saxParser;
	private BaseParser baseParser;



	public SAXParsing ( Object result, int type ) {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			saxParser = factory.newSAXParser();
			InputSource is = new InputSource();

			switch ( type ) {
			case LOGIN_PARSER : baseParser = new LoginParser(); 
			is.setCharacterStream(new StringReader( (( StringBuffer ) result).toString() ) );
			saxParser.parse( is, ( LoginParser ) baseParser );
			break;

			case REGISTER_PARSER : baseParser = new BaseParser(); 
			is.setCharacterStream(new StringReader( (( StringBuffer ) result).toString() ) );
			saxParser.parse( is, baseParser );
			break;

			case FORGOT_PASSWORD_PARSER : baseParser = new BaseParser(); 
			is.setCharacterStream(new StringReader( (( StringBuffer ) result).toString() ) );
			saxParser.parse( is, baseParser );
			break;
			

			case BANNER_PARSER : baseParser = new BannerParser(); 
			is.setCharacterStream(new StringReader( (( StringBuffer ) result).toString() ) );
			saxParser.parse( is, ( BannerParser ) baseParser );

			case CHANGE_PASSWORD_PARSER : baseParser = new ChangePasswordParser(); 
			is.setCharacterStream(new StringReader( (( StringBuffer ) result).toString() ) );
			saxParser.parse( is, ( ChangePasswordParser ) baseParser );

			break;

			}

		} catch (ParserConfigurationException e) {
			Print.getInstance().show( e );
		} catch (SAXException e) {
			Print.getInstance().show( e );
		} catch (IOException e) {
			Print.getInstance().show( e );
		}
	}

	public int getStatus () {
		if ( baseParser != null ) {
			return ( baseParser.isSuccess () )? 1 : -1;
		} else {
			return -1;
		}
	}

	public String getReason () {
		if ( baseParser != null ) {
			return baseParser.getReason();
		} else {
			return null;
		}
	}
}
