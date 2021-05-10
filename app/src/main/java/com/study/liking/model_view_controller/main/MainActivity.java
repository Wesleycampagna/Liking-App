package com.study.liking.model_view_controller.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.databinding.ActivityMainBinding;
import com.study.liking.model_view_controller.auth_social_media_email.AuthSocialMediaEmailActivity;
import com.study.liking.model_view_controller.list_super_hero.ListSuperHeroActivity;
import com.study.liking.model_view_controller.people.PeopleActivity;
import com.study.liking.models.OwnUser;
import com.study.liking.utils.Constants;

public class MainActivity extends BaseActivity implements MainContract.View {

    private ActivityMainBinding binding;
    private MainContract.Presenter presenter;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this, this);
        presenter.init();

    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void loadInterface(OwnUser ownUser) {
        if (ownUser != null) {
            binding.saveCredentials.setChecked(ownUser.saveCredentials);
            binding.editTextName.setText(ownUser.name);
            binding.editTextLastName.setText(ownUser.lastName);
            binding.login.setText(ownUser.login);
        }
    }

    @Override
    public void setEmail(String email) {
        new Handler(getMainLooper()).post(() -> {
           this.email = email;
        });
    }

    @Override
    protected void initActions() {
            binding.proceedToGallery.setOnClickListener(v -> MainActivity.this.goToGallery());
            binding.proceedToRegistering.setOnClickListener(v -> MainActivity.this.goToRegistryUserActivity());
            binding.proceedToFacebookRegistration.setOnClickListener(v -> MainActivity.this.goToAuthSocialMediaOrEmail());
            binding.proceedToGmailRegistration.setOnClickListener(v -> MainActivity.this.goToAuthSocialMediaOrEmail());
    }

    private OwnUser getScreenAtributtes() {
        OwnUser ownUser = new OwnUser();
        ownUser.name = binding.editTextName.getText().toString().trim();
        ownUser.lastName = binding.editTextLastName.getText().toString().trim();
        ownUser.login = binding.login.getText().toString().trim();
        ownUser.password = binding.password.getText().toString().trim();
        ownUser.saveCredentials = binding.saveCredentials.isChecked();

        return ownUser;
    }

    private void goToRegistryUserActivity() {
        OwnUser ownUser = getScreenAtributtes();

        if (presenter.isValidData(ownUser)) {
            // bundle
            Intent intent = new Intent(this, PeopleActivity.class);
            startActivity(intent);
        }
        else showToast(getString(R.string.caught_invalid_input));
    }

    private void goToGallery() {
        OwnUser ownUser = getScreenAtributtes();

        if (presenter.isValidData(ownUser)) {
            Intent intent = new Intent(this, ListSuperHeroActivity.class);
            // bundle
            intent.putExtra(Constants.Bundle.USER_NAME, ownUser.name);
            intent.putExtra(Constants.Bundle.USER_LOGIN, ownUser.login);
            intent.putExtra(Constants.Bundle.USER_EMAIL, this.email);
            startActivity(intent);
        }
        else showToast(getString(R.string.caught_invalid_input));
    }

    private void goToAuthSocialMediaOrEmail() {
        Intent intent = new Intent(this, AuthSocialMediaEmailActivity.class);
        startActivityForResult(intent, Constants.RequestCodes.REQUEST_AUTH_SOCIAL_MEDIA_EMAIL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.RequestCodes.REQUEST_AUTH_SOCIAL_MEDIA_EMAIL == requestCode) {
            if (resultCode == RESULT_OK) {
                presenter.saveData(data);
            }
        }
    }
}