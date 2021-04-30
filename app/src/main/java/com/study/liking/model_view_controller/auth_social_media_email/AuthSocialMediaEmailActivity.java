package com.study.liking.model_view_controller.auth_social_media_email;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.databinding.ActivityAuthEmailBinding;
import com.study.liking.utils.Constants;

public class AuthSocialMediaEmailActivity extends BaseActivity implements AuthSocialMediaEmailContract.View {

    private ActivityAuthEmailBinding binding;
    private AuthSocialMediaEmailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AuthSocialMediaEmailPresenter(this);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.background_secondary_color));
        getWindow().setStatusBarColor(getResources().getColor(R.color.background_secondary_color));
    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth_email);
    }

    @Override
    protected void initActions() {
        binding.buttonProceed.setOnClickListener(v -> {
            if (binding.inputtedEmail.getText().toString().trim().length() > 0) {
                //bundles
                Intent intent = new Intent();
                intent.putExtra(Constants.Bundle.USER_EMAIL, binding.inputtedEmail.getText().toString());

                setResult(RESULT_OK, intent);
                this.finish();
            }
            else showToastMessage(getString(R.string.invalid_email));
        });
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}