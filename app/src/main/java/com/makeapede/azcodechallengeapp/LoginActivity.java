/*
 * $filename
 * Copyright (C) 2017  Automata Development
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.makeapede.azcodechallengeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.Collections;

public class LoginActivity extends AppCompatActivity {
	private static final int LOG_IN_RC = 253;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FirebaseAuth auth = FirebaseAuth.getInstance();
		if (auth.getCurrentUser() == null) {
			startActivityForResult(
					AuthUI.getInstance()
						  .createSignInIntentBuilder()
						  .setAvailableProviders(
								  Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build())).build(),
					LOG_IN_RC);
		} else {
			startActivity(new Intent(this, KidListActivity.class));
			finish();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == LOG_IN_RC) {
			if (resultCode == ResultCodes.OK) {
				// Success!
				Toast.makeText(this, "Success! :D", Toast.LENGTH_SHORT).show();

				startActivity(new Intent(this, KidListActivity.class));
			} else {
				// Failure :(
				Toast.makeText(this, "Failure :(", Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}
}
