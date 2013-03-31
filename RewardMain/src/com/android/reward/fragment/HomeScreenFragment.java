package com.android.reward.fragment;

import com.android.reward.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class HomeScreenFragment extends MainFragment implements OnClickListener{
	EditText etAdvanceSearch;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.home_screen, container, false);
		etAdvanceSearch = (EditText)v.findViewById(R.id.etAdvanceSearch);
		v.findViewById(R.id.btnSignIn).setOnClickListener(this);
		v.findViewById(R.id.btnSignUp).setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {	
		switch (v.getId()) {
		case R.id.btnSignIn: signIn();
			 	  break;
		case R.id.btnSignUp: signUp();
	 	  break;

		default:
			break;
		}
		
	}
	
	/**
	 * Sign-In implementation
	 */
	private void signIn(){
		
	}
	/**
	 * Sign-Up implementation
	 */
	private void signUp(){
		
	}

}
