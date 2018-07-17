package com.example.dusan.movieinfo.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dusan.movieinfo.R;
import com.example.dusan.movieinfo.data.model.Movie;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@EViewGroup(R.layout.view_item_movie) public class MovieItemView extends RelativeLayout {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";

    @ViewById TextView movieName;
    @ViewById TextView movieRating;
    @ViewById TextView movieOverview;
    @ViewById ImageView moviePoster;

    public MovieItemView(Context context) {
        super(context);
    }

    public MovieItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(Movie movie) {
        this.movieName.setText(movie.getName());
        this.movieRating.setText(movie.getRating());
        this.movieOverview.setText(movie.getOverview());
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            new DownloadImageTask(moviePoster, movie.getImageUrl()).execute();
        } else {
            moviePoster.setImageResource(R.mipmap.placeholder);
        }
    }

    @SuppressLint("StaticFieldLeak") private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView moviePoster;
        String url;

        DownloadImageTask(ImageView moviePoster, String url) {
            this.moviePoster = moviePoster;
            this.url = url;
        }

        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(IMAGE_BASE_URL.concat(url)).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            moviePoster.setImageBitmap(result);
        }
    }
}
