package com.roshsoft.starwars.ui.element;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roshsoft.starwars.data.http.Planet;
import com.roshsoft.starwars.databinding.ItemPlanetBinding;

public class PlanetsViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView name, climate;

    public PlanetsViewHolder(@NonNull ItemPlanetBinding binding) {
        super(binding.getRoot());
        image = binding.planetImg;
        name = binding.planetName;
        climate = binding.planetClimate;
    }

    public void onBind(Planet planet) {
        name.setText(planet.getName());
        climate.setText(("Climate: " + planet.getClimate()));
    }
}
