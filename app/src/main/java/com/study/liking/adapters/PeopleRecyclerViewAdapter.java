package com.study.liking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.liking.databinding.PeopleAdapterViewBinding;
import com.study.liking.listeners.OnCallEditUserListener;
import com.study.liking.listeners.OnRemoveUserListener;
import com.study.liking.models.User;
import com.study.liking.view_holders.PeopleViewHolder;

import java.util.List;

public class PeopleRecyclerViewAdapter extends RecyclerView.Adapter<PeopleViewHolder> {

    private Context context;
    private List<User> users;
    private OnCallEditUserListener onEdit;
    private OnRemoveUserListener onRemove;

    public PeopleRecyclerViewAdapter(Context context, OnCallEditUserListener onEdit, OnRemoveUserListener onRemove) {
        this.context = context;
        this.onEdit = onEdit;
        this.onRemove = onRemove;
    }

    public void setRegisteredPeople(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int view) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PeopleAdapterViewBinding binding = PeopleAdapterViewBinding.inflate(layoutInflater, parent, false);
        return new PeopleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);

        PeopleAdapterViewBinding binding = holder.getBinding();
        setActions(binding, user);
    }

    @Override
    public int getItemCount() {
        return users != null ?  users.size() : 0;
    }

    private void setActions(PeopleAdapterViewBinding binding, final User user) {
        binding.btnEdit.setOnClickListener(v -> goToEditScreen(user));
        binding.btnRemove.setOnClickListener(v -> removeUser(user));
    }

    private void goToEditScreen(User user) {
        onEdit.onUserEdit(user);
    }

    private void removeUser(User user) {
        // 2 ways out
        // remove in database and update in oringin
        onRemove.onRemoveUser(user);

        // remove in database and remove from array here
//        user.delete();
//        users.remove(user);
//        notifyDataSetChanged();
    }
}
