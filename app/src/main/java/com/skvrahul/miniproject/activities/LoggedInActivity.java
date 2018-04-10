package com.skvrahul.miniproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;
import com.skvrahul.miniproject.R;


public class LoggedInActivity extends AppCompatActivity {
    Button employeeManagementButton;
    Button customerManagementButton;
    Button inventoryManagementButton;
    Button categoryManagementButton;
    Button billingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        employeeManagementButton = findViewById(R.id.employeemgt_btn);
        customerManagementButton = findViewById(R.id.customermgt_btn);
        inventoryManagementButton = findViewById(R.id.inventorymgt_btn);
        categoryManagementButton = findViewById(R.id.categorymgt_btn);
        billingButton = findViewById(R.id.billing_btn);
        /*
        employeeManagementButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, AddOrDelEmployees.class);
                startActivity(i);
            }
        });
        */

        customerManagementButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoggedInActivity.this, CustomerManagementActivity.class);
                startActivity(i);
            }
        });

        employeeManagementButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoggedInActivity.this, AddOrDelEmployees.class);
                startActivity(i);
            }
        });
        billingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoggedInActivity.this, AddItemsActivity.class);
                startActivity(i);
            }
        });
        inventoryManagementButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoggedInActivity.this,InventoryManagementActivity.class);
                startActivity(i);
            }
        });
        categoryManagementButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoggedInActivity.this,CategoryManagementActivity.class);
                startActivity(i);
            }
        });
    }
}
