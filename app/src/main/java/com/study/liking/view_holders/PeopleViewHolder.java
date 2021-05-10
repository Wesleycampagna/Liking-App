package com.study.liking.view_holders;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.study.liking.databinding.PeopleAdapterViewBinding;
import com.study.liking.models.User;
import com.study.liking.utils.FormatUtils;

public class PeopleViewHolder extends RecyclerView.ViewHolder {

    PeopleAdapterViewBinding binding;

    public PeopleViewHolder(@NonNull PeopleAdapterViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(User user) {
        binding.userName.setText(user.concatName());
        binding.userEmail.setText(user.email);
        binding.userCpf.setText(FormatUtils.formatCpf(user.cpf));

        binding.executePendingBindings();
    }

    public PeopleAdapterViewBinding getBinding() {
        return binding;
    }
}
