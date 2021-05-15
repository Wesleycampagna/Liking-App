package com.study.liking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.liking.api.responses.character_data_wrapper.response.CharacterDataContainerResponse;
import com.study.liking.api.responses.character_data_wrapper.response.CharacterResponse;
import com.study.liking.databinding.SuperHeroAdapterBinding;
import com.study.liking.listeners.OnAddSuperHeroListListener;
import com.study.liking.models.SuperHero;
import com.study.liking.view_holders.SuperHeroListViewHolder;

import java.util.HashMap;

public class SuperHeroListRecyclerViewAdapter extends RecyclerView.Adapter<SuperHeroListViewHolder> {

    private Context context;
    private OnAddSuperHeroListListener onAdd;
    private CharacterDataContainerResponse response;
    private HashMap<Long, SuperHero> heroes;

    public SuperHeroListRecyclerViewAdapter(Context context, OnAddSuperHeroListListener onAdd) {
        this.context = context;
        this.onAdd = onAdd;
    }

    public void setSuperHeroResponse(CharacterDataContainerResponse response, HashMap<Long, SuperHero> heroes) {
        this.response = response;
        this.heroes = heroes;
    }

    @NonNull
    @Override
    public SuperHeroListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SuperHeroAdapterBinding binding = SuperHeroAdapterBinding.inflate(layoutInflater, parent, false);
        return new SuperHeroListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperHeroListViewHolder holder, int position) {
        CharacterResponse heros = response.results.get(position);
        holder.bind(heros, context);

        SuperHeroAdapterBinding binding = holder.getBinding();
        binding.btnRemoveSuperHero.setVisibility(View.GONE);
        ((ViewGroup.MarginLayoutParams) binding.btnAddEditSuperHero.getLayoutParams()).setMarginEnd(0);

        setActions(binding, heros);
    }

    @Override
    public int getItemCount() {
        return response != null ? response.count : 0;
    }

    private void setActions(SuperHeroAdapterBinding binding, CharacterResponse superHero) {
        if (!heroes.containsKey(superHero.id))
            binding.btnAddEditSuperHero.setOnClickListener(v -> onAdd.onAdd(superHero));
    }
}
