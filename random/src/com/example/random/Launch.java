package com.example.random;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Launch extends Activity implements OnClickListener,
		OnCheckedChangeListener {
	Button mEnter;
	EditText mSize;
	int size;
	RadioGroup PColor, BColor;
	String Pcolor="#0000FF", Bcolor= "#FFF8C6";

	// String color;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher_main);
		mEnter = (Button) findViewById(R.id.bEnter);
		mSize = (EditText) findViewById(R.id.eSize);
		PColor = (RadioGroup) findViewById(R.id.rGC);
		BColor = (RadioGroup) findViewById(R.id.rGB);
		PColor.setOnCheckedChangeListener(this);
		BColor.setOnCheckedChangeListener(this);
		mEnter.setOnClickListener(this);

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		if (arg0.equals(PColor)) {
			switch (arg1) {
			case R.id.rBlack:
				Pcolor = "#000000";
				break;
			case R.id.rBlue:
				Pcolor = "#0000FF";
				break;
			case R.id.rOrange:
				Pcolor = "#FF00FF";
				break;
			}
		} else {
			switch (arg1) {
			case R.id.bWheat:
				Bcolor = "#FFF8C6";
				break;
			case R.id.bWhite:
				Bcolor = "#FFFFFF";
				break;
			case R.id.bBlack:
				Bcolor = "#000000";
				break;
			}
		}
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		{ 
		   size=Integer.valueOf(mSize.getText().toString());		  
		  Bundle basket=new Bundle(); 
		  basket.putInt("size", size);
		  basket.putString("Pcolor", Pcolor); 
		  basket.putString("Bcolor", Bcolor); 
		  Intent startCanvas = new Intent(Launch.this, MainActivity.class);
		  startCanvas.putExtras(basket); 
		  System.out.println("Launch");
		  System.out.println("Pcolor"+Pcolor);
		  System.out.println("Bcolor"+Bcolor);
		  startActivity(startCanvas);  
		  
		  } 
	}

}
