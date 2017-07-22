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

public class ColorsActivity extends AppCompatActivity {

	private static final String RED = "red";
	private static final String GREEN = "green";
	private static final String BLUE = "blue";
	private static final String YELLOW = "yellow";

	private static final String BUCKET = "bucket";
	private static final String CUP = "cup";
	private static final String SHOVEL = "shovel";

	String color;
	String type;
	int winValue;
	int winButton;
	boolean[] taken = new boolean[12];
	int[] backgrounds = new int[12];

	Button button1;
	Button button2;
	Button button3;
	Button button4;

	String filenametapthe;
	String filenamered;
	String filenameblue;
	String filenamegreen;
	String filenameyellow;
	String filenamebucket;
	String filenameshovel;
	String filenamecup;
	String filenameCorrect;
	String filenameTryAgain;

	public void playAudio(String firstFile) {
		MediaPlayer mp = new MediaPlayer();
		try {
			try {
				mp.setDataSource(ColorsActivity.this, Uri.parse(firstFile));
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
				mp.setDataSource(ColorsActivity.this, Uri.parse(firstFile));
				mp2.setDataSource(ColorsActivity.this, Uri.parse(secondFile));
				mp3.setDataSource(ColorsActivity.this, Uri.parse(thirdFile));
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

	public void playAudio(String firstFile, String secondFile, String thirdFile, String fourthFile) {
		MediaPlayer mp = new MediaPlayer();
		MediaPlayer mp2 = new MediaPlayer();
		MediaPlayer mp3 = new MediaPlayer();
		MediaPlayer mp4 = new MediaPlayer();

		try {
			try {
				mp.setDataSource(ColorsActivity.this, Uri.parse(firstFile));
				mp2.setDataSource(ColorsActivity.this, Uri.parse(secondFile));
				mp3.setDataSource(ColorsActivity.this, Uri.parse(thirdFile));
				mp4.setDataSource(ColorsActivity.this, Uri.parse(fourthFile));
			} catch (IOException e) {
				e.printStackTrace();
			}

			mp.prepareAsync();
			mp2.prepareAsync();
			mp3.prepareAsync();
			mp4.prepareAsync();

			mp.setOnPreparedListener(p -> mp.start());

			mp2.setOnPreparedListener(p -> mp.setOnCompletionListener(p1 -> {
				mp2.start();
				mp.release();
			}));

			mp3.setOnPreparedListener(p -> mp2.setOnCompletionListener(p1 -> {
				mp3.start();
				mp2.release();
			}));

			mp4.setOnPreparedListener(p -> mp3.setOnCompletionListener(p1 -> {
				mp4.start();
				mp3.release();
			}));

			mp4.setOnCompletionListener(p -> mp4.release());

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

	void updateTypes(int value) {
		switch (value) {
			case 0:
				color = RED;
				type = BUCKET;
				break;
			case 1:
				color = GREEN;
				type = BUCKET;
				break;
			case 2:
				color = BLUE;
				type = BUCKET;
				break;
			case 3:
				color = YELLOW;
				type = BUCKET;
				break;
			case 4:
				color = RED;
				type = CUP;
				break;
			case 5:
				color = GREEN;
				type = CUP;
				break;
			case 6:
				color = BLUE;
				type = CUP;
				break;
			case 7:
				color = YELLOW;
				type = CUP;
				break;
			case 8:
				color = RED;
				type = SHOVEL;
				break;
			case 9:
				color = GREEN;
				type = SHOVEL;
				break;
			case 10:
				color = BLUE;
				type = SHOVEL;
				break;
			case 11:
				color = YELLOW;
				type = SHOVEL;
				break;
		}
	}

	void cycleGame(boolean winner) {
		if(winner) {
			//play correct!
			playAudio(filenameCorrect);
			//toast correct!
			Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT).show();

			button1.setTag(false);
			button2.setTag(false);
			button3.setTag(false);
			button4.setTag(false);
			clearTaken();

			winValue = randInt(11,0);
			updateTypes(winValue);
			String tempColor = filenamered;
			switch (color){
				case RED:
					tempColor = filenamered;
					break;
				case GREEN:
					tempColor = filenamegreen;
					break;
				case BLUE:
					tempColor = filenameblue;
					break;
				case YELLOW:
					tempColor = filenameyellow;
					break;
			}
			String tempType = BUCKET;
			switch (type){
				case BUCKET:
					tempType = filenamebucket;
					break;
				case SHOVEL:
					tempType = filenameshovel;
					break;
				case CUP:
					tempType = filenamecup;
					break;
			}
			playAudio(filenameCorrect, filenametapthe, tempColor, tempType);
			taken[winValue] = true;
			winButton = randInt(4, 1);
			switch (winButton){
				case 1:
					button1.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[winValue], null));
					button1.setTag(true);
					button2.setTag(false);
					button3.setTag(false);
					button4.setTag(false);
					break;
				case 2:
					button2.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[winValue], null));
					button2.setTag(true);
					button1.setTag(false);
					button3.setTag(false);
					button4.setTag(false);
					break;
				case 3:
					button3.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[winValue], null));
					button3.setTag(true);
					button1.setTag(false);
					button2.setTag(false);
					button4.setTag(false);
					break;
				case 4:
					button4.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[winValue], null));
					button4.setTag(true);
					button1.setTag(false);
					button2.setTag(false);
					button3.setTag(false);
					break;
			}

			if(!(button1.getTag().equals(true))){
				int backgroundValue = randInt(11, 0);
				while(taken[backgroundValue]){
					backgroundValue = randInt(11, 0);
					Log.d("ColorsActivity", "button1");
					Log.d("ColorsActivity", String.valueOf(backgroundValue));
				}
				button1.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[backgroundValue], null));
				taken[backgroundValue] = true;
			}
			if(!(button2.getTag().equals(true))){
				int backgroundValue = randInt(11, 0);
				while(taken[backgroundValue]){
					backgroundValue = randInt(11, 0);
					Log.d("ColorsActivity", "button2");
					Log.d("ColorsActivity", String.valueOf(backgroundValue));
				}
				button2.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[backgroundValue], null));
				taken[backgroundValue] = true;
			}
			if(!(button3.getTag().equals(true))){
				int backgroundValue = randInt(11, 0);
				while(taken[backgroundValue]){
					backgroundValue = randInt(11, 0);
					Log.d("ColorsActivity", "button3");
					Log.d("ColorsActivity", String.valueOf(backgroundValue));
				}
				button3.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[backgroundValue], null));
				taken[backgroundValue] = true;
			}
			if(!(button4.getTag().equals(true))){
				int backgroundValue = randInt(11, 0);
				while(taken[backgroundValue]){
					backgroundValue = randInt(11, 0);
					Log.d("ColorsActivity", "button4");
					Log.d("ColorsActivity", String.valueOf(backgroundValue));
				}
				button4.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[backgroundValue], null));
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
		setContentView(R.layout.activity_colors);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		filenametapthe = "android.resource://" + this.getPackageName() + "/raw/tapthe";
		filenamered = "android.resource://" + this.getPackageName() + "/raw/red";
		filenameblue = "android.resource://" + this.getPackageName() + "/raw/blue";
		filenamegreen = "android.resource://" + this.getPackageName() + "/raw/green";
		filenameyellow = "android.resource://" + this.getPackageName() + "/raw/yellow";
		filenamebucket = "android.resource://" + this.getPackageName() + "/raw/bucket";
		filenameshovel = "android.resource://" + this.getPackageName() + "/raw/shovel";
		filenamecup = "android.resource://" + this.getPackageName() + "/raw/cup";
		filenameCorrect = "android.resource://" + this.getPackageName() + "/raw/correct";
		filenameTryAgain = "android.resource://" + this.getPackageName() + "/raw/tryagain";

		//file_bucket_red = ResourcesCompat.getDrawable(getResources(), R.drawable.bucket_red, null);

		/*file_bucket_green
		file_bucket_blue
		file_bucket_yellow
		file_cup_red
		file_cup_blue
		file_cup_green
		file_cup_yellow
		file_shovel_red
		file_shovel_blue
		file_shovel_green
		file_shovel_yellow*/

		button1=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		button3=(Button)findViewById(R.id.button3);
		button4=(Button)findViewById(R.id.button4);
		button1.setTag(false);
		button2.setTag(false);
		button3.setTag(false);
		button4.setTag(false);

		backgrounds[0] = R.mipmap.bucket_red;
		backgrounds[1] = R.mipmap.bucket_green;
		backgrounds[2] = R.mipmap.bucket_blue;
		backgrounds[3] = R.mipmap.bucket_yellow;
		backgrounds[4] = R.mipmap.cup_red;
		backgrounds[5] = R.mipmap.cup_green;
		backgrounds[6] = R.mipmap.cup_blue;
		backgrounds[7] = R.mipmap.cup_yellow;
		backgrounds[8] = R.mipmap.shovel_red;
		backgrounds[9] = R.mipmap.shovel_green;
		backgrounds[10] = R.mipmap.shovel_blue;
		backgrounds[11] = R.mipmap.shovel_yellow;

		winValue = randInt(11,0);
		updateTypes(winValue);

		String tempColor = filenamered;
		switch (color){
			case RED:
				tempColor = filenamered;
				break;
			case GREEN:
				tempColor = filenamegreen;
				break;
			case BLUE:
				tempColor = filenameblue;
				break;
			case YELLOW:
				tempColor = filenameyellow;
				break;
		}
		String tempType = BUCKET;
		switch (type){
			case BUCKET:
				tempType = filenamebucket;
				break;
			case SHOVEL:
				tempType = filenameshovel;
				break;
			case CUP:
				tempType = filenamecup;
				break;
		}
		playAudio(filenametapthe, tempColor, tempType);

		taken[winValue] = true;
		winButton = randInt(4, 1);
		switch (winButton){
			case 1:
				button1.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[winValue], null));
				button1.setTag(true);
				button2.setTag(false);
				button3.setTag(false);
				button4.setTag(false);
				break;
			case 2:
				button2.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[winValue], null));
				button2.setTag(true);
				button1.setTag(false);
				button3.setTag(false);
				button4.setTag(false);
				break;
			case 3:
				button3.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[winValue], null));
				button3.setTag(true);
				button1.setTag(false);
				button2.setTag(false);
				button4.setTag(false);
				break;
			case 4:
				button4.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[winValue], null));
				button4.setTag(true);
				button1.setTag(false);
				button2.setTag(false);
				button3.setTag(false);
				break;
		}

		if(!(button1.getTag().equals(true))){
			int backgroundValue = randInt(11, 0);
			while(backgroundValue == winValue || taken[backgroundValue]){
				backgroundValue = randInt(11, 0);
				Log.d("ColorsActivity", String.valueOf(backgroundValue));
			}
			button1.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[backgroundValue], null));
			taken[backgroundValue] = true;
		}
		if(!(button2.getTag().equals(true))){
			int backgroundValue = randInt(11, 0);
			while(backgroundValue == winValue || taken[backgroundValue]){
				backgroundValue = randInt(11, 0);
				Log.d("ColorsActivity", String.valueOf(backgroundValue));
			}
			button2.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[backgroundValue], null));
			taken[backgroundValue] = true;
		}
		if(!(button3.getTag().equals(true))){
			int backgroundValue = randInt(11, 0);
			while(backgroundValue == winValue || taken[backgroundValue]){
				backgroundValue = randInt(11, 0);
				Log.d("ColorsActivity", String.valueOf(backgroundValue));
			}
			button3.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[backgroundValue], null));
			taken[backgroundValue] = true;
		}
		if(!(button4.getTag().equals(true))){
			int backgroundValue = randInt(11, 0);
			while(backgroundValue == winValue || taken[backgroundValue]){
				backgroundValue = randInt(11, 0);
				Log.d("ColorsActivity", String.valueOf(backgroundValue));
			}
			button4.setBackground(ResourcesCompat.getDrawable(getResources(), backgrounds[backgroundValue], null));
			taken[backgroundValue] = true;
		}


		//button1.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.bucket_red, null));


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
