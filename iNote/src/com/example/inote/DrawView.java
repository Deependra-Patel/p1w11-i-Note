package com.example.inote;
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
import android.widget.EditText;

public class DrawView extends View implements OnTouchListener{
	private Bitmap  mBitmap;
	private Canvas  mCanvas;
	private Path    mPath;
	private Paint   mBitmapPaint;
	private Paint   mPaint;
	private PathMeasure mPM;
	Point[] currentPoints;
	float aCoordinates[] = {1f, 1f};
	EditText showPoints;
	int answer;
	Recognizer recognizer;
	Point[] lastBox = new Point[2];
	String oneLetter = "", wholeString = "", oneWord = "";

	public DrawView(Context c) {
	    super(c);

	    mPath = new Path();
	    mPM = new PathMeasure(mPath, false);
	    mBitmapPaint = new Paint(Paint.DITHER_FLAG);

	    //showPoints = (TextView) findViewById.(R.id.editText1);
	    mPaint = new Paint();
	    mPaint.setAntiAlias(true);
	    mPaint.setDither(true);
	    mPaint.setColor(0xFF000000);
	    mPaint.setStyle(Paint.Style.STROKE);
	    mPaint.setStrokeJoin(Paint.Join.ROUND);
	    mPaint.setStrokeCap(Paint.Cap.ROUND);
	    mPaint.setStrokeWidth(3);
	}
	public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mPath = new Path();
        mPM = new PathMeasure(mPath, false);
	    mBitmapPaint = new Paint(Paint.DITHER_FLAG);

	    //showPoints = (TextView) findViewById.(R.id.editText1);
	    
	    mPaint = new Paint();
	    mPaint.setAntiAlias(true);
	    mPaint.setDither(true);
	    mPaint.setColor(0xFF000000);
	    mPaint.setStyle(Paint.Style.STROKE);
	    mPaint.setStrokeJoin(Paint.Join.ROUND);
	    mPaint.setStrokeCap(Paint.Cap.ROUND);
	    mPaint.setStrokeWidth(3);
	    recognizer = new Recognizer(context);
	    lastBox[0] = new Point(0, 0);
	    lastBox[1] = new Point(0, 0);
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
	private static final float TOUCH_TOLERANCE = 0;

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
	}
	
	
	    
	
	private void touch_up() {
	    mPath.lineTo(mX, mY);
	    // commit the path to our offscreen
	    mCanvas.drawPath(mPath, mPaint);
	    currentPoints = getPoints(20);
	    float minx = getBox(currentPoints)[0].x;
	    boolean p = lastBox[1].x + 1 < minx;
	    if (lastBox[1].x + 1 < minx)
	    {
	    	recognizer.noOfInputs = 0;
	    }
	    lastBox = getBox(currentPoints);
	    recognizer.COM[recognizer.noOfInputs] = getCOM(currentPoints);
	    recognizer.factors[recognizer.noOfInputs] = standardise(currentPoints);
	    answer = recognizer.recognize(currentPoints) + 1;
	    recognizer.inputs[recognizer.noOfInputs] = answer;
	    System.out.println("touchup ended.");
	    recognizer.noOfInputs = recognizer.noOfInputs + 1;
	    if (p) oneWord = oneWord.concat(oneLetter);
	    oneLetter = recognizer.recognizeLetter();
	    System.out.println("oneLetter printed: " + oneLetter);
	    System.out.println(recognizer.inputs[0]);
	    System.out.println("oneWord :" + oneWord);
	    mPath.reset();
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
	public Point[] getPoints(int n){
		//showPoints = (EditText) findViewById.(R.id.editText1);
	    Point[] points = new Point[200];
		mPM = new PathMeasure(mPath, false);
		for(int i = 0; i<n; i++)
		{
			mPM.getPosTan(mPM.getLength() * i/n, aCoordinates, null);
			Point curr = new Point(aCoordinates[0], aCoordinates[1]);
			points[i] = curr;
		}
		return points;
	}
	
	public float standardise(Point[] input){
		float max_x=(float) 0.0,min_x=(float) 100000.0,max_y=(float) 0.0,min_y=(float) 100000.0,factor;
		Point current;
		for(int i=0;i<20;i++)
		{
			current=input[i];
			if(current.x>max_x)
				max_x=current.x;
			if(current.y>max_y)
				max_y=current.y;	
			
			if(current.x<min_x)
				min_x=current.x;
			if(current.y<min_y)
				min_y=current.y;
		}
		if((max_x-min_x)>(max_y-min_y))
			factor=100/(max_x-min_x);
		else
			factor=100/(max_y-min_y);
		for(int i=0;i<20;i++)
		{
			input[i]= new Point(factor*(input[i].x-min_x),factor*(input[i].y-min_y));
		}
		return factor;
	}
	
	
	
		
	
	
	public void pointToString(Point[] p)
	{
		String answer = "";
		for (int i = 0; i < 20; i++)
		{
			answer.concat(Float.toString(p[i].x));
			System.out.println(Float.toString(p[i].x));
			//answer.concat("\n");
			answer.concat(Float.toString(p[i].y));
			System.out.println(Float.toString(p[i].y));
			//answer.concat("\n");			
		}
		//return answer;
	}
	
	public Point getCOM(Point[] p)
	{
		float x0= 0, y0 = 0;
		for (int i = 0; i < 20; i++){
			x0 = x0 + p[i].x;
			y0 = y0 + p[i].y;
		}
		return new Point(x0/20, y0/20);
	}
	
	public Point[] getBox(Point[] p)
	{
		Point min = new Point(10000, 0), max = new Point(-1000, 0);
		Point[] p0 = new Point[2];
		for (int i = 0; i < 20; i++)
		{
			if (p[i].x < min.x){
				min.x = p[i].x;
				min.y = p[i].y;
			}
			else if (p[i].x > max.x){
				max.x = p[i].x;
				max.y = p[i].y;
			}
		}
		p0[0] = min;
		p0[1] = max;
		return p0;
	}

}


