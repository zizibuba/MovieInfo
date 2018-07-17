package com.example.dusan.movieinfo.rest;

import com.example.dusan.movieinfo.data.model.Movie;
import com.example.dusan.movieinfo.data.model.MovieDetails;
import com.example.dusan.movieinfo.data.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApiService {

    @GET("movie/top_rated") Call<Movies> getListOfMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/credits") Call<MovieDetails> getMovieDetails(@Path("movie_id") int id, @Query("api_key") String apiKey);

}
