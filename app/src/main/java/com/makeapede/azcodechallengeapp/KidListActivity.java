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
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class KidListActivity extends AppCompatActivity {
	private KidAdapter adapter;
	private RecyclerView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kid_list);

		list = (RecyclerView) findViewById(R.id.kid_list);
		list.setHasFixedSize(true);

		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		list.setLayoutManager(layoutManager);

		adapter = new KidAdapter(new ArrayList<>());

		findViewById(R.id.add_button).setOnClickListener((v) -> {
			Log.d("KidListActivity", "Adding item");

			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setTitle("Test");
			builder.setMessage("test");
			builder.setPositiveButton("OK", (dialogInterface, i) -> {
				adapter.addItem(new Kid(0, "Name"));
			});

			builder.create().show();
		});

		list.setAdapter(adapter);
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
				return true;
			case R.id.action_account:
				//go to password confirmation page
				Log.d("KidListActivity", "Attempting password check");
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public class KidAdapter extends RecyclerView.Adapter<KidAdapter.ViewHolder> {
		private ArrayList<Kid> kids;

		public KidAdapter(ArrayList<Kid> kids) {
			this.kids = kids;
		}

		public void addItem(Kid kid) {
			kids.add(kid);
			Log.d("KidListActivity", "Printing all kids");
			for(Kid kid1 : kids) {
				Log.d("KidListActivity", kid1.name);
			}
			//notifyDataSetChanged();
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
			holder.ageView.setText(String.valueOf(kids.get(position).age));
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
			}
		}
	}

	public class Kid {
		public int age;
		public String name;

		public Kid(int age, String name) {
			this.age = age;
			this.name = name;
		}
	}
}
