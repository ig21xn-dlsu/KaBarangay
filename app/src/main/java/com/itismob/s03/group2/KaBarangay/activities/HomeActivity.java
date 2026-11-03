package com.itismob.s03.group2.KaBarangay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.itismob.s03.group2.KaBarangay.R;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout btnDocRequests, btnComplaints, btnEquipment, btnDirectory;
    private LinearLayout navSaklolo, navProfile;
    private ImageView btnPrevAnnouncement, btnNextAnnouncement;
    private TextView tvCarouselCounter, tvAnnouncementBadge, tvAnnouncementTime, tvAnnouncementTitle, tvAnnouncementBody;
    private TextView btnViewStatus;

    private int currentAnnouncementIndex = 1;
    private final int totalAnnouncements = 3;

    // Carousel prototype dataset
    private final String[] badges = {"PUBLIC ADVISORY", "COMMUNITY NOTICE", "HEALTH ADVISORY"};
    private final String[] times = {"Bukas, 7:00 AM", "Linggo, 9:00 AM", "Biyernes, 8:00 AM"};
    private final String[] titles = {
            "Scheduled Barangay Clean-up Drive & Fogging",
            "Barangay General Assembly & Town Hall",
            "Free Medical & Dental Mission 2024"
    };
    private final String[] bodies = {
            "Residents of Purok 1 to 4 are advised that community fogging and drainage clearing will be conducted this Saturday from 7:00 AM to 12:00 PM.",
            "All resident heads of families are invited to attend the quarterly barangay town hall assembly at the Covered Court.",
            "Free health checkups, dental extractions, and vitamins distribution for seniors and children at the Bgy Health Center."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        initViews();
        setupListeners();
    }

    private void initViews() {
        btnDocRequests = findViewById(R.id.btnDocRequests);
        btnComplaints = findViewById(R.id.btnComplaints);
        btnEquipment = findViewById(R.id.btnEquipment);
        btnDirectory = findViewById(R.id.btnDirectory);

        navSaklolo = findViewById(R.id.navSaklolo);
        navProfile = findViewById(R.id.navProfile);

        btnPrevAnnouncement = findViewById(R.id.btnPrevAnnouncement);
        btnNextAnnouncement = findViewById(R.id.btnNextAnnouncement);
        tvCarouselCounter = findViewById(R.id.tvCarouselCounter);

        tvAnnouncementBadge = findViewById(R.id.tvAnnouncementBadge);
        tvAnnouncementTime = findViewById(R.id.tvAnnouncementTime);
        tvAnnouncementTitle = findViewById(R.id.tvAnnouncementTitle);
        tvAnnouncementBody = findViewById(R.id.tvAnnouncementBody);

        btnViewStatus = findViewById(R.id.btnViewStatus);
    }

    private void setupListeners() {
        // Service Card 1: Document Requests
        if (btnDocRequests != null) {
            btnDocRequests.setOnClickListener(v -> 
                startActivity(new Intent(HomeActivity.this, DocumentRequestActivity.class))
            );
        }

        // Service Card 2: Complaints & Blotter
        if (btnComplaints != null) {
            btnComplaints.setOnClickListener(v -> 
                startActivity(new Intent(HomeActivity.this, ComplaintActivity.class))
            );
        }

        // Service Card 3: Equipment Booking
        if (btnEquipment != null) {
            btnEquipment.setOnClickListener(v -> 
                startActivity(new Intent(HomeActivity.this, EquipmentBookingActivity.class))
            );
        }

        // Service Card 4: Barangay Directory
        if (btnDirectory != null) {
            btnDirectory.setOnClickListener(v -> 
                startActivity(new Intent(HomeActivity.this, DirectoryActivity.class))
            );
        }

        // View Active Status Link
        if (btnViewStatus != null) {
            btnViewStatus.setOnClickListener(v -> 
                startActivity(new Intent(HomeActivity.this, DocumentRequestActivity.class))
            );
        }

        // Bottom Navigation: Saklolo (Hotlines)
        if (navSaklolo != null) {
            navSaklolo.setOnClickListener(v -> 
                startActivity(new Intent(HomeActivity.this, HotlineActivity.class))
            );
        }

        // Bottom Navigation: Profile
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> 
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class))
            );
        }

        // Announcement Carousel Controls
        if (btnPrevAnnouncement != null) {
            btnPrevAnnouncement.setOnClickListener(v -> {
                if (currentAnnouncementIndex > 1) {
                    currentAnnouncementIndex--;
                } else {
                    currentAnnouncementIndex = totalAnnouncements;
                }
                updateAnnouncementContent();
            });
        }

        if (btnNextAnnouncement != null) {
            btnNextAnnouncement.setOnClickListener(v -> {
                if (currentAnnouncementIndex < totalAnnouncements) {
                    currentAnnouncementIndex++;
                } else {
                    currentAnnouncementIndex = 1;
                }
                updateAnnouncementContent();
            });
        }
    }

    private void updateAnnouncementContent() {
        int idx = currentAnnouncementIndex - 1;

        if (tvCarouselCounter != null) {
            tvCarouselCounter.setText("Item " + currentAnnouncementIndex + " of " + totalAnnouncements);
        }
        if (tvAnnouncementBadge != null) {
            tvAnnouncementBadge.setText(badges[idx]);
        }
        if (tvAnnouncementTime != null) {
            tvAnnouncementTime.setText(times[idx]);
        }
        if (tvAnnouncementTitle != null) {
            tvAnnouncementTitle.setText(titles[idx]);
        }
        if (tvAnnouncementBody != null) {
            tvAnnouncementBody.setText(bodies[idx]);
        }
    }
}