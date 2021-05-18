package com.study.liking.model_view_controller.registry_user;

import com.study.liking.databinding.ActivityRegistryUserBinding;
import com.study.liking.models.User;

public interface RegistryUserContract {

    interface View {
        void fitUI(User user);
    }

    interface Presenter {
        void saveAndValidateData(ActivityRegistryUserBinding binding) throws Exception;
        void init();
    }
}
