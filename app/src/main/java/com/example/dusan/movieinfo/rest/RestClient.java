package com.example.dusan.movieinfo.rest;

import com.example.dusan.movieinfo.data.model.MovieDetails;
import com.example.dusan.movieinfo.data.model.Movies;

import retrofit2.Call;

public class RestClient {

    private static final String API_KEY = "c22d755514350d9836b3f9b173b3d763";
    private RestApiService retrofitInstance;

    public RestClient() {
        if (retrofitInstance == null) {
            retrofitInstance = RetrofitInstance.getRetrofitInstance();
        }
    }

    public Call<Movies> getMovieList() {
        return retrofitInstance.getListOfMovies(API_KEY);
    }

    public Call<MovieDetails> getMovieDetails(Integer movieId) {
        return retrofitInstance.getMovieDetails(movieId, API_KEY);
    }

}
