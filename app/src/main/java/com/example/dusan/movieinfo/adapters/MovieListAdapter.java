package com.example.dusan.movieinfo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.dusan.movieinfo.views.MovieItemView;
import com.example.dusan.movieinfo.data.model.Movie;
import com.example.dusan.movieinfo.views.MovieItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class MovieListAdapter extends RecyclerViewAdapterBase<Movie, MovieItemView> {

    @RootContext Context context;

    @Override protected MovieItemView onCreateItemView(ViewGroup parent, int viewType) {
        return MovieItemView_.build(context);
    }

    @Override public void onBindViewHolder(@NonNull ViewWrapper<MovieItemView> holder, int position) {
        MovieItemView movieItemView = holder.getView();
        Movie movie = items.get(position);
        movieItemView.setOnClickListener(view -> {
            if (clickListener != null) {
                clickListener.onItemClick(movie);
            }
        });
        movieItemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        movieItemView.bind(movie);
    }

    @Override public int getItemViewType(int position) {
        return position;
    }
}