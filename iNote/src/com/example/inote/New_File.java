package com.example.inote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class New_File extends Activity implements OnClickListener,
		OnCheckedChangeListener {
	Button mEnter;
	RadioGroup BColor;
	String Bcolor = "#FFF8C6";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher_main);
		mEnter = (Button) findViewById(R.id.bEnter);
		BColor = (RadioGroup) findViewById(R.id.rGB);
		BColor.setOnCheckedChangeListener(this);
		mEnter.setOnClickListener(this);

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		if (arg0.equals(BColor)) {
			switch (arg1) {
			case R.id.bWheat:
				Bcolor = "#FFF8C6";
				break;
			case R.id.bWhite:
				Bcolor = "#FFFFFF";
				break;
			case R.id.bYellow:
				Bcolor = "#ffff00";
				break;
			case R.id.bSlate:
				Bcolor = "#cdd0ae";
				break;
			case R.id.bLace:
				Bcolor = "#FDF5E6";
				break;
			case R.id.bPink:
				Bcolor = "#FF00FF";
				break;

			}
		}
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		{
			Bundle basket = new Bundle();
			basket.putString("Bcolor", Bcolor);
			Intent startCanvas = new Intent(New_File.this, MainActivity.class);
			startCanvas.putExtras(basket);
			startActivity(startCanvas);
			finish();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			finish();
		}

		return super.onKeyDown(keyCode, event);
	}

}
