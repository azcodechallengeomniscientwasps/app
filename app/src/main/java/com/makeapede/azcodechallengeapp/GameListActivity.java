package com.makeapede.azcodechallengeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.makeapede.azcodechallengeapp.games.*;

public class GameListActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_list);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		findViewById(R.id.button_colors).setOnClickListener((v) -> {
			startActivity(new Intent(this, ColorsActivity.class));
		});
		findViewById(R.id.button_counting).setOnClickListener((v) -> {
			startActivity(new Intent(this, CountingActivity.class));

		});
		findViewById(R.id.button_letters).setOnClickListener((v) -> {
			startActivity(new Intent(this, LettersActivity.class));

		});
		findViewById(R.id.button_stuff).setOnClickListener((v) -> {
			Log.d("GameListActivity", "Stuff!");

		});
	}
}
