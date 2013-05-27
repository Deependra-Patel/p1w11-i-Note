package com.example.random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help extends Activity{
	Button mEnter;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		mEnter=(Button)findViewById(R.id.bEnter);
		mEnter.setOnClickListener(
				new View.OnClickListener() {
					
					@Override
					public void onClick(View view) {
						// TODO Auto-generated method stub
					Intent startCanvas = new Intent(Help.this, Menu.class);
					finish();
					startActivity(startCanvas);
					
					}
				});
	}
}
