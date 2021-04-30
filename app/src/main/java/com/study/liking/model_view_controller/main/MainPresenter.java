package com.study.liking.model_view_controller.main;

import android.content.Context;
import android.content.Intent;

import com.study.liking.R;
import com.study.liking.models.OwnUser;
import com.study.liking.utils.Constants;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private Context context;

    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void init() {
        this.view.loadInterface(OwnUser.getCredentials());
    }

    @Override
    public void saveData(Intent data) {
        // save in sprinkles
        OwnUser ownUserDataBase = OwnUser.getCredentials();

        if(ownUserDataBase == null)
            ownUserDataBase = new OwnUser();

        ownUserDataBase.email = data.getStringExtra(Constants.Bundle.USER_EMAIL);
        boolean saved = ownUserDataBase.save();

        if (saved) this.view.showToast(context.getString(R.string.successfuly_save_data));
        else this.view.showToast(context.getString(R.string.something_is_wrong));
    }

    @Override
    public boolean isValidData(OwnUser ownUser) {
        OwnUser ownUserDataBase = OwnUser.getCredentials();

        if(ownUserDataBase == null)
            return saveOwnUser(ownUser);

        if (ownUser.isUserNotFilled())
            return saveOwnUser(ownUser);
        return ownUser.checkCredentials(ownUserDataBase);
    }

    private boolean saveOwnUser(OwnUser ownUser) {
        if (isNotEmpty(ownUser)) {
            ownUser.firstAccess = false;
            ownUser.save();
            return true;
        }
        return false;
    }

    private boolean isNotEmpty(OwnUser ownUser) {
        return  ownUser.name != null && ownUser.name.length() > 0 &&
                ownUser.lastName != null && ownUser.lastName.length() > 0 &&
                ownUser.login != null && ownUser.login.length() > 0 &&
                ownUser.password != null && ownUser.password.length() > 0;
    }
}
