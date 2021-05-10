package com.study.liking.view_holders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.liking.databinding.SuperHeroAdapterBinding;

public class SuperHeroViewHolder extends RecyclerView.ViewHolder {

    private SuperHeroAdapterBinding binding;

    public SuperHeroViewHolder(@NonNull SuperHeroAdapterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    // bind TODO-wesley

    public SuperHeroAdapterBinding getBinding() {
        return binding;
    }
}
