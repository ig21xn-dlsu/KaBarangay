package com.itismob.s03.group2.KaBarangay.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.itismob.s03.group2.KaBarangay.R;

public class DocumentReceiptActivity extends AppCompatActivity {

    // Key used by DocumentRequestActivity to pass the clicked document
    public static final String EXTRA_DOCUMENT_TYPE = "documentType";

    private ImageView ivReceiptPreview;
    private View layoutUploadPlaceholder;
    private TextView tvReceiptStatus;
    private CheckBox cbInfoCertification;
    private CheckBox cbRequesterCertification;

    private Uri receiptUri = null;
    private String documentType;

    private final ActivityResultLauncher<String> imagePicker =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    receiptUri = uri;
                    ivReceiptPreview.setImageURI(uri);
                    ivReceiptPreview.setVisibility(View.VISIBLE);
                    layoutUploadPlaceholder.setVisibility(View.GONE);
                    tvReceiptStatus.setText("Napili na ang larawan. Pindutin ulit upang palitan.");
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_receipt);

        // Receive what was clicked and show it in the header
        documentType = getIntent().getStringExtra(EXTRA_DOCUMENT_TYPE);
        if (documentType == null) documentType = "Dokumento";
        ((TextView) findViewById(R.id.tvDocumentTitle)).setText(documentType);

        ivReceiptPreview = findViewById(R.id.ivReceiptPreview);
        layoutUploadPlaceholder = findViewById(R.id.layoutUploadPlaceholder);
        tvReceiptStatus = findViewById(R.id.tvReceiptStatus);
        cbInfoCertification = findViewById(R.id.cbInfoCertification);
        cbRequesterCertification = findViewById(R.id.cbRequesterCertification);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.btnUploadReceipt).setOnClickListener(v -> imagePicker.launch("image/*"));
        findViewById(R.id.btnSubmitReceipt).setOnClickListener(v -> submit());

        // Bottom nav
        findViewById(R.id.navTahanan).setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
        findViewById(R.id.navSaklolo).setOnClickListener(v -> {
            startActivity(new Intent(this, HotlineActivity.class));
        });
        findViewById(R.id.navProfile).setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });
    }

    private void submit() {
        if (receiptUri == null) {
            Toast.makeText(this, "Mag-upload muna ng larawan ng resibo.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!cbInfoCertification.isChecked() || !cbRequesterCertification.isChecked()) {
            Toast.makeText(this, "Lagyan ng tsek ang parehong pagpapatunay.", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: save/upload here. You have:
        //   documentType (String) and receiptUri (Uri)
        Toast.makeText(this, documentType + " - naisumite na ang resibo.", Toast.LENGTH_LONG).show();
        finish();
    }
}


