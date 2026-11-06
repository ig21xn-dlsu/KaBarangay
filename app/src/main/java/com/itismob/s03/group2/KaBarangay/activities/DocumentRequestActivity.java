package com.itismob.s03.group2.KaBarangay.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itismob.s03.group2.KaBarangay.R;

public class DocumentRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_request);

        // Back
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Call the Civil Desk (opens the dialer, no permission needed)
        findViewById(R.id.btnCallCivilDesk).setOnClickListener(v -> {
            Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:87001234"));
            startActivity(dial);
        });

        // Document request rows
        setupRequestItem(R.id.itemBarangayClearance, "Barangay Clearance");
        setupRequestItem(R.id.itemCertResidency, "Certificate of Residency");
        setupRequestItem(R.id.itemBusinessClearance, "Barangay Business Clearance");
        setupRequestItem(R.id.itemGoodMoral, "Certificate of Good Moral Character");
        setupRequestItem(R.id.itemIndigency, "Certificate of Indigency");
        setupRequestItem(R.id.itemNoObjection, "Certificate of No Objection");

        // Bottom nav (IDs come from layout_bottom_navigation.xml)
        findViewById(R.id.navTahanan).setOnClickListener(v -> {
            // e.g. startActivity(new Intent(this, HomeActivity.class));
        });
        findViewById(R.id.navSaklolo).setOnClickListener(v -> {
            // e.g. startActivity(new Intent(this, SakloloActivity.class));
        });
        findViewById(R.id.navProfile).setOnClickListener(v -> {
            // e.g. startActivity(new Intent(this, ProfileActivity.class));
        });
    }

    private void setupRequestItem(int viewId, String documentName) {
        View item = findViewById(viewId);
        item.setOnClickListener(v -> {
            // Replace this with the actual application form screen
            // Intent i = new Intent(this, DocumentApplicationActivity.class);
            // i.putExtra("documentType", documentName);
            // startActivity(i);
            Toast.makeText(this, documentName, Toast.LENGTH_SHORT).show();
        });
    }
}