package com.example.dusan.movieinfo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.dusan.movieinfo.views.CrewItemView;
import com.example.dusan.movieinfo.data.model.CrewMember;
import com.example.dusan.movieinfo.views.CrewItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class CrewListAdapter extends RecyclerViewAdapterBase<CrewMember, CrewItemView> {

    @RootContext Context context;

    @Override public CrewItemView onCreateItemView(ViewGroup parent, int viewType) {
        return CrewItemView_.build(context);
    }

    @Override public void onBindViewHolder(@NonNull ViewWrapper<CrewItemView> holder, int position) {
        CrewItemView crewItemView = holder.getView();
        CrewMember crewMember = items.get(position);
        crewItemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        crewItemView.bind(crewMember);
    }

    @Override public int getItemViewType(int position) {
        return position;
    }
}