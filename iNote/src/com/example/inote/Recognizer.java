package com.example.inote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import com.example.inote.Point;

public class Recognizer{
	int noOfChars = 39;
	int noOfInputs = 0;
	Point[] COM = new Point[100];
	float[] factors = new float[100];
	int[] inputs = new int[100];
	Point[][] std = new Point[noOfChars][200];
	String[] characters = new String[]{"|", ")"};
	
	public int recognize(Point[] p){
		int minErrorChar = 0;
		float minimumError = 1000000000, currError;
		for(int i = 0; i < noOfChars; i++){
			currError = compare2(std[i], p);
			if(minimumError > currError){
				minErrorChar = i;
				minimumError = currError;
			}
		}
		return minErrorChar;
	}
	
	public Recognizer(Point[][] p){
		std = p;
	}
	
	public Recognizer(Context context){
		
		InputStream is = context.getResources().openRawResource(R.raw.standard_data);
		BufferedReader r = new BufferedReader(new InputStreamReader(is));
		for(int j = 0; j < noOfChars; j++){
			Point[] p = new Point[200];
			for(int i = 0; i < 200; i++){
				String x;
				try {
					x = r.readLine();
					String y = r.readLine();
					p[i] = new Point(Float.parseFloat(x), Float.parseFloat(y));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e){
					e.printStackTrace();
				}
				
			}
			System.out.println("here1 " + j);
			std[j] = p;
			
		}
	}
	
	private float compareTwoChars(Point[] stdChar, Point[] userChar){
		float error = 0, minError = 1000, currDistance = 100000;
		int lastPoint = -1, t = -1;
		for(int i = 0; i < 20; i=i+1){
			t = lastPoint + 1;
			for(int j = lastPoint + 1; (j < lastPoint + 20) & (j < 200); j++){
				try{
					
				currDistance = stdChar[j].distance(userChar[i]);
				if(currDistance < minError){
					t = j;
					minError = currDistance;
				}
				} catch (NullPointerException e){
					e.printStackTrace();
				}
				
			}
			lastPoint = t;
			error = error + minError;
		}
		
		return error;
	}
	
	private float compare2(Point[] stdChar, Point[] userChar){
		float error = 0, currDistance;
		for(int i = 0; i < 20; i++){
			currDistance = stdChar[i*10].distance(userChar[i]);
			error = error + currDistance;
		}
		return error;
	}
	
	public String recognizeLetter(){
		String letter = "";
		switch (noOfInputs)
		{
		case 1:
			switch (inputs[0])
			{
			case 3:
				letter = " ";
				break;
			case 39:
				letter = "A";
				break;
			case 7:
				letter = "C";
				break;
			case 8:
				letter = "D";
				break;
			case 11:
				letter = "G";
				break;
			case 12:
				letter = "H";
				break;
			case 15:
				letter = "I";
				break;
			case 5:
				letter = "I";
				break;
			case 16:
				letter = "T";
				break;
			case 17:
				letter = "J";
				break;
			case 19:
				letter = "K";
				break;
			case 21:
				letter = "L";
				break;
			case 22:
				letter = "M";
				break;
			case 23:
				letter = "V";
				break;
			case 26:
				letter = "N";
				break;
			case 28:
				letter = "O";
				break;
			case 29:
				letter = "P";
				break;
			case 30:
				letter = "Q";
				break;
			case 31:
				letter = "R";
				break;
			case 33:
				letter = "S";
				break;
			case 34:
				letter = "U";
				break;
			case 35:
				letter = "W";
				break;
			case 36:
				letter = "Y";
				break;
			case 37:
				letter = "Z";
				break;
			
			}
			break;
		case 2:
			if (inputs[0] == 4 & inputs[1] == 3) letter = "A";
			else if (inputs[0] == 9 & inputs[1] == 3) letter = "E";
			else if (inputs[0] == 7 & inputs[1] == 3) letter = "E";
			else if (inputs[0] == 10 & inputs[1] == 3) letter = "F";
			else if (inputs[0] == 13 & inputs[1] == 5) letter = "H";
			else if (inputs[0] == 5 & inputs[1] == 14) letter = "H";
			else if (inputs[0] == 16 & inputs[1] == 3) letter = "I";
			else if (inputs[0] == 5 & inputs[1] == 27) letter = "N";
			else if (inputs[0] == 7 & inputs[1] == 6) letter = "O";
			else if (inputs[0] == 28 & inputs[1] == 2) letter = "Q";
			else if (inputs[0] == 5 & inputs[1] == 32) letter = "R";
			else if (inputs[0] == 3 & inputs[1] == 5) letter = "T";
			else if (inputs[0] == 2 & inputs[1] == 1){
				if(COM[1].x - COM[0].x > 25/factors[0]) letter = "V";
				else letter = "X";
			}
			else if (inputs[0] == 23 & inputs[1] == 5) letter = "Y";
			else if (inputs[0] == 18 & inputs[1] == 3) letter = "J";
			else if (inputs[0] == 5 & inputs[1] == 20) letter = "K";
			else if (inputs[0] == 24 & inputs[1] == 5) letter = "M";
			else if (inputs[0] == 5 & inputs[1] == 25) letter = "M";
			else if (inputs[0] == 5 & inputs[1] == 39) letter = "B";
			else if (inputs[0] == 5 & inputs[1] == 6){
				if (COM[1].y < COM[0].y - 25/(2*factors[0])) letter = "P";
				else letter = "D";
			}
			break;
		case 3:
			if (inputs[0] == 1 & inputs[1] == 2 & inputs[2] == 3) letter = "A";
			else if (inputs[0] == 5 & inputs[1] == 6 & inputs[2] == 6) letter = "B";
			else if (inputs[0] == 5 & inputs[1] == 3 & inputs[2] == 5) letter = "H";
			else if (inputs[0] == 3 & inputs[1] == 5 & inputs[2] == 3) letter = "I";
			else if (inputs[0] == 5 & inputs[1] == 2 & inputs[2] == 5) letter = "N";
			else if (inputs[0] == 2 & inputs[1] == 1 & inputs[2] == 5) letter = "Y";
			else if (inputs[0] == 5 & inputs[1] == 23 & inputs[2] == 5) letter = "M";
			break;
		case 4:
			if (inputs[0] == 5 & inputs[1] == 3 & inputs[2] == 3 & inputs[3] == 3) letter = "E";
			else if (inputs[0] == 2 & inputs[1] == 1 & inputs[2] == 2 & inputs[3] == 1) letter = "W";
			else if (inputs[0] == 5 & inputs[1] == 2 & inputs[2] == 1 & inputs[3] == 5) letter = "M";
			else if (inputs[0] == 5 & inputs[1] == 1 & inputs[2] == 2 & inputs[3] == 5) letter = "W";
			break;
		}
		return letter;
	}
}