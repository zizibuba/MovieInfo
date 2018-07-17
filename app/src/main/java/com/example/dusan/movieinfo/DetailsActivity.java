package com.example.dusan.movieinfo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dusan.movieinfo.adapters.CrewListAdapter;
import com.example.dusan.movieinfo.data.model.Movie;
import com.example.dusan.movieinfo.data.model.MovieDetails;
import com.example.dusan.movieinfo.rest.RestClient;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_details)
public class DetailsActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailsActivity.class.getSimpleName();
    private RestClient restClient;
    @Extra Movie selectedMovie;
    @ViewById TextView movieGenre;
    @ViewById TextView movieRating;
    @ViewById TextView movieOverview;
    @ViewById RecyclerView crewList;
    @Bean CrewListAdapter crewListAdapter;

    @AfterViews public void initializeViews() {
        restClient = new RestClient();
        initCrewList();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(selectedMovie.getName());

        this.movieGenre.setText(getGenre(selectedMovie.getGenreIds()));
        this.movieRating.setText(selectedMovie.getRating());
        this.movieOverview.setText(selectedMovie.getOverview());
    }

    @OptionsItem(android.R.id.home) void backPressed() {
        finish();
    }

    private void initCrewList() {
        crewList.setLayoutManager(new LinearLayoutManager(this));
        crewList.setAdapter(crewListAdapter);
        restClient.getMovieDetails(selectedMovie.getId()).enqueue(new Callback<MovieDetails>() {
            @Override public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {
                crewListAdapter.setCollection(response.body().getCrewList());
            }

            @Override public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t) {
                Toast.makeText(DetailsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getGenre(List<Integer> genreIds) {
        String genres = "";
        for (int i : genreIds) {
            switch (i) {
                case 35: {
                    genres = genres.concat(getString(R.string.comedy));
                    break;
                }
                case 18: {
                    genres = genres.concat(getString(R.string.drama));
                    break;
                }
                case 10749: {
                    genres = genres.concat(getString(R.string.romance));
                    break;
                }
                case 28: {
                    genres = genres.concat(getString(R.string.action));
                    break;
                }
                case 16: {
                    genres = genres.concat(getString(R.string.animated));
                    break;
                }
                case 99: {
                    genres = genres.concat(getString(R.string.documentary));
                    break;
                }
                case 10751: {
                    genres = genres.concat(getString(R.string.family));
                    break;
                }
                case 14: {
                    genres = genres.concat(getString(R.string.fantasy));
                    break;
                }
                case 36: {
                    genres = genres.concat(getString(R.string.history));
                    break;
                }
                case 10752: {
                    genres = genres.concat(getString(R.string.war));
                    break;
                }
                case 80: {
                    genres = genres.concat(getString(R.string.crime));
                    break;
                }
                case 10402: {
                    genres = genres.concat(getString(R.string.music));
                    break;
                }
                case 9648: {
                    genres = genres.concat(getString(R.string.mystery));
                    break;
                }
                case 878: {
                    genres = genres.concat(getString(R.string.scifi));
                    break;
                }
                case 27: {
                    genres = genres.concat(getString(R.string.horror));
                    break;
                }
                case 10770: {
                    genres = genres.concat(getString(R.string.tvmovie));
                    break;
                }
                case 53: {
                    genres = genres.concat(getString(R.string.thriller));
                    break;
                }
                case 37: {
                    genres = genres.concat(getString(R.string.western));
                    break;
                }
                case 12: {
                    genres = genres.concat(getString(R.string.adventure));
                    break;
                }
            }
        }
        return genres;
    }

}
