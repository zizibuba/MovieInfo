package com.example.dusan.movieinfo.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {

    @SerializedName("title") @Expose private String name;
    @SerializedName("vote_average") @Expose private String rating;
    @SerializedName("vote_count") @Expose private String voteCount;
    @SerializedName("id") @Expose private Integer id;
    @SerializedName("popularity") @Expose private String popularity;
    @SerializedName("original_language") @Expose private String originalLanguage;
    @SerializedName("overview") @Expose private String overview;
    @SerializedName("genre_ids") @Expose private List<Integer> genreIds;
    @SerializedName("poster_path") @Expose private String imageUrl;
    @SerializedName("release_date") @Expose private String releaseDate;

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getOverview() {
        return overview;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
