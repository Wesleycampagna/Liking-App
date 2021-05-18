package com.study.liking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.liking.R;
import com.study.liking.databinding.SuperHeroAdapterBinding;
import com.study.liking.listeners.OnRemoveSuperHeroListener;
import com.study.liking.models.SuperHero;
import com.study.liking.view_holders.SuperHeroViewHolder;

import java.util.List;

public class SuperHeroRecyclerViewAdapter extends RecyclerView.Adapter<SuperHeroViewHolder> {

    private Context context;
    private OnRemoveSuperHeroListener onHeroInfo;
    private List<SuperHero> superHeroes;
    private boolean canScroll;

    public SuperHeroRecyclerViewAdapter(Context context, OnRemoveSuperHeroListener onHeroInfo) {
        this.context = context;
        this.onHeroInfo = onHeroInfo;
        this.canScroll = true;
    }

    public void setSuperHeroes(List<SuperHero> superHeroes) {
        this.superHeroes = superHeroes;
    }

    public void setState(boolean canScroll) {
        this.canScroll = canScroll;
    }

    @NonNull
    @Override
    public SuperHeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SuperHeroAdapterBinding binding = SuperHeroAdapterBinding.inflate(layoutInflater, parent, false);
        return new SuperHeroViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperHeroViewHolder holder, int position) {
        SuperHero hero = superHeroes.get(position);
        holder.bind(hero, context);

        SuperHeroAdapterBinding binding = holder.getBinding();
        binding.imageView.setImageResource(R.drawable.ic_baseline_info_24);
        setActions(binding, hero);
    }

    @Override
    public int getItemCount() {
        return superHeroes != null ? superHeroes.size() : 0;
    }

    private void setActions(SuperHeroAdapterBinding binding, SuperHero superHero) {
        binding.btnInfo.setOnClickListener(v -> onHeroInfo.onInfo(superHero));
        binding.btnAddRemoveSuperHero.setOnClickListener(v -> removeSuperHero(superHero));
    }

    private void removeSuperHero(SuperHero superHero) {
        superHero.delete();
        superHeroes.remove(superHero);
        notifyDataSetChanged();
    }
}
