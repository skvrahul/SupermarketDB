package com.skvrahul.miniproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.skvrahul.miniproject.R;

public class CustomerManagementActivity extends AppCompatActivity {
    private String TAG = "CustomerManagementActivity";
    Button addCustomerButton;
    Button removeCustomerButton;
    Button viewCustomerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_mgt);
        addCustomerButton = findViewById(R.id.add_customer_btn);
        removeCustomerButton = findViewById(R.id.rem_customer_btn);
        viewCustomerButton = findViewById(R.id.info_customer_btn);
        addCustomerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start NewActivity.class
                Intent i = new Intent(CustomerManagementActivity.this,
                        AddCustomerActivity.class);
                startActivity(i);
            }
        });
        removeCustomerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start NewActivity.class
                Intent i = new Intent(CustomerManagementActivity.this,
                        RemoveCustomerActivity.class);
                startActivity(i);
            }
        });
        viewCustomerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerManagementActivity.this,viewListOfCustomersActivity.class);
                startActivity(i);

            }
        });
    }
}
