package com.study.liking.model_view_controller.people;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.adapters.PeopleRecyclerViewAdapter;
import com.study.liking.databinding.ActivityPeopleBinding;
import com.study.liking.listeners.OnCallEditUserListener;
import com.study.liking.listeners.OnRemoveUserListener;
import com.study.liking.model_view_controller.registry_user.RegistryUserActivity;
import com.study.liking.models.User;
import com.study.liking.utils.Constants;
import com.study.liking.utils.TextUtils;

import java.util.List;

public class PeopleActivity extends BaseActivity implements PeopleContract.View, OnCallEditUserListener, OnRemoveUserListener {

    private ActivityPeopleBinding binding;
    private Animation fromBottom;
    private Animation toBottom;
    private boolean clicked;
    private PeopleContract.Presenter presenter;
    private PeopleRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PeoplePresenter(this, this);
        presenter.init();
    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_people);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);
    }

    @Override
    protected void initActions() {
        binding.fabDefault.setOnClickListener(v -> setFabVisible());
        binding.fabAdd.setOnClickListener(v -> goToRegistryPerson(null));
        binding.btnGoBack.setOnClickListener(v -> goBack());
    }

    @Override
    public void initSearchComponent() {
        binding.searchUser.addTextChangedListener(TextUtils.searchTextWatcherListener(binding.searchUser, presenter));
    }

    private void setFabVisible() {
        if (!clicked) {
            clicked = true;
            binding.fabAdd.setVisibility(View.VISIBLE);
            setFabAnimation(fromBottom);
        } else {
            clicked = false;
            binding.fabAdd.setVisibility(View.GONE);
            setFabAnimation(toBottom);
        }
    }

    private void setFabAnimation(Animation animation) {
        binding.fabAdd.startAnimation(animation);
    }

    private void goToRegistryPerson(Bundle bundle) {
        Intent intent = new Intent(this, RegistryUserActivity.class);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
    }

    private void goBack() {
        finish();
    }

    @Override
    public void initRecyclerView() {
        adapter = new PeopleRecyclerViewAdapter(this, this, this);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.setAdapter(adapter);
        RecyclerView recyclerView = binding.recycler;
    }

    @Override
    public void updateRecyclerView(List<User> users) {
        runOnUiThread(() -> {
            adapter.setRegisteredPeople(users);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.updateRecyclerView();
    }

    @Override
    public void onUserEdit(User user) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.Bundle.USER_EDIT, user);
        goToRegistryPerson(bundle);
    }

    @Override
    public void onRemoveUser(User user) {
        this.presenter.onRemoveUser(user);
    }
}