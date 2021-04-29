package com.study.liking.model_view_controller.auth_social_media_email;

public class AuthSocialMediaEmailPresenter implements AuthSocialMediaEmailContract.Presenter {
    private AuthSocialMediaEmailContract.View view;

    public AuthSocialMediaEmailPresenter(AuthSocialMediaEmailContract.View view) {
        this.view = view;
    }
}
