package com.itismob.s03.group2.KaBarangay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s03.group2.KaBarangay.R;
import com.itismob.s03.group2.KaBarangay.adapters.EquipmentAdapter;
import com.itismob.s03.group2.KaBarangay.models.EquipmentItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class EquipmentBookingActivity extends AppCompatActivity {
    private final List<EquipmentItem> allItems = Arrays.asList(
            new EquipmentItem("KAGANAPAN • EVENTS", "Mga Tolda • Canopy Tents", "10x10ft & 10x20ft heavy-duty pop-up and steel pole tarpaulin shelters", "Magagamit (8 Units Ready)", R.drawable.ic_logo_placeholder),
            new EquipmentItem("KAGANAPAN • SEATING", "Mga Upuan • Monoblock Chairs", "Heavy-duty plastic chairs for assemblies, wakes, and family gatherings", "Magagamit (85 Chairs Ready)", R.drawable.ic_logo_placeholder),
            new EquipmentItem("KAGANAPAN • TABLES", "Mga Mesa • Folding & Long Tables", "6ft & 8ft foldable catering tables, registration desks, and buffet surfaces", "Magagamit (18 Tables Ready)", R.drawable.ic_logo_placeholder),
            new EquipmentItem("PALAKASAN • SPORTS", "Mga Bola • Basketball & Volleyball", "Official regulation size 7 basketballs, competition volleyballs, and court nets", "Magagamit (14 Balls Ready)", R.drawable.ic_logo_placeholder),
            new EquipmentItem("IBA PA • MISCELLANEOUS", "Iba Pang Kagamitan • Public Gear", "Portable PA sound systems, wireless mics, wooden podium, emergency cots", "Kasalukuyang Nakare-serba", R.drawable.ic_logo_placeholder)
    );
    private EquipmentAdapter adapter;
    private String selectedCategory = "Lahat (All)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_equipment_booking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.rvEquipment);
        adapter = new EquipmentAdapter(allItems, item -> {
            Intent intent = new Intent(this, BookingRequestActivity.class);
            intent.putExtra(BookingRequestActivity.EXTRA_EQUIPMENT_TITLE, item.getTitle());
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        EditText search = findViewById(R.id.etEquipmentSearch);
        search.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { filterItems(s.toString()); }
            @Override public void afterTextChanged(Editable s) { }
        });

        setupCategory(findViewById(R.id.chipAll), "Lahat (All)");
        setupCategory(findViewById(R.id.chipEvents), "Kaganapan (Events)");
        setupCategory(findViewById(R.id.chipSports), "Palakasan (Sports)");
        findViewById(R.id.btnBackToServices).setOnClickListener(v -> finish());
    }

    private void setupCategory(TextView chip, String category) {
        chip.setOnClickListener(v -> {
            selectedCategory = category;
            filterItems(((EditText) findViewById(R.id.etEquipmentSearch)).getText().toString());
        });
    }

    private void filterItems(String query) {
        String normalizedQuery = query.toLowerCase(Locale.ROOT).trim();
        List<EquipmentItem> filtered = new ArrayList<>();
        for (EquipmentItem item : allItems) {
            boolean matchesCategory = selectedCategory.startsWith("Lahat")
                    || item.getCategory().toLowerCase(Locale.ROOT).contains(selectedCategory.substring(0, 8).toLowerCase(Locale.ROOT));
            boolean matchesSearch = normalizedQuery.isEmpty()
                    || item.getTitle().toLowerCase(Locale.ROOT).contains(normalizedQuery)
                    || item.getDescription().toLowerCase(Locale.ROOT).contains(normalizedQuery);
            if (matchesCategory && matchesSearch) filtered.add(item);
        }
        adapter.updateItems(filtered);
    }
}