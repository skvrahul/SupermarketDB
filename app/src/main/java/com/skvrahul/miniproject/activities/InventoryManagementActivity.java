package com.skvrahul.miniproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.Inventory;

public class InventoryManagementActivity extends AppCompatActivity {
    Button addItemButton;
    Button remItemButton;
    Button viewItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_management);
        addItemButton = findViewById(R.id.add_item_btn);
        remItemButton = findViewById(R.id.rem_item_btn);
        viewItemButton = findViewById(R.id.item_view_btn);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InventoryManagementActivity.this,RegisterItemActivity.class);
                startActivity(i);
            }
        });
        remItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InventoryManagementActivity.this,RemoveItemActivity.class);
                startActivity(i);

            }
        });
        viewItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(InventoryManagementActivity.this,.class);
                //startActivity(i);
            }
        });
    }
}
