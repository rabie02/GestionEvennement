package com.example.gestionevennement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewEventsActivity extends AppCompatActivity implements EventListAdapter.EventButtonClickListener {

    private List<Event> eventList;
    private RecyclerView recyclerViewEvents;
    private EventListAdapter eventListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        eventList = new ArrayList<>();

        recyclerViewEvents = findViewById(R.id.recyclerViewEvents);

        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(this));

        eventListAdapter = new EventListAdapter(eventList, this);

        recyclerViewEvents.setAdapter(eventListAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            String newEventName = intent.getStringExtra("newEventName");
            String newEventDescription = intent.getStringExtra("newEventDescription");
            if (newEventName != null && newEventDescription != null) {
                Event newEvent = new Event(newEventName, newEventDescription);
                addEventToList(newEvent);
            }
        }
    }

    @Override
    public void onDetailsButtonClick(int position) {
        Event event = eventList.get(position);
        Intent intent = new Intent(this, EventDetailsActivity.class);
        intent.putExtra("eventName", event.getName());
        intent.putExtra("eventDescription", event.getDescription());
        startActivity(intent);
    }

    @Override
    public void onEditButtonClick(int position) {
        Event event = eventList.get(position);
        Intent intent = new Intent(this, EditEventActivity.class);
        intent.putExtra("eventId", event.getId()); // Passer l'ID de l'événement
        intent.putExtra("eventName", event.getName());
        intent.putExtra("eventDescription", event.getDescription());
        startActivity(intent);
    }

    @Override
    public void onDeleteButtonClick(int position) {
        eventList.remove(position);
        eventListAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Événement supprimé avec succès", Toast.LENGTH_SHORT).show();
    }

    public void addEventToList(Event event) {
        eventList.add(event);
        eventListAdapter.notifyItemInserted(eventList.size() - 1);
    }
}
