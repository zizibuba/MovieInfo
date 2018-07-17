package com.example.dusan.movieinfo.rest;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static RestApiService retrofit;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static RestApiService getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RestApiService.class);
        }
        return retrofit;
    }
}
