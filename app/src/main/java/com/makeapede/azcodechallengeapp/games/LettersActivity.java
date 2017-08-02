package com.makeapede.azcodechallengeapp.games;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.makeapede.azcodechallengeapp.R;

import java.io.IOException;
import java.util.Random;

public class LettersActivity extends AppCompatActivity {

	int winValue;
	int winButton;
	boolean[] taken = new boolean[26];

	String filenametaptheletter;
	String filenamea;
	String filenameb;
	String filenamec;
	String filenamed;
	String filenamee;
	String filenamef;
	String filenameg;
	String filenameh;
	String filenamei;
	String filenamej;
	String filenamek;
	String filenamel;
	String filenamem;
	String filenamen;
	String filenameo;
	String filenamep;
	String filenameq;
	String filenamer;
	String filenames;
	String filenamet;
	String filenameu;
	String filenamev;
	String filenamew;
	String filenamex;
	String filenamey;
	String filenamez;
	String filenameCorrect;
	String filenameTryAgain;

	Button button1;
	Button button2;
	Button button3;
	Button button4;

	char[] letters = new char[26];

	public void playAudio(String firstFile) {
		MediaPlayer mp = new MediaPlayer();
		try {
			try {
				mp.setDataSource(LettersActivity.this, Uri.parse(firstFile));
			} catch (IOException e) {
				e.printStackTrace();
			}

			mp.prepareAsync();

			mp.setOnPreparedListener(p -> mp.start());

			mp.setOnCompletionListener(p1 -> mp.release());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playAudio(String firstFile, String secondFile, String thirdFile) {
		MediaPlayer mp = new MediaPlayer();
		MediaPlayer mp2 = new MediaPlayer();
		MediaPlayer mp3 = new MediaPlayer();

		try {
			try {
				mp.setDataSource(LettersActivity.this, Uri.parse(firstFile));
				mp2.setDataSource(LettersActivity.this, Uri.parse(secondFile));
				mp3.setDataSource(LettersActivity.this, Uri.parse(thirdFile));
			} catch (IOException e) {
				e.printStackTrace();
			}

			mp.prepareAsync();
			mp2.prepareAsync();
			mp3.prepareAsync();

			mp.setOnPreparedListener(p -> mp.start());

			mp2.setOnPreparedListener(p -> mp.setOnCompletionListener(p1 -> {
				mp2.start();
				mp.release();
			}));

			mp3.setOnPreparedListener(p -> mp2.setOnCompletionListener(p1 -> {
				mp3.start();
				mp2.release();
			}));

			mp3.setOnCompletionListener(p1 -> mp3.release());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playAudio(String firstFile, String secondFile) {
		MediaPlayer mp = new MediaPlayer();
		MediaPlayer mp2 = new MediaPlayer();

		try {
			try {
				mp.setDataSource(LettersActivity.this, Uri.parse(firstFile));
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				mp2.setDataSource(LettersActivity.this, Uri.parse(secondFile));
			} catch (IOException e) {
				e.printStackTrace();
			}

			mp.prepareAsync();
			mp2.prepareAsync();

			mp.setOnPreparedListener(mp1 -> mp.start());

			mp2.setOnCompletionListener(mp1 -> mp2.release());

			mp2.setOnPreparedListener(mp1 -> mp.setOnCompletionListener(mp3 -> {
				mp2.start();
				mp.release();
			}));


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int randInt(int max, int min) {
		Random rand = new Random();

		return rand.nextInt(max-min +1) + min;
	}
	void clearTaken() {
		for (int i = 0; i< taken.length; i++){
			taken[i] = false;
		}
	}

	void cycleGame(boolean winner) {
		if(winner) {
			//play correct!
			//playAudio(filenameCorrect);
			//toast correct!
			Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT).show();

			button1.setTag(false);
			button2.setTag(false);
			button3.setTag(false);
			button4.setTag(false);
			clearTaken();

			winValue = randInt(25,0);
			String templetter  = filenamea;;
			switch (winValue){
				case 0:
					templetter = filenamea;
					break;
				case 1:
					templetter = filenameb;
					break;
				case 2:
					templetter = filenamec;
					break;
				case 3:
					templetter = filenamed;
					break;
				case 4:
					templetter = filenamee;
					break;
				case 5:
					templetter = filenamef;
					break;
				case 6:
					templetter = filenameg;
					break;
				case 7:
					templetter = filenameh;
					break;
				case 8:
					templetter = filenamei;
					break;
				case 9:
					templetter = filenamej;
					break;
				case 10:
					templetter = filenamek;
					break;
				case 11:
					templetter = filenamel;
					break;
				case 12:
					templetter = filenamem;
					break;
				case 13:
					templetter = filenamen;
					break;
				case 14:
					templetter = filenameo;
					break;
				case 15:
					templetter = filenamep;
					break;
				case 16:
					templetter = filenameq;
					break;
				case 17:
					templetter = filenamer;
					break;
				case 18:
					templetter = filenames;
					break;
				case 19:
					templetter = filenamet;
					break;
				case 20:
					templetter = filenameu;
					break;
				case 21:
					templetter = filenamev;
					break;
				case 22:
					templetter = filenamew;
					break;
				case 23:
					templetter = filenamex;
					break;
				case 24:
					templetter = filenamey;
					break;
				case 25:
					templetter = filenamez;
					break;
			}

			playAudio(filenameCorrect, filenametaptheletter, templetter);
			taken[winValue] = true;
			winButton = randInt(4, 1);
			Log.d("LettersActivity", String.valueOf(winButton));
			switch (winButton){
				case 1:
					button1.setText(String.valueOf(letters[winValue]));
					button1.setTag(true);
					button2.setTag(false);
					button3.setTag(false);
					button4.setTag(false);
					break;
				case 2:
					button2.setText(String.valueOf(letters[winValue]));
					button2.setTag(true);
					button1.setTag(false);
					button3.setTag(false);
					button4.setTag(false);
					break;
				case 3:
					button3.setText(String.valueOf(letters[winValue]));
					button3.setTag(true);
					button1.setTag(false);
					button2.setTag(false);
					button4.setTag(false);
					break;
				case 4:
					button4.setText(String.valueOf(letters[winValue]));
					button4.setTag(true);
					button1.setTag(false);
					button2.setTag(false);
					button3.setTag(false);
					break;
			}

			if(!(button1.getTag().equals(true))){
				int backgroundValue = randInt(25, 0);
				while(taken[backgroundValue]){
					backgroundValue = randInt(25, 0);
					Log.d("ColorsActivity", "button1");
					Log.d("ColorsActivity", String.valueOf(backgroundValue));
				}
				button1.setText(String.valueOf(letters[backgroundValue]));
				taken[backgroundValue] = true;
			}
			if(!(button2.getTag().equals(true))){
				int backgroundValue = randInt(25, 0);
				while(taken[backgroundValue]){
					backgroundValue = randInt(25, 0);
					Log.d("ColorsActivity", "button2");
					Log.d("ColorsActivity", String.valueOf(backgroundValue));
				}
				button2.setText(String.valueOf(letters[backgroundValue]));
				taken[backgroundValue] = true;
			}
			if(!(button3.getTag().equals(true))){
				int backgroundValue = randInt(25, 0);
				while(taken[backgroundValue]){
					backgroundValue = randInt(25, 0);
					Log.d("ColorsActivity", "button3");
					Log.d("ColorsActivity", String.valueOf(backgroundValue));
				}
				button3.setText(String.valueOf(letters[backgroundValue]));
				taken[backgroundValue] = true;
			}
			if(!(button4.getTag().equals(true))){
				int backgroundValue = randInt(25, 0);
				while(taken[backgroundValue]){
					backgroundValue = randInt(25, 0);
					Log.d("ColorsActivity", "button4");
					Log.d("ColorsActivity", String.valueOf(backgroundValue));
				}
				button4.setText(String.valueOf(letters[backgroundValue]));
				taken[backgroundValue] = true;
			}

		} else {
			Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
			playAudio(filenameTryAgain);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_letters);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		filenamea = "android.resource://" + this.getPackageName() + "/raw/a";
		filenameb = "android.resource://" + this.getPackageName() + "/raw/b";
		filenamec = "android.resource://" + this.getPackageName() + "/raw/c";
		filenamed = "android.resource://" + this.getPackageName() + "/raw/d";
		filenamee = "android.resource://" + this.getPackageName() + "/raw/e";
		filenamef = "android.resource://" + this.getPackageName() + "/raw/f";
		filenameg = "android.resource://" + this.getPackageName() + "/raw/g";
		filenameh = "android.resource://" + this.getPackageName() + "/raw/h";
		filenamei = "android.resource://" + this.getPackageName() + "/raw/i";
		filenamej = "android.resource://" + this.getPackageName() + "/raw/j";
		filenamek = "android.resource://" + this.getPackageName() + "/raw/k";
		filenamel = "android.resource://" + this.getPackageName() + "/raw/l";
		filenamem = "android.resource://" + this.getPackageName() + "/raw/m";
		filenamen = "android.resource://" + this.getPackageName() + "/raw/n";
		filenameo = "android.resource://" + this.getPackageName() + "/raw/o";
		filenamep = "android.resource://" + this.getPackageName() + "/raw/p";
		filenameq = "android.resource://" + this.getPackageName() + "/raw/q";
		filenamer = "android.resource://" + this.getPackageName() + "/raw/r";
		filenames = "android.resource://" + this.getPackageName() + "/raw/s";
		filenamet = "android.resource://" + this.getPackageName() + "/raw/t";
		filenameu = "android.resource://" + this.getPackageName() + "/raw/u";
		filenamev = "android.resource://" + this.getPackageName() + "/raw/v";
		filenamew = "android.resource://" + this.getPackageName() + "/raw/w";
		filenamex = "android.resource://" + this.getPackageName() + "/raw/x";
		filenamey = "android.resource://" + this.getPackageName() + "/raw/y";
		filenamez = "android.resource://" + this.getPackageName() + "/raw/z";
		filenametaptheletter = "android.resource://" + this.getPackageName() + "/raw/taptheletter";
		filenameCorrect = "android.resource://" + this.getPackageName() + "/raw/correct";
		filenameTryAgain = "android.resource://" + this.getPackageName() + "/raw/tryagain";

		button1=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		button3=(Button)findViewById(R.id.button3);
		button4=(Button)findViewById(R.id.button4);
		button1.setTag(false);
		button2.setTag(false);
		button3.setTag(false);
		button4.setTag(false);

		letters[0] = 'A';
		letters[1] = 'B';
		letters[2] = 'C';
		letters[3] = 'D';
		letters[4] = 'E';
		letters[5] = 'F';
		letters[6] = 'G';
		letters[7] = 'H';
		letters[8] = 'I';
		letters[9] = 'J';
		letters[10] = 'K';
		letters[11] = 'L';
		letters[12] = 'M';
		letters[13] = 'N';
		letters[14] = 'O';
		letters[15] = 'P';
		letters[16] = 'Q';
		letters[17] = 'R';
		letters[18] = 'S';
		letters[19] = 'T';
		letters[20] = 'U';
		letters[21] = 'V';
		letters[22] = 'W';
		letters[23] = 'X';
		letters[24] = 'Y';
		letters[25] = 'Z';

		winValue = randInt(25,0);
		String templetter  = filenamea;;
		switch (winValue){
			case 0:
				templetter = filenamea;
				break;
			case 1:
				templetter = filenameb;
				break;
			case 2:
				templetter = filenamec;
				break;
			case 3:
				templetter = filenamed;
				break;
			case 4:
				templetter = filenamee;
				break;
			case 5:
				templetter = filenamef;
				break;
			case 6:
				templetter = filenameg;
				break;
			case 7:
				templetter = filenameh;
				break;
			case 8:
				templetter = filenamei;
				break;
			case 9:
				templetter = filenamej;
				break;
			case 10:
				templetter = filenamek;
				break;
			case 11:
				templetter = filenamel;
				break;
			case 12:
				templetter = filenamem;
				break;
			case 13:
				templetter = filenamen;
				break;
			case 14:
				templetter = filenameo;
				break;
			case 15:
				templetter = filenamep;
				break;
			case 16:
				templetter = filenameq;
				break;
			case 17:
				templetter = filenamer;
				break;
			case 18:
				templetter = filenames;
				break;
			case 19:
				templetter = filenamet;
				break;
			case 20:
				templetter = filenameu;
				break;
			case 21:
				templetter = filenamev;
				break;
			case 22:
				templetter = filenamew;
				break;
			case 23:
				templetter = filenamex;
				break;
			case 24:
				templetter = filenamey;
				break;
			case 25:
				templetter = filenamez;
				break;
		}

		playAudio(filenametaptheletter, templetter);
		taken[winValue] = true;
		winButton = randInt(4, 1);
		Log.d("LettersActivity", String.valueOf(winButton));
		switch (winButton){
			case 1:
				button1.setText(String.valueOf(letters[winValue]));
				button1.setTag(true);
				button2.setTag(false);
				button3.setTag(false);
				button4.setTag(false);
				break;
			case 2:
				button2.setText(String.valueOf(letters[winValue]));
				button2.setTag(true);
				button1.setTag(false);
				button3.setTag(false);
				button4.setTag(false);
				break;
			case 3:
				button3.setText(String.valueOf(letters[winValue]));
				button3.setTag(true);
				button1.setTag(false);
				button2.setTag(false);
				button4.setTag(false);
				break;
			case 4:
				button4.setText(String.valueOf(letters[winValue]));
				button4.setTag(true);
				button1.setTag(false);
				button2.setTag(false);
				button3.setTag(false);
				break;
		}

		if(!(button1.getTag().equals(true))){
			int backgroundValue = randInt(25, 0);
			while(taken[backgroundValue]){
				backgroundValue = randInt(25, 0);
				Log.d("ColorsActivity", "button1");
				Log.d("ColorsActivity", String.valueOf(backgroundValue));
			}
			button1.setText(String.valueOf(letters[backgroundValue]));
			taken[backgroundValue] = true;
		}
		if(!(button2.getTag().equals(true))){
			int backgroundValue = randInt(25, 0);
			while(taken[backgroundValue]){
				backgroundValue = randInt(25, 0);
				Log.d("ColorsActivity", "button2");
				Log.d("ColorsActivity", String.valueOf(backgroundValue));
			}
			button2.setText(String.valueOf(letters[backgroundValue]));
			taken[backgroundValue] = true;
		}
		if(!(button3.getTag().equals(true))){
			int backgroundValue = randInt(25, 0);
			while(taken[backgroundValue]){
				backgroundValue = randInt(25, 0);
				Log.d("ColorsActivity", "button3");
				Log.d("ColorsActivity", String.valueOf(backgroundValue));
			}
			button3.setText(String.valueOf(letters[backgroundValue]));
			taken[backgroundValue] = true;
		}
		if(!(button4.getTag().equals(true))){
			int backgroundValue = randInt(25, 0);
			while(taken[backgroundValue]){
				backgroundValue = randInt(25, 0);
				Log.d("ColorsActivity", "button4");
				Log.d("ColorsActivity", String.valueOf(backgroundValue));
			}
			button4.setText(String.valueOf(letters[backgroundValue]));
			taken[backgroundValue] = true;
		}

		button1.setOnClickListener((v) -> {
			if(button1.getTag().equals(true)) {
				cycleGame(true);
			}
			else{
				cycleGame(false);
			}
		});
		button2.setOnClickListener((v) -> {
			if(button2.getTag().equals(true)) {
				cycleGame(true);
			}
			else{
				cycleGame(false);
			}
		});
		button3.setOnClickListener((v) -> {
			if(button3.getTag().equals(true)) {
				cycleGame(true);
			}
			else{
				cycleGame(false);
			}
		});
		button4.setOnClickListener((v) -> {
			if(button4.getTag().equals(true)) {
				cycleGame(true);
			}
			else{
				cycleGame(false);
			}
		});
	}
}
