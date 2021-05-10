package com.study.liking.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.liking.view_holders.SuperHeroViewHolder;

public class SuperHeroRecyclerViewAdapter extends RecyclerView.Adapter<SuperHeroViewHolder> {

    private Context context;

    public SuperHeroRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SuperHeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SuperHeroViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
