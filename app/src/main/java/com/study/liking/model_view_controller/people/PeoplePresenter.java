package com.study.liking.model_view_controller.people;

import android.content.Context;

import com.study.liking.listeners.OnTextChangeListener;
import com.study.liking.models.User;

import java.util.ArrayList;
import java.util.List;

public class PeoplePresenter implements PeopleContract.Presenter {

    private Context context;
    private PeopleContract.View view;

    public PeoplePresenter(Context context, PeopleContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void init() {
        view.initRecyclerView();
        this.updateRecyclerView();
        view.initSearchComponent();
    }

    @Override
    public void onRemoveUser(User user) {
        user.delete();
        updateRecyclerView();
    }

    @Override
    public void updateRecyclerView() {
        new Thread(() -> {
            // load data from database
            List<User> users = User.getAllUsers();

            // update recyclerView
            if (users.size() > 0 && users != null) {
                view.updateRecyclerView(users);
            }
        }).start();
    }

    @Override
    public void filterData(String text) {
        new Thread(() -> {
            // load data from database
            List<User> users = User.filterUsersBy(text);

            // update recyclerView
            if (users.size() > 0 && users != null) {
                view.updateRecyclerView(users);
            }
            else view.updateRecyclerView(new ArrayList<>());
        }).start();
    }
}
