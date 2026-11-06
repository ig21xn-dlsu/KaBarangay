package com.itismob.s03.group2.KaBarangay.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.itismob.s03.group2.KaBarangay.R;

import java.util.Calendar;

public class BookingRequestActivity extends AppCompatActivity {
	public static final String EXTRA_EQUIPMENT_TITLE = "equipment_title";
	private TextView tvDate;
	private TextView tvTime;
	private TextView tvFileName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_booking_request);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});

		TextView selectedEquipment = findViewById(R.id.tvSelectedEquipment);
		selectedEquipment.setText(getIntent().getStringExtra(EXTRA_EQUIPMENT_TITLE));
		findViewById(R.id.btnBack).setOnClickListener(v -> finish());
		tvDate = findViewById(R.id.tvBookingDate);
		tvTime = findViewById(R.id.tvBookingTime);
		tvFileName = findViewById(R.id.tvSelectedFile);
		findViewById(R.id.btnChooseDate).setOnClickListener(v -> chooseDate());
		findViewById(R.id.btnChooseTime).setOnClickListener(v -> chooseTime());
		findViewById(R.id.btnUploadDocument).setOnClickListener(v -> openDocumentPicker());
		findViewById(R.id.btnSubmitBooking).setOnClickListener(v -> submitRequest());
	}

	private void chooseDate() {
		Calendar now = Calendar.getInstance();
		new DatePickerDialog(this, (view, year, month, day) -> {
			String date = String.format("%02d/%02d/%04d", month + 1, day, year);
			tvDate.setText(date);
			((TextView) findViewById(R.id.btnChooseDate)).setText("Petsa\n" + date);
		    },
				now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).show();
	}

	private void chooseTime() {
		Calendar now = Calendar.getInstance();
		new TimePickerDialog(this, (view, hour, minute) -> {
			String time = String.format("%02d:%02d", hour, minute);
			tvTime.setText(time);
			((TextView) findViewById(R.id.btnChooseTime)).setText("Oras\n" + time);
		    },
				now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false).show();
	}

	private void openDocumentPicker() {
		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
		intent.setType("application/pdf");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		startActivityForResult(intent, 44);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 44 && resultCode == RESULT_OK && data != null) {
			Uri uri = data.getData();
			if (uri != null) tvFileName.setText(uri.getLastPathSegment());
		}
	}

	private void submitRequest() {
		EditText purpose = findViewById(R.id.etPurpose);
		if (purpose.getText().toString().trim().isEmpty()) {
			purpose.setError("Ilagay ang layunin ng request");
			return;
		}
		new AlertDialog.Builder(this)
				.setTitle("Naisumite ang request")
				.setMessage("Prototype lamang ito. Ipapasa ang iyong equipment request para sa review ng Barangay Property Custodian.")
				.setPositiveButton("Sige", (dialog, which) -> finish())
				.show();
	}
}
