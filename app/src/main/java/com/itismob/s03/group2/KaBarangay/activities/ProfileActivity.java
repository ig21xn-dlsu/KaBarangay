package com.itismob.s03.group2.KaBarangay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.itismob.s03.group2.KaBarangay.R;

public class ProfileActivity extends AppCompatActivity {

    private LinearLayout navSaklolo, navTahanan;
    private TextView btnViewID, btnReplaceID, btnOfficeSchedule;
    private MaterialButton btnUploadID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        initViews();
        setupTopAndBottomBar();
        setupListeners();
    }

    private void initViews() {
        navSaklolo = findViewById(R.id.navSaklolo);
        navTahanan = findViewById(R.id.navTahanan);

        btnViewID = findViewById(R.id.btnViewID);
        btnReplaceID = findViewById(R.id.btnReplaceID);
        btnUploadID = findViewById(R.id.btnUploadID);
        btnOfficeSchedule = findViewById(R.id.btnOfficeSchedule);
    }

    private void setupTopAndBottomBar() {
        // Set Top Bar Screen Title to Citizen Profile
        TextView tvBarangayHome = findViewById(R.id.tvBarangayHome);
        if (tvBarangayHome != null) {
            tvBarangayHome.setText("Citizen Profile");
        }

        // Set Bottom Navigation Active State for Profile Tab
        ImageView ivTahananIcon = findViewById(R.id.ivTahananIcon);
        TextView tvTahananText = findViewById(R.id.tvTahananText);
        ImageView ivProfileIcon = findViewById(R.id.ivProfileIcon);
        TextView tvProfileText = findViewById(R.id.tvProfileText);

        int activeColor = ContextCompat.getColor(this, R.color.nav_green_active);
        int inactiveColor = ContextCompat.getColor(this, R.color.nav_green_inactive);

        if (ivTahananIcon != null && tvTahananText != null) {
            ivTahananIcon.setColorFilter(inactiveColor);
            tvTahananText.setTextColor(inactiveColor);
        }

        if (ivProfileIcon != null && tvProfileText != null) {
            ivProfileIcon.setColorFilter(activeColor);
            tvProfileText.setTextColor(activeColor);
        }
    }

    private void setupListeners() {
        // Bottom Navigation: Saklolo (Hotlines)
        if (navSaklolo != null) {
            navSaklolo.setOnClickListener(v -> 
                startActivity(new Intent(ProfileActivity.this, HotlineActivity.class))
            );
        }

        // Bottom Navigation: Tahanan (Home)
        if (navTahanan != null) {
            navTahanan.setOnClickListener(v -> 
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class))
            );
        }

        // Action: View Uploaded ID
        if (btnViewID != null) {
            btnViewID.setOnClickListener(v -> 
                Toast.makeText(this, "Ipinapakita ang naipasa na PhilID_DelaCruz", Toast.LENGTH_SHORT).show()
            );
        }

        // Action: Replace Uploaded ID
        if (btnReplaceID != null) {
            btnReplaceID.setOnClickListener(v -> 
                Toast.makeText(this, "Pumili ng bagong dokumento para sa beripikasyon", Toast.LENGTH_SHORT).show()
            );
        }

        // Action: Upload Valid ID Button
        if (btnUploadID != null) {
            btnUploadID.setOnClickListener(v -> 
                Toast.makeText(this, "Mag-browse ng ID File (JPG, PNG, PDF)", Toast.LENGTH_SHORT).show()
            );
        }

        // Action: Office Schedule Information
        if (btnOfficeSchedule != null) {
            btnOfficeSchedule.setOnClickListener(v -> 
                Toast.makeText(this, "Bukas ang Desk 2 Lunes - Biyernes, 8:00 AM - 5:00 PM", Toast.LENGTH_LONG).show()
            );
        }
    }
}