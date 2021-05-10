package com.study.liking.model_view_controller.registry_user;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.databinding.ActivityRegistryUserBinding;
import com.study.liking.models.User;
import com.study.liking.utils.MaskUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegistryUserActivity extends BaseActivity implements RegistryUserContract.View {

    private ActivityRegistryUserBinding binding;
    private RegistryUserContract.Presenter presenter;
    private Date dateCalendarPicker;
    private boolean isSaving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RegistryUserPresenter(this, this, getIntent().getExtras());
        presenter.init();
    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registry_user);
    }

    @Override
    protected void initActions() {
        binding.btnRegister.setOnClickListener(v -> saveData());
        binding.btnCalendar.setOnClickListener(v -> openCalendar());
        binding.calendarView.buttonSelectDate.setOnClickListener(v -> saveDateFromCalendar());
        binding.editTextPhone.addTextChangedListener(MaskUtils.insert(MaskUtils.MaskType.TEL, binding.editTextPhone));
        binding.editTextCpf.addTextChangedListener(MaskUtils.insert(MaskUtils.MaskType.CPF, binding.editTextCpf));
        binding.editTextDateBorn.addTextChangedListener(MaskUtils.insert(MaskUtils.MaskType.DATE, binding.editTextDateBorn));
        binding.calendarView.calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> getCalendarPick(year, month, dayOfMonth));
    }

    private void getCalendarPick(int year, int month, int dayOfMonth) {
        isSaving = true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        dateCalendarPicker = calendar.getTime();
        isSaving = false;
    }

    private void saveData() {
        // save in the database -> checking if the value is repeated
        boolean isValid = false;
        try {
            presenter.saveAndValidateData(binding);
            finish();
        } catch (Exception e) {
            showToast(e.getMessage());
        }
    }

    private void openCalendar() {
        // put result on calendar
        hideKeyboard(this);
        Date date = getInitialDate();
        binding.calendarView.calendar.setDate(date.getTime());
        binding.calendarView.calendarLayout.setVisibility(View.VISIBLE);
    }

    private Date getInitialDate() {
        if (!binding.editTextDateBorn.getText().toString().equals("") && binding.editTextDateBorn.getText().toString() != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                return sdf.parse(binding.editTextDateBorn.getText().toString());
            }
            catch (Exception e) {
                return actualDate();
            }
        }
        else return actualDate();
    }

    private Date actualDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    private void saveDateFromCalendar() {
        binding.calendarView.calendarLayout.setVisibility(View.GONE);
        Date calendarDate = dateCalendarPicker != null ? dateCalendarPicker : getInitialDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String date = dateFormat.format(calendarDate.getTime());

        // format to correct label
        binding.editTextDateBorn.setText(date);
    }

    @Override
    public void fitUI(User user) {
        binding.editTextEmail.setText(user.email);
        binding.editTextDateBorn.setText(user.birthDate);
        binding.editTextName.setText(user.name);
        binding.editTextLastName.setText(user.lastName);
        binding.editTextPhone.setText(user.phoneNumber);
        binding.editTextCpf.setText(user.cpf);
    }
}