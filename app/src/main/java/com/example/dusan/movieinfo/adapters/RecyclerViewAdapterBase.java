package com.example.dusan.movieinfo.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    List<T> items = new ArrayList<>();
    @Nullable OnItemClickListener<T> clickListener;

    @Override public final ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<>(onCreateItemView(parent, viewType));
    }

    @Override public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    public void setCollection(final List<T> list) {
        items = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.clickListener = listener;
    }


    public interface OnItemClickListener<I> {

        void onItemClick(I item);
    }
}
