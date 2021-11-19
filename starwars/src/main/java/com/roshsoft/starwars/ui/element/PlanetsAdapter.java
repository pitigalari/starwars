package com.roshsoft.starwars.ui.element;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.roshsoft.starwars.data.http.PlanetDetail;
import com.roshsoft.starwars.databinding.ItemPlanetBinding;
import com.roshsoft.starwars.ui.util.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

public class PlanetsAdapter extends PagedListAdapter<PlanetDetail, PlanetsViewHolder> {

    private final OnItemClickListener<PlanetDetail> mListener;

    public PlanetsAdapter(OnItemClickListener<PlanetDetail> clickListener) {
        this(new DiffUtil.ItemCallback<PlanetDetail>() {

            @Override
            public boolean areItemsTheSame(@NonNull @NotNull PlanetDetail oldItem, @NonNull @NotNull PlanetDetail newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull @NotNull PlanetDetail oldItem, @NonNull @NotNull PlanetDetail newItem) {
                return false;
            }
        }, clickListener);
    }

    private PlanetsAdapter(@NonNull DiffUtil.ItemCallback<PlanetDetail> diffCallback,
                           OnItemClickListener<PlanetDetail> clickListener) {
        super(diffCallback);
        mListener = clickListener;
    }

    @NonNull
    @Override
    public PlanetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlanetsViewHolder(
                ItemPlanetBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),
                mListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetsViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }
}
