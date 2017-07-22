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

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class KidListActivity extends AppCompatActivity {
	public static final String EXTRA_KID_NAME = "extra-name";

	private KidAdapter adapter;
	private RecyclerView list;
	private FirebaseUser user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kid_list);

		list = (RecyclerView) findViewById(R.id.kid_list);
		list.setHasFixedSize(true);

		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		list.setLayoutManager(layoutManager);

		adapter = new KidAdapter(new ArrayList<>());
		list.setAdapter(adapter);

		findViewById(R.id.add_button).setOnClickListener((v) -> {
			Log.d("KidListActivity", "Adding item");

			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setView(R.layout.add_kid_dialog);
			builder.setPositiveButton("OK", (dialogInterface, i) -> {
				Dialog dialog = (Dialog) dialogInterface;

				String name = ((EditText) dialog.findViewById(R.id.name_input)).getText().toString();
				String age = ((EditText) dialog.findViewById(R.id.age_input)).getText().toString();

				adapter.addItem(new Kid(age, name));
			});

			builder.create().show();
		});

		user = FirebaseAuth.getInstance().getCurrentUser();
		if (user != null) {
			String uid = user.getUid();

			DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/" + uid);
			ref.addValueEventListener(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					ArrayList<Kid> kids = new ArrayList<>();

					for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
						Kid kid = snapshot.getValue(Kid.class);
						if (kid != null) {
							kid.uid = snapshot.getKey();
						}
						kids.add(kid);
					}

					Collections.sort(kids);
					adapter.setData(kids);
				}

				@Override
				public void onCancelled(DatabaseError databaseError) {}
			});
		}
	}

	void signOut() {
		FirebaseAuth thing = FirebaseAuth.getInstance();
		thing.signOut();
		// user is now signed out
		startActivity(new Intent(KidListActivity.this, LoginActivity.class));
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_kid_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.action_logout:
				//logout method thingy
				Log.d("KidListActivity", "Attempting logout...");
				signOut();
				break;

			case R.id.action_account:
				//go to password confirmation page
				Log.d("KidListActivity", "Attempting password check");
				break;

			case R.id.action_events:
				Intent intent = new Intent(this, EventsActivity.class);
				startActivity(intent);
				break;

			default:
				return super.onOptionsItemSelected(item);
		}

		return true;
	}

	public class KidAdapter extends RecyclerView.Adapter<KidAdapter.ViewHolder> {
		private ArrayList<Kid> kids;

		public KidAdapter(ArrayList<Kid> kids) {
			this.kids = kids;
		}

		public void setData(ArrayList<Kid> kids) {
			this.kids = kids;
			notifyDataSetChanged();
		}

		public void addItem(Kid kid) {
			kids.add(kid);

			if(user != null) {
				DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/" + user.getUid());
				DatabaseReference kidRef = ref.push();
				kidRef.setValue(kid);

				kid.uid = kidRef.getKey();
			}

			notifyItemInserted(kids.size()-1);
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.kid_list_item, parent, false);

			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			holder.nameView.setText(kids.get(position).name);
			holder.ageView.setText("Age: " + String.valueOf(kids.get(position).age));
		}

		@Override
		public int getItemCount() {
			return kids.size();
		}

		public class ViewHolder extends RecyclerView.ViewHolder {
			TextView nameView;
			TextView ageView;

			public ViewHolder(View itemView) {
				super(itemView);

				nameView = itemView.findViewById(R.id.name);
				ageView = itemView.findViewById(R.id.age);

				itemView.setOnClickListener(view -> {
					Intent intent = new Intent(KidListActivity.this, GameListActivity.class);

					intent.putExtra(EXTRA_KID_NAME, nameView.getText());

					startActivity(intent);
				});

				itemView.setOnLongClickListener(view -> {
					// Show edit dialog/screen
					AlertDialog.Builder builder = new AlertDialog.Builder(KidListActivity.this);

					View dialogView = LayoutInflater.from(KidListActivity.this)
							.inflate(R.layout.edit_kid_dialog, null);

					EditText nameInput = dialogView.findViewById(R.id.name_input);
					EditText ageInput = dialogView.findViewById(R.id.age_input);

					nameInput.setText(nameView.getText());
					ageInput.setText(ageView.getText());

					builder.setView(dialogView);
					builder.setPositiveButton("OK", (dialogInterface, i) -> {
						String newName = nameInput.getText().toString();
						String newAge = ageInput.getText().toString();

						if(!newName.equals("") && !newAge.equals("")) {
							Kid kid = kids.get(getAdapterPosition());
							kid.name = newName;
							kid.age = newAge;

							nameView.setText(newName);
							ageView.setText("Age: " + newAge);

							DatabaseReference ref = FirebaseDatabase.getInstance().getReference(
									"/" + user.getUid() + "/" + kid.uid);
							ref.setValue(kid);
						}
					});

					builder.setNegativeButton("Cancel", (dialogInterface, i) -> {});

					builder.setNeutralButton("Remove kid", (dialogInterface, i) -> {
						String removedUid = kids.remove(getAdapterPosition()).uid;

						DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/" + user.getUid() + "/" + removedUid);
						ref.removeValue();

						notifyItemRemoved(getAdapterPosition());
					});

					builder.create().show();

					return true;
				});
			}
		}
	}
}
