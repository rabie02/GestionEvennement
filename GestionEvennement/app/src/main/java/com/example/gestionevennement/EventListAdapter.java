package com.example.gestionevennement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    private List<Event> eventList;

    public interface EventButtonClickListener {
        void onDetailsButtonClick(int position);
        void onEditButtonClick(int position);
        void onDeleteButtonClick(int position);
    }

    private EventButtonClickListener buttonClickListener;

    public EventListAdapter(List<Event> eventList, EventButtonClickListener buttonClickListener) {
        this.eventList = eventList;
        this.buttonClickListener = buttonClickListener;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView eventNameTextView;
        public Button detailsButton;
        public Button editButton;
        public Button deleteButton;

        public EventViewHolder(View itemView) {
            super(itemView);
            eventNameTextView = itemView.findViewById(R.id.eventNameTextView);
            detailsButton = itemView.findViewById(R.id.detailsButton);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater : Utilisé pour créer une vue à partir du fichier de layout event_item_layout.
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item_layout, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        //Récupération de l'événement à la position spécifiée
        Event event = eventList.get(position);
        //Mise à jour de l'affichage du nom de l'événement
        holder.eventNameTextView.setText(event.getName());
        //Définition des écouteurs de clic pour les boutons
        holder.detailsButton.setOnClickListener(view -> buttonClickListener.onDetailsButtonClick(position));
        //Bouton d'édition
        holder.editButton.setOnClickListener(view -> buttonClickListener.onEditButtonClick(position));
        //supression
        holder.deleteButton.setOnClickListener(view -> buttonClickListener.onDeleteButtonClick(position));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
