package com.example.gestionevennement;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EventDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        TextView textViewEventName = findViewById(R.id.textViewEventName);
        TextView textViewEventDescription = findViewById(R.id.textViewEventDescription);
        TextView textViewEventDate = findViewById(R.id.textViewEventDate);
        Button buttonClose = findViewById(R.id.buttonClose);
        String eventName = getIntent().getStringExtra("eventName");
        String eventDescription = getIntent().getStringExtra("eventDescription");
        String eventDate = getIntent().getStringExtra("eventDate");
        textViewEventName.setText(eventName);
        textViewEventDescription.setText(eventDescription);
        textViewEventDate.setText(eventDate);
        buttonClose.setOnClickListener(view -> finish());
    }
}

