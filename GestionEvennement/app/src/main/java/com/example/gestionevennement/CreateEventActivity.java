package com.example.gestionevennement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateEventActivity extends AppCompatActivity {

    private EditText editTextEventName;
    private EditText editTextEventDescription;
    private EditText editTextEventStartDate;
    private EditText editTextEventEndDate;
    private Button buttonSaveEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        editTextEventName = findViewById(R.id.editTextEventName);
        editTextEventDescription = findViewById(R.id.editTextEventDescription);
        editTextEventStartDate = findViewById(R.id.editTextEventStartDate);
        editTextEventEndDate = findViewById(R.id.editTextEventEndDate);
        buttonSaveEvent = findViewById(R.id.buttonSaveEvent);
        buttonSaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEvent();
            }
        });
    }

    private void saveEvent() {

        String eventName = editTextEventName.getText().toString().trim();
        String eventDescription = editTextEventDescription.getText().toString().trim();
        String eventStartDate = editTextEventStartDate.getText().toString().trim();
        String eventEndDate = editTextEventEndDate.getText().toString().trim();


        if (eventName.isEmpty() || eventDescription.isEmpty() || eventStartDate.isEmpty() || eventEndDate.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }


        Event newEvent = new Event(eventName, eventDescription);

        Intent intent = new Intent(this, ViewEventsActivity.class);
        intent.putExtra("newEventName", eventName);
        intent.putExtra("newEventDescription", eventDescription);
        startActivity(intent);


        Toast.makeText(this, "Événement enregistré avec succès", Toast.LENGTH_SHORT).show();


        editTextEventName.setText("");
        editTextEventDescription.setText("");
        editTextEventStartDate.setText("");
        editTextEventEndDate.setText("");
    }
}
