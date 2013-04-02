package com.android.reward.customviews;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.android.reward.R;

public class SearchView extends View implements OnClickListener{
	String searchText = null;
	public SearchView(Context context,AttributeSet attrs) {
        super(context, attrs);
	
		
		TypedArray attributes = context.obtainStyledAttributes(attrs,
		        R.styleable.Search, 0, 0);
		
		String searchLabel = attributes.getString(R.styleable.Search_searchLabel);
		 searchText = attributes.getString(R.styleable.Search_searchEditText);
		
	}
	@Override
	public void onClick(View v) {
		System.out.println("================"+searchText);
		
	}
	
	

}
