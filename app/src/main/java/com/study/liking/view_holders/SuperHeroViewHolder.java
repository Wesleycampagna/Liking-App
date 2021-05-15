package com.study.liking.view_holders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.study.liking.R;
import com.study.liking.databinding.SuperHeroAdapterBinding;
import com.study.liking.models.SuperHero;
import com.study.liking.utils.Constants;

public class SuperHeroViewHolder extends RecyclerView.ViewHolder {

    private SuperHeroAdapterBinding binding;

    public SuperHeroViewHolder(@NonNull SuperHeroAdapterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    // bind
    public void bind(SuperHero superHero, Context context) {
        if (superHero != null) {
            binding.superHeroName.setText(superHero.name);
            binding.superHeroDescription.setText(!superHero.description.equals("") && superHero.description != null ? superHero.description : context.getString(R.string.no_description));

            binding.superHeroQuantityComics.setText(getQuatitylabel(binding, superHero.quantityComics, context));

            try {
                Glide.with(context)
                        .load(superHero.urlImage + Constants.IMAGE_PATH + superHero.imageExtension)
                        .centerCrop()
                        .into(binding.superHeroAvatar);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        binding.executePendingBindings();
    }

    private String getQuatitylabel(SuperHeroAdapterBinding binding, int quantity, Context context) {
        if (quantity < 20)
            return context.getString(R.string.quantity_comics, quantity);

        return context.getString(R.string.greatest_quantity_comics);
    }

    public SuperHeroAdapterBinding getBinding() {
        return binding;
    }
}
