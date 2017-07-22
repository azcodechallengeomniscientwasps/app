package com.makeapede.azcodechallengeapp;

import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class EventsActivity extends AppCompatActivity {
	private EventAdapter adapter;
	private RecyclerView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);

		list = (RecyclerView) findViewById(R.id.event_list);
		list.setHasFixedSize(true);

		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		list.setLayoutManager(layoutManager);

		adapter = new EventAdapter(new ArrayList<>());

		list.setAdapter(adapter);

		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		if (user != null) {
			DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/events");
			ref.addValueEventListener(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					ArrayList<Event> events = new ArrayList<>();

					for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
						Event event = snapshot.getValue(Event.class);
						events.add(event);
					}

					adapter.setData(events);
				}

				@Override
				public void onCancelled(DatabaseError databaseError) {}
			});
		}
	}

	public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
		private ArrayList<Event> events;

		public EventAdapter(ArrayList<Event> events) {
			this.events = events;
		}

		public void setData(ArrayList<Event> events) {
			this.events = events;

			notifyDataSetChanged();
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(EventsActivity.this)
									  .inflate(R.layout.event_list_item, parent, false);

			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			Event event = events.get(position);

			holder.titleView.setText(event.title);
			holder.dateTimeView.setText(event.dateTime);
			holder.locationView.setText(event.location);
			holder.detailsView.setText(event.details);

			holder.view.setOnClickListener(view -> {
				CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
				builder.setToolbarColor(Color.parseColor("#7dacf8"));
				CustomTabsIntent customTabsIntent = builder.build();
				customTabsIntent.launchUrl(EventsActivity.this, Uri.parse(event.link));
			});
		}

		@Override
		public int getItemCount() {
			return events.size();
		}

		public class ViewHolder extends RecyclerView.ViewHolder {
			View view;
			TextView titleView;
			TextView dateTimeView;
			TextView locationView;
			TextView detailsView;

			public ViewHolder(View itemView) {
				super(itemView);

				view = itemView;

				titleView = itemView.findViewById(R.id.event_title);
				dateTimeView = itemView.findViewById(R.id.date_time);
				locationView = itemView.findViewById(R.id.location);
				detailsView = itemView.findViewById(R.id.details);
			}
		}
	}
}
