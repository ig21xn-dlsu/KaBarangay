package com.itismob.s03.group2.KaBarangay.activities;

import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.itismob.s03.group2.KaBarangay.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText email = findViewById(R.id.etEmail);
        EditText password = findViewById(R.id.etPassword);

        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            if (email.getText().toString().trim().isEmpty()) {
                email.setError("Ilagay ang email o mobile number");
                return;
            }
            if (password.getText().toString().trim().isEmpty()) {
                password.setError("Ilagay ang password");
                return;
            }
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });

        findViewById(R.id.btnSignup).setOnClickListener(v ->
                startActivity(new Intent(this, SignupActivity.class)));
        findViewById(R.id.tvForgotPassword).setOnClickListener(v ->
                Toast.makeText(this, "Prototype: password recovery is not connected yet.", Toast.LENGTH_SHORT).show());
    }
}