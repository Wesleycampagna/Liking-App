package com.study.liking.model_view_controller.people;

import com.study.liking.listeners.OnTextChangeListener;
import com.study.liking.models.User;

import java.util.List;

public interface PeopleContract {

    interface View {
        void initRecyclerView();
        void updateRecyclerView(List<User> users);
        void initSearchComponent();
    }

    interface Presenter extends OnTextChangeListener {
        void init();
        void onRemoveUser(User user);
        void updateRecyclerView();
        void filterData(String text);
    }
}
