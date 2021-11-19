package com.roshsoft.starwars.data.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Planet {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("climate")
    @Expose
    private String climate;
    @SerializedName("url")
    @Expose
    private String url;

    public String getName() {
        return name;
    }

    public String getClimate() {
        return climate;
    }

    public String getID() {
        return url;
    }
}
