package com.study.liking.model_view_controller.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.databinding.ActivityMainBinding;
import com.study.liking.model_view_controller.auth_social_media_email.AuthSocialMediaEmailActivity;
import com.study.liking.model_view_controller.list_component.ListComponentActivity;
import com.study.liking.model_view_controller.registry_user.RegistryUserActivity;
import com.study.liking.utils.Constants;

public class MainActivity extends BaseActivity implements MainContract.View {

    private ActivityMainBinding binding;
    private  MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this);
    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void initActions() {
            binding.proceedToGallery.setOnClickListener(v -> MainActivity.this.goToGallery());
            binding.proceedToRegistering.setOnClickListener(v -> MainActivity.this.goToRegistryUserActivity());
            binding.proceedToFacebookRegistration.setOnClickListener(v -> MainActivity.this.goToAuthSocialMedia());
            binding.proceedToGmailRegistration.setOnClickListener(v -> MainActivity.this.goToAuthSocialMedia());
    }

    private void goToRegistryUserActivity() {
        // bundle
        Intent intent = new Intent(this, RegistryUserActivity.class);
        intent.putExtra(Constants.Bundle.USER_NAME, "Wesley");
        intent.putExtra(Constants.Bundle.USER_LOGIN, "login");
        intent.putExtra(Constants.Bundle.USER_PASSWORD, "password");
        startActivity(intent);
    }

    private void goToGallery() {
        Intent intent = new Intent(this, ListComponentActivity.class);
        // bundle
        intent.putExtra(Constants.Bundle.USER_NAME, "Wesley");
        intent.putExtra(Constants.Bundle.USER_LOGIN, "login");
        intent.putExtra(Constants.Bundle.USER_PASSWORD, "password");
        startActivity(intent);
    }

    private void goToAuthSocialMedia() {
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

    @Override
    public void showToast() {
        new Handler(Looper.myLooper()).post(() -> {
            Toast.makeText(this, getString(R.string.successfuly_save_data), Toast.LENGTH_SHORT).show();
        });
    }
}