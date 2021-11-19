package com.roshsoft.starwars.data.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import timber.log.Timber;

public class PlanetDetail {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("climate")
    @Expose
    private String climate;
    @SerializedName("gravity")
    @Expose
    private String gravity;
    @SerializedName("orbital_period")
    @Expose
    private String orbitalPeriod;
    @SerializedName("url")
    @Expose
    private String url;

    private int id;

    public String getName() {
        return name;
    }

    public String getClimate() {
        return climate;
    }

    public String getGravity() {
        return gravity;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public int getID() {
        if (id == 0 && url != null) {
            try {
                url = url.replace("https://", "");
                String[] parts = url.split("/");
                id = Integer.parseInt(parts[3]);
            } catch (Exception e) {
                Timber.e(e);
            }
        }
        return id;
    }
}
