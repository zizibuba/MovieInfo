package com.example.dusan.movieinfo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.dusan.movieinfo.adapters.MovieListAdapter;
import com.example.dusan.movieinfo.adapters.RecyclerViewAdapterBase;
import com.example.dusan.movieinfo.data.model.Movie;
import com.example.dusan.movieinfo.data.model.Movies;
import com.example.dusan.movieinfo.rest.RestClient;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements RecyclerViewAdapterBase.OnItemClickListener<Movie> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private RestClient restClient;
    @ViewById RecyclerView movieList;
    @Bean MovieListAdapter movieListAdapter;

    @AfterViews public void initializeViews() {
        restClient = new RestClient();
        initMovieList();
    }

    private void initMovieList() {
        movieList.setLayoutManager(new LinearLayoutManager(this));
        movieList.setAdapter(movieListAdapter);
        movieListAdapter.setOnItemClickListener(MainActivity.this);
        restClient.getMovieList().enqueue(new Callback<Movies>() {
            @Override public void onResponse(@NonNull Call<Movies> call, @NonNull Response<Movies> response) {
                movieListAdapter.setCollection(response.body().getMovieList());
            }

            @Override public void onFailure(@NonNull Call<Movies> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override public void onItemClick(Movie item) {
        Log.d(LOG_TAG, "onItemClick: " + item.getId());
        DetailsActivity_.intent(this).selectedMovie(item).start();
    }
}
