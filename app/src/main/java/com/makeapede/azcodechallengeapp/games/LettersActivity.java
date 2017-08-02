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
import java.util.ArrayList;
import java.util.Random;

public class LettersActivity extends AppCompatActivity {

	private class ButtonPlus{
		boolean taken = false;
		boolean win = false;
		Button bla;
		ButtonPlus(Button a){
			bla = a;
		}

		void setText(String text){
			bla.setText(text);
		}
		void setWin(boolean a){
			win = a;
		}
		boolean getWin(){
			return win;
		}
		Button getButton(){
			return bla;
		}
	}
	ArrayList<ButtonPlus> buttons = new ArrayList<>();

	int winValue;
	int winButton;
	boolean[] taken = new boolean[26];
	private String filenametaptheletter;
	private String[] filenames = new String[26];
	String filenameCorrect;
	String filenameTryAgain;

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
	void clearWin(){
		for(int i=0; i<4; i++){
			buttons.get(i).setWin(false);
		}
	}

	void cycleGame(boolean winner) {
		if(winner) {
			//play correct!
			//playAudio(filenameCorrect);
			//toast correct!
			Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT).show();

			clearWin();
			clearTaken();

			winValue = randInt(25,0);
			String templetter  = filenames[winValue];;

			playAudio(filenameCorrect, filenametaptheletter, templetter);
			taken[winValue] = true;
			winButton = randInt(4, 1);
			Log.d("LettersActivity", String.valueOf(winButton));
			for(int i = 0; i < 4; i++){
				if((i+1) == winButton) {
					buttons.get(i).setWin(true);
				}
				else{
					buttons.get(i).setWin(false);
				}
			}
			buttons.get(winButton-1).setText(String.valueOf(letters[winValue]));
			for(int i = 0; i < 4; i++){
				if(!buttons.get(i).getWin()){
					int backgroundValue = randInt(25, 0);
					while(taken[backgroundValue]){
						backgroundValue = randInt(25, 0);
						Log.d("ColorsActivity", "button1");
						Log.d("ColorsActivity", String.valueOf(backgroundValue));
					}
					buttons.get(i).setText(String.valueOf(letters[backgroundValue]));
					taken[backgroundValue] = true;
				}
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

		filenames[0] = "android.resource://" + this.getPackageName() + "/raw/a";
		filenames[1] = "android.resource://" + this.getPackageName() + "/raw/b";
		filenames[2] = "android.resource://" + this.getPackageName() + "/raw/c";
		filenames[3] = "android.resource://" + this.getPackageName() + "/raw/d";
		filenames[4] = "android.resource://" + this.getPackageName() + "/raw/e";
		filenames[5] = "android.resource://" + this.getPackageName() + "/raw/f";
		filenames[6] = "android.resource://" + this.getPackageName() + "/raw/g";
		filenames[7] = "android.resource://" + this.getPackageName() + "/raw/h";
		filenames[8] = "android.resource://" + this.getPackageName() + "/raw/i";
		filenames[9] = "android.resource://" + this.getPackageName() + "/raw/j";
		filenames[10] = "android.resource://" + this.getPackageName() + "/raw/k";
		filenames[11] = "android.resource://" + this.getPackageName() + "/raw/l";
		filenames[12] = "android.resource://" + this.getPackageName() + "/raw/m";
		filenames[13]= "android.resource://" + this.getPackageName() + "/raw/n";
		filenames[14] = "android.resource://" + this.getPackageName() + "/raw/o";
		filenames[15] = "android.resource://" + this.getPackageName() + "/raw/p";
		filenames[16] = "android.resource://" + this.getPackageName() + "/raw/q";
		filenames[17] = "android.resource://" + this.getPackageName() + "/raw/r";
		filenames[18] = "android.resource://" + this.getPackageName() + "/raw/s";
		filenames[19] = "android.resource://" + this.getPackageName() + "/raw/t";
		filenames[20] = "android.resource://" + this.getPackageName() + "/raw/u";
		filenames[21] = "android.resource://" + this.getPackageName() + "/raw/v";
		filenames[22] = "android.resource://" + this.getPackageName() + "/raw/w";
		filenames[23] = "android.resource://" + this.getPackageName() + "/raw/x";
		filenames[24] = "android.resource://" + this.getPackageName() + "/raw/y";
		filenames[25] = "android.resource://" + this.getPackageName() + "/raw/z";
		filenametaptheletter = "android.resource://" + this.getPackageName() + "/raw/taptheletter";
		filenameCorrect = "android.resource://" + this.getPackageName() + "/raw/correct";
		filenameTryAgain = "android.resource://" + this.getPackageName() + "/raw/tryagain";

		buttons.add(0, new ButtonPlus((Button)findViewById(R.id.button1)));
		buttons.add(1, new ButtonPlus((Button)findViewById(R.id.button2)));
		buttons.add(2, new ButtonPlus((Button)findViewById(R.id.button3)));
		buttons.add(3, new ButtonPlus((Button)findViewById(R.id.button4)));

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
		String templetter = filenames[winValue];
		playAudio(filenametaptheletter, templetter);
		taken[winValue] = true;
		winButton = randInt(4, 1);
		Log.d("LettersActivity", String.valueOf(winButton));
		for(int i = 0; i < 4; i++){
			if((i+1) == winButton) {
				buttons.get(i).setWin(true);
			}
			else{
				buttons.get(i).setWin(false);
			}
		}
		buttons.get(winButton-1).setText(String.valueOf(letters[winValue]));
		for(int i = 0; i < 4; i++){
			if(!buttons.get(i).getWin()){
				int backgroundValue = randInt(25, 0);
				while(taken[backgroundValue]){
					backgroundValue = randInt(25, 0);
					Log.d("ColorsActivity", "button1");
					Log.d("ColorsActivity", String.valueOf(backgroundValue));
				}
				buttons.get(i).setText(String.valueOf(letters[backgroundValue]));
				taken[backgroundValue] = true;
			}
		}

		buttons.get(0).getButton().setOnClickListener((v) -> {
			cycleGame(buttons.get(0).getWin());
		});
		buttons.get(1).getButton().setOnClickListener((v) -> {
			cycleGame(buttons.get(1).getWin());
		});
		buttons.get(2).getButton().setOnClickListener((v) -> {
			cycleGame(buttons.get(2).getWin());
		});
		buttons.get(3).getButton().setOnClickListener((v) -> {
			cycleGame(buttons.get(3).getWin());
		});
	}
}
