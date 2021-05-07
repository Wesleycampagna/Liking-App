package com.study.liking.model_view_controller.people;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.study.liking.BuildConfig;
import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.databinding.ActivityPeopleBinding;
import com.study.liking.model_view_controller.list_super_hero.ListSuperHeroActivity;
import com.study.liking.model_view_controller.main.MainActivity;
import com.study.liking.model_view_controller.registry_user.RegistryUserActivity;
import com.study.liking.utils.Constants;

public class PeopleActivity extends BaseActivity {

    private ActivityPeopleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_people);
    }

    @Override
    protected void initActions() {

    }

    private void goToRegistryPerson() {
        Intent intent = new Intent(this, RegistryUserActivity.class);
        startActivity(intent);
    }

    private void goBack() {
        finish();
    }
}