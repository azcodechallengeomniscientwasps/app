package com.makeapede.azcodechallengeapp.games;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.makeapede.azcodechallengeapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CountingActivity extends AppCompatActivity {

	String filename1;
	String filename2;
	String filename3;
	String filename4;
	String filename5;
	String filename6;
	String filename7;
	String filename8;
	String filename9;
	String filename10;
	String filenameCorrect;
	String filenameTryAgain;

	int number = 1;
	int numberPrevious = 0;
	ArrayList<ImageView> fishes = new ArrayList<>();

	public void playAudio(String firstFile, String secondFile) {
		MediaPlayer mp = new MediaPlayer();
		MediaPlayer mp2 = new MediaPlayer();

		try {
			try {
				mp.setDataSource(CountingActivity.this, Uri.parse(firstFile));
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				mp2.setDataSource(CountingActivity.this, Uri.parse(secondFile));
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

	int randInt() {
		Random rand = new Random();

		return rand.nextInt(10) + 1;
	}

	void setFishNumber(int number){
		for(int i = 0 ; i<fishes.size(); i++){
			if((i+1)<=number) {
				fishes.get(i).setVisibility(View.VISIBLE);
			}
			else{
				fishes.get(i).setVisibility(View.INVISIBLE);
			}
		}
	}

	void cycleGame(int number, String first_file){
		if(number==this.number){
			playAudio(first_file, filenameCorrect);
			// toast success
			Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT).show();
			//set the number
			this.number = randInt();
			while(this.number == numberPrevious){
				this.number = randInt();
			}
			//set the number of fish
			setFishNumber(this.number);
			numberPrevious = this.number;
		}
		else {
			playAudio(first_file, filenameTryAgain);
			Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counting);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		filename1 = "android.resource://" + this.getPackageName() + "/raw/one";
		filename2 = "android.resource://" + this.getPackageName() + "/raw/two";
		filename3 = "android.resource://" + this.getPackageName() + "/raw/three";
		filename4 = "android.resource://" + this.getPackageName() + "/raw/four";
		filename5 = "android.resource://" + this.getPackageName() + "/raw/five";
		filename6 = "android.resource://" + this.getPackageName() + "/raw/six";
		filename7 = "android.resource://" + this.getPackageName() + "/raw/seven";
		filename8 = "android.resource://" + this.getPackageName() + "/raw/eight";
		filename9 = "android.resource://" + this.getPackageName() + "/raw/nine";
		filename10 = "android.resource://" + this.getPackageName() + "/raw/ten";
		filenameCorrect = "android.resource://" + this.getPackageName() + "/raw/correct";
		filenameTryAgain = "android.resource://" + this.getPackageName() + "/raw/tryagain";

		ImageView fish1 = (ImageView)findViewById(R.id.fish1);fish1.setVisibility(View.INVISIBLE);fishes.add(fish1);
		ImageView fish2 = (ImageView)findViewById(R.id.fish2);fish2.setVisibility(View.INVISIBLE);fishes.add(fish2);
		ImageView fish3 = (ImageView)findViewById(R.id.fish3);fish3.setVisibility(View.INVISIBLE);fishes.add(fish3);
		ImageView fish4 = (ImageView)findViewById(R.id.fish4);fish4.setVisibility(View.INVISIBLE);fishes.add(fish4);
		ImageView fish5 = (ImageView)findViewById(R.id.fish5);fish5.setVisibility(View.INVISIBLE);fishes.add(fish5);
		ImageView fish6 = (ImageView)findViewById(R.id.fish6);fish6.setVisibility(View.INVISIBLE);fishes.add(fish6);
		ImageView fish7 = (ImageView)findViewById(R.id.fish7);fish7.setVisibility(View.INVISIBLE);fishes.add(fish7);
		ImageView fish8 = (ImageView)findViewById(R.id.fish8);fish8.setVisibility(View.INVISIBLE);fishes.add(fish8);
		ImageView fish9 = (ImageView)findViewById(R.id.fish9);fish9.setVisibility(View.INVISIBLE);fishes.add(fish9);
		ImageView fish10 = (ImageView)findViewById(R.id.fish10);fish10.setVisibility(View.INVISIBLE);fishes.add(fish10);

		Button button1=(Button)findViewById(R.id.number_1);
		Button button2=(Button)findViewById(R.id.number_2);
		Button button3=(Button)findViewById(R.id.number_3);
		Button button4=(Button)findViewById(R.id.number_4);
		Button button5=(Button)findViewById(R.id.number_5);
		Button button6=(Button)findViewById(R.id.number_6);
		Button button7=(Button)findViewById(R.id.number_7);
		Button button8=(Button)findViewById(R.id.number_8);
		Button button9=(Button)findViewById(R.id.number_9);
		Button button10=(Button)findViewById(R.id.number_10);

		this.number = randInt();
		while(this.number == numberPrevious){
			this.number = randInt();
		}
		//set the number of fish
		setFishNumber(this.number);
		numberPrevious = this.number;

		button1.setOnClickListener((v) -> cycleGame(1, filename1));

		button2.setOnClickListener((v) -> cycleGame(2, filename2));

		button3.setOnClickListener((v) -> cycleGame(3, filename3));

		button4.setOnClickListener((v) -> cycleGame(4, filename4));

		button5.setOnClickListener((v) -> cycleGame(5, filename5));

		button6.setOnClickListener((v) -> cycleGame(6, filename6));

		button7.setOnClickListener((v) -> cycleGame(7, filename7));

		button8.setOnClickListener((v) -> cycleGame(8, filename8));

		button9.setOnClickListener((v) -> cycleGame(9, filename9));

		button10.setOnClickListener((v) -> cycleGame(10, filename10));
	}
}
