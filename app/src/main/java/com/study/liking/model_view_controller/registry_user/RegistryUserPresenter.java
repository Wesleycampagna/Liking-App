package com.study.liking.model_view_controller.registry_user;

import android.content.Context;
import android.os.Bundle;

import com.study.liking.R;
import com.study.liking.databinding.ActivityRegistryUserBinding;
import com.study.liking.exceptions.CpfException;
import com.study.liking.exceptions.DateParseLocalException;
import com.study.liking.exceptions.FieldFormException;
import com.study.liking.exceptions.SprinklesNotSaveException;
import com.study.liking.models.User;
import com.study.liking.utils.Constants;
import com.study.liking.utils.MaskUtils;
import com.study.liking.utils.Utils;

public class RegistryUserPresenter implements RegistryUserContract.Presenter {

    private RegistryUserContract.View view;
    private Context context;
    private User editUser;
    private Bundle bundle;

    public RegistryUserPresenter(RegistryUserContract.View view, Context context, Bundle bundle) {
        this.view = view;
        this.context = context;
        this.bundle = bundle;
    }

    @Override
    public void init() {
        if (bundle != null) {
            editUser = (User) bundle.getSerializable(Constants.Bundle.USER_EDIT);
            if (editUser != null)
                view.fitUI(editUser);
        }
    }

    @Override
    public void saveAndValidateData(ActivityRegistryUserBinding binding) throws Exception {
        // check if all fields is filled
        this.validateFields(binding);
        String cpf = MaskUtils.unmask(binding.editTextCpf.getText().toString());
        User user;

        // check if this is a user to edit or a new user
        if (editUser == null) {
            // check if is a valid register -> by CPF
            boolean existsCpf = User.isValidUser(cpf);
            if(existsCpf) throw new CpfException(context.getString(R.string.cpf_already_exists));
            user = new User();
        }
        else {
            user = editUser;
        }

        user.avatar = "";
        user.cpf = cpf;
        user.email = binding.editTextEmail.getText().toString();
        user.birthDate = MaskUtils.unmask(binding.editTextDateBorn.getText().toString());
        user.name = MaskUtils.unmask(binding.editTextName.getText().toString());
        user.lastName = MaskUtils.unmask(binding.editTextLastName.getText().toString());
        user.phoneNumber = MaskUtils.unmask(binding.editTextPhone.getText().toString());

        this.validateUserData(user);

        boolean saved = user.save();
        if (!saved) throw new SprinklesNotSaveException("Os dados não foram salvos!");
    }

    private void validateUserData(User user) throws FieldFormException, DateParseLocalException {
        if (!Utils.isCpfValid(user.cpf, true))
            throw new FieldFormException("Cpf é inválido!");

        if (Utils.isBirthDateInvalid(user.birthDate))
            throw new FieldFormException("Data de nascimento é inválida!");

        if (!Utils.isPhoneValid(user.phoneNumber))
            throw new FieldFormException("telefone é inválido!");
    }

    private void validateFields(ActivityRegistryUserBinding binding) throws Exception {
        if (binding.editTextName.getText().toString().equals("") || binding.editTextName.getText().toString() == null)
            throw new FieldFormException("Nome não pode ser vazio!");

        if (binding.editTextLastName.getText().toString().equals("") || binding.editTextLastName.getText().toString() == null)
            throw new FieldFormException("Sobrenome não pode ser vazio!");

        if (binding.editTextDateBorn.getText().toString().equals("") || binding.editTextDateBorn.getText().toString() == null)
            throw new FieldFormException("Data de nascimento não pode ser vazio!");

        if (binding.editTextPhone.getText().toString().equals("") || binding.editTextPhone.getText().toString() == null)
            throw new FieldFormException("Número de telephone não pode ser vazio!");

        if (binding.editTextCpf.getText().toString().equals("") || binding.editTextCpf.getText().toString() == null)
            throw new FieldFormException("CPF não pode ser vazio!");

        if (binding.editTextEmail.getText().toString().equals("") || binding.editTextEmail.getText().toString() == null)
            throw new FieldFormException("Email não pode ser vazio!");
    }
}
