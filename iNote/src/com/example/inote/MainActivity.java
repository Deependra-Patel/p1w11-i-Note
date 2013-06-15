package com.example.inote;

import java.util.Locale;
import android.app.Activity;
import android.graphics.Color;
//import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inote.DrawView;

public class MainActivity extends Activity implements OnClickListener {

	TextToSpeech tts;
	String data="Welcome to our Project.";
	String Bcolor;
	DrawView drawView;
	Button mSpeak,mSave,mEnter, bkspc, spc;
	TextView answer;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Bundle gotBasket = getIntent().getExtras();
		Bcolor = gotBasket.getString("Bcolor");
		drawView = new DrawView(this);
		drawView = (DrawView) findViewById(R.id.drawView1);
		drawView.setBackgroundColor(Color.parseColor(Bcolor));
		drawView.requestFocus();

		mSave=(Button)findViewById(R.id.bSave);
		bkspc = (Button) findViewById(R.id.bkspc);
		mEnter=(Button)findViewById(R.id.bEnter);
		mSpeak=(Button)findViewById(R.id.bSpeak);
		tts = new TextToSpeech(MainActivity.this,
				new TextToSpeech.OnInitListener() {

					@Override
					public void onInit(int status) {
						// TODO Auto-generated method stub
						if (status != TextToSpeech.ERROR)
							tts.setLanguage(Locale.UK);

					}

				});
		//mSpeak.setOnClickListener(this);
		mSave.setOnClickListener(this);
		mSpeak.setOnClickListener(this);
		mEnter.setOnClickListener(this);
		bkspc.setOnClickListener(this);
		answer = (TextView) findViewById(R.id.answer);

	}


	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) 
		{
		case R.id.bSpeak:
			tts.speak(drawView.wholeString, TextToSpeech.QUEUE_FLUSH, null);//write answer string in place of data
			break;
		/*case R.id.bSave:
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
			break;*/
		case R.id.bEnter:
			drawView.oneWord = drawView.oneWord.concat(drawView.oneLetter);
			drawView.wholeString = drawView.wholeString.concat(drawView.oneWord);
			String ans = drawView.wholeString;
			answer.setText(ans);
			drawView.clear();
			drawView.lastBox[0] = new Point(0,0);
			drawView.lastBox[1] = new Point(0,0);
			drawView.oneWord = "";
			drawView.oneLetter = "";
			break;
		case R.id.bkspc:
			if(drawView.wholeString.length() > 0) drawView.wholeString = drawView.wholeString.substring(0, drawView.wholeString.length() - 1);
			answer.setText(drawView.wholeString);
			drawView.oneWord ="";
			drawView.oneLetter = "";
			drawView.clear();
			break;
		case R.id.bSave:
			Toast t = Toast.makeText(this, "Note has been saved.", Toast.LENGTH_LONG);
			t.show();
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
