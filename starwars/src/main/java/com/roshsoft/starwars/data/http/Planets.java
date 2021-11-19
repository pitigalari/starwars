package com.roshsoft.starwars.data.http;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import timber.log.Timber;

public class Planets {

    @SerializedName("results")
    @Expose
    private List<PlanetDetail> planetList;
    @SerializedName("next")
    @Expose
    private String nextUrl;
    @SerializedName("previous")
    @Expose
    private String preUrl;

    public List<PlanetDetail> getPlanetList() {
        return planetList;
    }

    @Nullable
    public Integer getNextPage() {
        if (nextUrl != null)
            try {
                return Integer.parseInt(nextUrl.split("page=")[1]);
            } catch (Exception e) {
                Timber.e(e);
            }
        return null;
    }

    @Nullable
    public Integer getPreviousPage() {
        if (preUrl != null)
            try {
                return Integer.parseInt(preUrl.split("page=")[1]);
            } catch (Exception e) {
                Timber.e(e);
            }
        return null;
    }
}
