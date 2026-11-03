package com.itismob.s03.group2.KaBarangay.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.itismob.s03.group2.KaBarangay.R;

import java.util.Calendar;
import java.util.Locale;

public class ComplaintActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etPhone, etEmail, etIncidentDate, etDescription;
    private Spinner spComplaintType;
    private CheckBox cbDataPrivacy;
    private TextView tvCharCounter, btnBack, btnCallHotline;
    private ImageView btnClear;
    private RelativeLayout btnDatePicker;
    private MaterialButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        initViews();
        setupSpinner();
        setupListeners();
    }

    private void initViews() {
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etIncidentDate = findViewById(R.id.etIncidentDate);
        etDescription = findViewById(R.id.etDescription);
        spComplaintType = findViewById(R.id.spComplaintType);
        cbDataPrivacy = findViewById(R.id.cbDataPrivacy);
        tvCharCounter = findViewById(R.id.tvCharCounter);
        btnBack = findViewById(R.id.btnBack);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCallHotline = findViewById(R.id.btnCallHotline);
    }

    private void setupSpinner() {
        String[] categories = new String[]{
                "Pumili ng uri ng reklamo...",
                "Ingay at Estorbo (Noise Disturbance)",
                "Away Pamilya o Kapitbahay (Neighborhood Dispute)",
                "Araw-araw na Kalinisan (Sanitation & Garbage)",
                "Sakit sa Hayop / Pagsaway (Pet Nuisance)",
                "Iba pa (Others)"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                categories
        );
        spComplaintType.setAdapter(adapter);
    }

    private void setupListeners() {
        // Back navigation
        btnBack.setOnClickListener(v -> finish());

        // Date Picker Trigger
        btnDatePicker.setOnClickListener(v -> showDatePicker());
        etIncidentDate.setOnClickListener(v -> showDatePicker());

        // Character counter for narrative description (800 max)
        etDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int currentLength = s.length();
                tvCharCounter.setText(currentLength + " / 800");
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Submit Action
        btnSubmit.setOnClickListener(v -> validateAndSubmit());

        // Emergency Hotline Direct Dialer
        btnCallHotline.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:8700112"));
            startActivity(callIntent);
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, yearSelected, monthSelected, daySelected) -> {
                    String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d",
                            monthSelected + 1, daySelected, yearSelected);
                    etIncidentDate.setText(formattedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void validateAndSubmit() {
        if (etFirstName.getText().toString().trim().isEmpty()) {
            etFirstName.setError("Required");
            return;
        }
        if (etLastName.getText().toString().trim().isEmpty()) {
            etLastName.setError("Required");
            return;
        }
        if (etPhone.getText().toString().trim().isEmpty()) {
            etPhone.setError("Required");
            return;
        }
        if (etIncidentDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Pumili ng petsa ng pangyayari", Toast.LENGTH_SHORT).show();
            return;
        }
        if (spComplaintType.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Pumili ng uri ng reklamo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etDescription.getText().toString().trim().isEmpty()) {
            etDescription.setError("Maglagay ng salaysay");
            return;
        }
        if (!cbDataPrivacy.isChecked()) {
            Toast.makeText(this, "Kailangan mong lagyan ng tsek ang Data Privacy certification", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Naipasa nang matagumpay ang iyong reklamo!", Toast.LENGTH_LONG).show();
        finish();
    }

    private void resetForm() {
        etFirstName.setText("");
        etLastName.setText("");
        etPhone.setText("");
        etEmail.setText("");
        etIncidentDate.setText("");
        etDescription.setText("");
        spComplaintType.setSelection(0);
        cbDataPrivacy.setChecked(false);
        Toast.makeText(this, "Nalinis na ang form", Toast.LENGTH_SHORT).show();
    }
}