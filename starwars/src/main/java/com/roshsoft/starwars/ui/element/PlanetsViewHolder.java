package com.roshsoft.starwars.ui.element;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.roshsoft.starwars.data.http.PlanetDetail;
import com.roshsoft.starwars.databinding.ItemPlanetBinding;
import com.roshsoft.starwars.ui.util.OnItemClickListener;

public class PlanetsViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView name, climate;
    private ConstraintLayout container;

    private final OnItemClickListener<PlanetDetail> mListener;

    public PlanetsViewHolder(@NonNull ItemPlanetBinding binding,
                             OnItemClickListener<PlanetDetail> clickListener) {
        super(binding.getRoot());
        mListener = clickListener;
        image = binding.planetImg;
        name = binding.planetName;
        climate = binding.planetClimate;
        container = binding.itemContainer;
    }

    public void onBind(PlanetDetail planet) {
        name.setText(planet.getName());
        climate.setText(("Climate: " + planet.getClimate()));
        container.setOnClickListener(v -> {
            if (mListener != null)
                mListener.onItemClicked(planet);
        });
    }
}
