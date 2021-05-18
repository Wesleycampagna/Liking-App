package com.study.liking.view_holders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.study.liking.R;
import com.study.liking.api.responses.character_data_wrapper.response.CharacterResponse;
import com.study.liking.databinding.SuperHeroAdapterBinding;
import com.study.liking.utils.Constants;

public class SuperHeroListViewHolder extends RecyclerView.ViewHolder {

    private SuperHeroAdapterBinding binding;

    public SuperHeroListViewHolder(@NonNull SuperHeroAdapterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    // bind
    public void bind(CharacterResponse superHero, Context context) {
        if (superHero != null) {
            binding.superHeroName.setText(superHero.name);
            binding.superHeroDescription.setText(!superHero.description.equals("") && superHero.description != null ? superHero.description : context.getString(R.string.no_description));

            if (superHero.comics != null && superHero.comics.items != null)
                binding.superHeroQuantityComics.setText(getQuatitylabel(binding, superHero.comics.items.size(), context));

            try {
                Glide.with(context)
                        .load(superHero.thumbnail.path + Constants.IMAGE_PATH + superHero.thumbnail.extension)
                        .centerCrop()
                        .into(binding.superHeroAvatar);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            // It could be done that way, but...
//            new Thread(() -> {
//                // get image
//
//                // on receceived run at UiThread
//                ((Activity) context).runOnUiThread(() -> {
//                    // load information
//                });
//            }).start();

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
