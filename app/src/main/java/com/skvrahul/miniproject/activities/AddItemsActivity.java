package com.skvrahul.miniproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.skvrahul.miniproject.R;

public class AddItemsActivity extends AppCompatActivity {
    TextView empName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        empName = findViewById(R.id.add_items_name_tv);
        String name = getIntent().getStringExtra("name");
        empName.setText("Logged in as ");
        empName.append(name);

    }
}
