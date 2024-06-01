package com.example.gestionevennement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditEventActivity extends AppCompatActivity {

    private EditText editTextEventName;
    private EditText editTextEventDescription;
    private Button buttonSaveChanges;

    private int eventId;
    private String eventName;
    private String eventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        editTextEventName = findViewById(R.id.editTextEventName);
        editTextEventDescription = findViewById(R.id.editTextEventDescription);
        buttonSaveChanges = findViewById(R.id.buttonSaveChanges);
        eventId = getIntent().getIntExtra("eventId", -1);
        eventName = getIntent().getStringExtra("eventName");
        eventDescription = getIntent().getStringExtra("eventDescription");
        editTextEventName.setText(eventName);
        editTextEventDescription.setText(eventDescription);
        buttonSaveChanges.setOnClickListener(view -> saveChanges());
    }

    private void saveChanges() {

        String newEventName = editTextEventName.getText().toString();
        String newEventDescription = editTextEventDescription.getText().toString();


        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        boolean updateSuccess = databaseHelper.updateEvent(eventId, newEventName, newEventDescription);

        // Vérifier si la mise à jour a réussi
        if (updateSuccess) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("eventId", eventId);
            resultIntent.putExtra("newEventName", newEventName);
            resultIntent.putExtra("newEventDescription", newEventDescription);
            setResult(RESULT_OK, resultIntent);
            finish();
        } else {

            Toast.makeText(this, "Échec de la mise à jour de l'événement", Toast.LENGTH_SHORT).show();
        }
    }
}
