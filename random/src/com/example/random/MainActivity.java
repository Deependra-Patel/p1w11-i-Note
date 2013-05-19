package com.example.random;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.random.DrawView;

public class MainActivity extends Activity implements OnClickListener{
    DrawView drawView;
    Button bSave;
    //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.parent);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawView = new DrawView(this);
        //drawView = (DrawView) findViewById(R.id.drawView1);
        //drawView.setBackgroundColor(Color.GREEN);
        setContentView(R.layout.activity_main);
        drawView = (DrawView) findViewById(R.id.drawView1);
        drawView.setBackgroundColor(Color.WHITE);
        drawView.requestFocus();

    }
    private void setUpVariables(){
    	
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		drawView.setDrawingCacheEnabled(true);
		Bitmap b = drawView.getDrawingCache();

		FileOutputStream fos = null;
		try {
		fos = new FileOutputStream("/sdcard/test.png");
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}

		b.compress(CompressFormat.PNG, 95, fos);
		
	}
    
}