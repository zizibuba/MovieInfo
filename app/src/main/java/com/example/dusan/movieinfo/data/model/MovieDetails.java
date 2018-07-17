package com.example.dusan.movieinfo.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails {

    @SerializedName("crew") @Expose private List<CrewMember> crewList = new ArrayList<>();

    public List<CrewMember> getCrewList() {
        return crewList;
    }
}
