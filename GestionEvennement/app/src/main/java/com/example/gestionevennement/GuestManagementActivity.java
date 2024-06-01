package com.example.gestionevennement;

// Importations des classes nécessaires
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// Déclaration de la classe GuestManagementActivity, qui hérite de AppCompatActivity
public class GuestManagementActivity extends AppCompatActivity {

    // Déclaration des variables membres
    private EditText editTextEventDescription;
    private EditText editTextInviteEmail;
    private ListView listViewInvites;
    private Button buttonAddInvite;
    private Button buttonRemoveInvite;
    private Button buttonSendInvitations;

    private ArrayList<String> invitedList;
    private ArrayAdapter<String> invitedListAdapter;

    // Méthode appelée lors de la création de l'activité
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_management); // Définit le layout à utiliser

        // Liaison des variables avec les éléments de l'interface graphique
        editTextEventDescription = findViewById(R.id.editTextEventDescription);
        editTextInviteEmail = findViewById(R.id.editTextInviteEmail);
        listViewInvites = findViewById(R.id.listViewInvites);
        buttonAddInvite = findViewById(R.id.buttonAddInvite);
        buttonRemoveInvite = findViewById(R.id.buttonRemoveInvite);
        buttonSendInvitations = findViewById(R.id.buttonSendInvitations);

        // Initialisation de la liste d'invités et de l'adaptateur
        invitedList = new ArrayList<>();
        invitedListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, invitedList);
        listViewInvites.setAdapter(invitedListAdapter);

        // Définition des actions à effectuer lors du clic sur les boutons
        buttonAddInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInvite(); // Appel de la méthode pour ajouter un invité
            }
        });

        buttonRemoveInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeInvite(); // Appel de la méthode pour supprimer un invité
            }
        });

        buttonSendInvitations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInvitationsByEmail(); // Appel de la méthode pour envoyer les invitations par e-mail
            }
        });
    }

    // Méthode pour ajouter un invité à la liste
    private void addInvite() {
        String inviteEmail = editTextInviteEmail.getText().toString().trim();
        if (!inviteEmail.isEmpty()) {
            invitedList.add("Invité: " + inviteEmail);
            invitedListAdapter.notifyDataSetChanged(); // Met à jour l'affichage de la liste
            editTextInviteEmail.getText().clear(); // Efface le champ de saisie
        } else {
            Toast.makeText(this, "Veuillez saisir une adresse e-mail valide", Toast.LENGTH_SHORT).show();
        }
    }

    // Méthode pour supprimer le dernier invité de la liste
    private void removeInvite() {
        if (!invitedList.isEmpty()) {
            invitedList.remove(invitedList.size() - 1);
            invitedListAdapter.notifyDataSetChanged(); // Met à jour l'affichage de la liste
        } else {
            Toast.makeText(this, "Aucun invité à supprimer", Toast.LENGTH_SHORT).show();
        }
    }

    // Méthode pour envoyer une invitation par e-mail
    private void sendInvitationByEmail(String eventDescription, String inviteEmail) {
        // Implémentez le code pour envoyer une invitation par e-mail
        // Utilisez eventDescription et inviteEmail pour personnaliser le contenu de l'e-mail
        Toast.makeText(this, "Invitation envoyée par e-mail à : " + inviteEmail, Toast.LENGTH_SHORT).show();
    }

    // Méthode pour envoyer toutes les invitations par e-mail
    private void sendInvitationsByEmail() {
        String eventDescription = editTextEventDescription.getText().toString().trim();
        if (eventDescription.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir une description de l'événement", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> inviteEmails = new ArrayList<>();
        for (String invite : invitedList) {
            String inviteEmail = invite.split(": ")[1];
            inviteEmails.add(inviteEmail);
        }

        if (inviteEmails.isEmpty()) {
            Toast.makeText(this, "Veuillez ajouter au moins un invité", Toast.LENGTH_SHORT).show();
            return;
        }

        for (String inviteEmail : inviteEmails) {
            sendInvitationByEmail(eventDescription, inviteEmail); // Envoie l'invitation pour chaque adresse e-mail
        }
    }
}
