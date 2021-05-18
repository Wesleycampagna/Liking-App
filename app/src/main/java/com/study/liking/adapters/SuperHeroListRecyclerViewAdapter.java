package com.study.liking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.liking.R;
import com.study.liking.api.responses.character_data_wrapper.response.CharacterDataContainerResponse;
import com.study.liking.api.responses.character_data_wrapper.response.CharacterResponse;
import com.study.liking.databinding.SuperHeroAdapterBinding;
import com.study.liking.listeners.OnInfoListSuperHeroListener;
import com.study.liking.listeners.OnListSuperHeroAdd;
import com.study.liking.models.SuperHero;
import com.study.liking.view_holders.SuperHeroListViewHolder;

import java.util.HashMap;

public class SuperHeroListRecyclerViewAdapter extends RecyclerView.Adapter<SuperHeroListViewHolder> {

    private Context context;
    private OnInfoListSuperHeroListener heroInfo;
    private CharacterDataContainerResponse response;
    private HashMap<Long, SuperHero> heroes;
    private OnListSuperHeroAdd addAction;

    public SuperHeroListRecyclerViewAdapter(Context context, OnInfoListSuperHeroListener heroInfo, OnListSuperHeroAdd addAction) {
        this.context = context;
        this.heroInfo = heroInfo;
        this.addAction = addAction;
    }

    public void setSuperHeroResponse(CharacterDataContainerResponse response, HashMap<Long, SuperHero> heroes) {
        this.response = response;
        this.heroes = heroes;
    }

    public void addSuperHeroResponse(CharacterDataContainerResponse response, HashMap<Long, SuperHero> heroes) {
        this.response.results.addAll(response.results);
        this.heroes = heroes;
    }

    public int getQuantity() {
        return this.response.results.size();
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
        binding.imageIconAddRemove.setImageResource(R.drawable.ic_add_button);
//        ((ViewGroup.MarginLayoutParams) binding.btnInfo.getLayoutParams()).setMarginEnd(0); save this! could help tomorrow

        setActions(binding, heros);
    }

    @Override
    public int getItemCount() {
        return response != null && response.results != null ? response.results.size() : 0;
    }

    private void setActions(SuperHeroAdapterBinding binding, CharacterResponse superHero) {
        if (!heroes.containsKey(superHero.id)) {
            binding.btnAddRemoveSuperHero.setOnClickListener(v -> addAction.onAdd(superHero));
        }
        binding.btnInfo.setOnClickListener(v -> heroInfo.onOpenInfo(superHero));
    }
}
