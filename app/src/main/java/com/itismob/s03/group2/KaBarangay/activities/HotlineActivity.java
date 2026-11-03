package com.itismob.s03.group2.KaBarangay.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s03.group2.KaBarangay.R;
import com.itismob.s03.group2.KaBarangay.adapters.HotlineAdapter;
import com.itismob.s03.group2.KaBarangay.utils.HotlineDataGenerator;

public class HotlineActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HotlineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotline);

        recyclerView = findViewById(R.id.rvHotlines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load generated prototype data
        adapter = new HotlineAdapter(HotlineDataGenerator.getHotlines());
        recyclerView.setAdapter(adapter);
    }
}