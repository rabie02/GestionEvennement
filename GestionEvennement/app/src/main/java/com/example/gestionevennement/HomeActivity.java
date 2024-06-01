package com.example.gestionevennement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button buttonCreateEvent;
    private Button buttonViewEvents;
    private Button buttonGuestManagement;
    private Button buttonBudgetManagement; // Nouveau bouton ajouté
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonCreateEvent = findViewById(R.id.buttonCreateEvent);
        buttonViewEvents = findViewById(R.id.buttonViewEvents);
        buttonGuestManagement = findViewById(R.id.buttonGuestManagement);
        buttonBudgetManagement = findViewById(R.id.buttonBudgetManagement);
        buttonLogout = findViewById(R.id.buttonLogout);

        buttonCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CreateEventActivity.class));
            }
        });

        buttonViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ViewEventsActivity.class));
            }
        });

        buttonGuestManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, GuestManagementActivity.class));
            }
        });

        buttonBudgetManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BudgetManagementActivity.class));
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
