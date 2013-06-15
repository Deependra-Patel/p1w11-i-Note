package com.example.random;
import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawView extends View implements OnTouchListener {
	private Bitmap  mBitmap;
	private Canvas  mCanvas;
	private Path    mPath;
	private Paint   mBitmapPaint;
	private Paint   mPaint;
	private PathMeasure mPM;
	MainActivity ma=new MainActivity();
	int size=MainActivity.get_size();
	String Pcolor1=MainActivity.get_Pcolor();
	//String color=MainActivity.color1;
	float aCoordinates[] = {1f, 1f};

	public DrawView(Context c) {
	    super(c);
	   // size=size1;
	   // color=color1;
	    mPath = new Path();
	    mPM = new PathMeasure(mPath, false);
	    mBitmapPaint = new Paint(Paint.DITHER_FLAG);
	    mPaint = new Paint();
	    mPaint.setAntiAlias(true);
	    mPaint.setDither(true);
	    System.out.println("Pcolor1"+Pcolor1);
	    mPaint.setColor(Color.parseColor(Pcolor1));
	    mPaint.setStyle(Paint.Style.STROKE);
	    mPaint.setStrokeJoin(Paint.Join.ROUND);
	    mPaint.setStrokeCap(Paint.Cap.ROUND);
	    mPaint.setStrokeWidth(size);
	}
	
	public DrawView(Context context, AttributeSet attrs) {
		 super(context, attrs);
		//color=color1;
		//size=size1;
      
        // TODO Auto-generated constructor stub
        mPath = new Path();
        mPM = new PathMeasure(mPath, false);
	    mBitmapPaint = new Paint(Paint.DITHER_FLAG);

	    mPaint = new Paint();
	    mPaint.setAntiAlias(true);
	    mPaint.setDither(true);
	    mPaint.setStyle(Paint.Style.STROKE);
	    mPaint.setStrokeJoin(Paint.Join.ROUND);
	    mPaint.setStrokeCap(Paint.Cap.ROUND);
	    mPaint.setStrokeWidth(size);
	    System.out.println("Pcolor1"+Pcolor1);
	  // mPaint.setColor(Color.parseColor(Pcolor1));
    }
    

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	    super.onSizeChanged(w, h, oldw, oldh);
	    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
	    mCanvas = new Canvas(mBitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
	    canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

	    canvas.drawPath(mPath, mPaint);
	}

	private float mX, mY;
	private float Xarray[]=new float[1000];
	private float Yarray[]=new float[1000];
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_start(float x, float y) {
	    mPath.reset();
	    mPath.moveTo(x, y);
	    mX = x;
	    mY = y;
	}
	private void touch_move(float x, float y) {
	    float dx = Math.abs(x - mX);
	    float dy = Math.abs(y - mY);
	    if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
	        mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
	        mY = y;
	    }
	    mPM = new PathMeasure(mPath, false);
	    //System.out.println(Float.toString(mPM.getLength()));
	}
	private void touch_up() {
	    mPath.lineTo(mX, mY);
	    // commit the path to our offscreen
	    mCanvas.drawPath(mPath, mPaint);
	    mPM = new PathMeasure(mPath, false);
	   // System.out.println(Float.toString(mPM.getLength()));
	    mPM.getPosTan(mPM.getLength() * 0.5f, aCoordinates, null);
	    // kill this so we don't double draw
	    mPath.reset();
	    //System.out.println(Float.toString(aCoordinates[0]));
	   // mPaint.setColor(Color.RED);
	   // mCanvas.drawCircle(aCoordinates[0], aCoordinates[1], 5, mPaint);
	    mPaint.setColor(Color.BLACK);
	}
	private void showMidpoint()
	{	
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    float x = event.getX();
	    float y = event.getY();

	    switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	            touch_start(x, y);
	            invalidate();
	            break;
	        case MotionEvent.ACTION_MOVE:
	            touch_move(x, y);
	            invalidate();
	            break;
	        case MotionEvent.ACTION_UP:
	            touch_up();
	            invalidate();
	            break;
	    }
	    return true;
	}

	public void clear(){
	    mBitmap.eraseColor(Color.TRANSPARENT);
	    invalidate();
	    System.gc();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
	    float y = event.getY();

	    switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	            touch_start(x, y);
	            invalidate();
	            break;
	        case MotionEvent.ACTION_MOVE:
	            touch_move(x, y);
	            invalidate();
	            break;
	        case MotionEvent.ACTION_UP:
	            touch_up();
	            invalidate();
	            break;
	    }
	    return true;
	}
}


