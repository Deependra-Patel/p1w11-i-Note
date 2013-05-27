package com.example.random;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.random.DrawView;

public class MainActivity extends Activity implements OnClickListener {

	// Button bSave;
	static int size1;
	static String Pcolor;
	TextToSpeech tts;
	String data="Welcome to our Project.";
	String Bcolor;
	DrawView drawView;
	Button mSpeak,mSave,mEnter;

	// public static String color1;
	// LinearLayout linearLayout = (LinearLayout) findViewById(R.id.parent);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("main");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Bundle gotBasket = getIntent().getExtras();

		size1 = gotBasket.getInt("size");
		Bcolor = gotBasket.getString("Bcolor");
		Pcolor = gotBasket.getString("Pcolor");
		System.out.println(size1);
		// color1=gotBasket.getString("color");
		System.out.println(Bcolor);
		System.out.println(Pcolor);
		drawView = new DrawView(this);

		// drawView = (DrawView) findViewById(R.id.drawView1);
		// drawView.setBackgroundColor(Color.GREEN);

		drawView = (DrawView) findViewById(R.id.drawView1);
		drawView.setBackgroundColor(Color.parseColor(Bcolor));
		drawView.setDrawingCacheBackgroundColor(Color.parseColor(Pcolor));
		drawView.requestFocus();
		
		mSpeak=(Button)findViewById(R.id.bSpeak);
		mSave=(Button)findViewById(R.id.bSave);
		mEnter=(Button)findViewById(R.id.bEnter);
		tts = new TextToSpeech(MainActivity.this,
				new TextToSpeech.OnInitListener() {

					@Override
					public void onInit(int status) {
						// TODO Auto-generated method stub
						if (status != TextToSpeech.ERROR)
							tts.setLanguage(Locale.UK);

					}

				});
		mSpeak.setOnClickListener(this);
		mSave.setOnClickListener(this);
		mEnter.setOnClickListener(this);
		

	}

	public static String get_Pcolor() {
		return Pcolor;
	}

	public static int get_size() {
		return size1;
	}

	private void setUpVariables() {

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) 
		{
		case R.id.bSpeak:
			System.out.println("speak clicked");
			tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);
			break;
		case R.id.bSave:
			System.out.println("save clicked");
			drawView.setDrawingCacheEnabled(true);
			Bitmap b = drawView.getDrawingCache();
			String root = Environment.getExternalStorageDirectory().toString();
			File myDir = new File(root + "/saved_images");
			myDir.mkdirs();
			Random generator = new Random();
			int n = 10000;
			n = generator.nextInt(n);
			String fname = "Image-" + n + ".jpg";
			File file = new File(myDir, fname);
			if (file.exists())
				file.delete();
			try {
				FileOutputStream out = new FileOutputStream(file);
				b.compress(Bitmap.CompressFormat.JPEG, 90, out);
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case R.id.bEnter:
			break;
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(tts!= null)
			{
			tts.stop();
			tts.shutdown();
			}
		
		super.onPause();
	}

}
