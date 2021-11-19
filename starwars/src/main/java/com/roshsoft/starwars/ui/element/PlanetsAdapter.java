package com.roshsoft.starwars.ui.element;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.roshsoft.starwars.data.http.Planet;
import com.roshsoft.starwars.databinding.ItemPlanetBinding;

import org.jetbrains.annotations.NotNull;

public class PlanetsAdapter extends PagedListAdapter<Planet, PlanetsViewHolder> {

    public PlanetsAdapter() {
        this(new DiffUtil.ItemCallback<Planet>() {

            @Override
            public boolean areItemsTheSame(@NonNull @NotNull Planet oldItem, @NonNull @NotNull Planet newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull @NotNull Planet oldItem, @NonNull @NotNull Planet newItem) {
                return false;
            }
        });
    }

    private PlanetsAdapter(@NonNull DiffUtil.ItemCallback<Planet> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PlanetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlanetsViewHolder(
                ItemPlanetBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetsViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }
}
