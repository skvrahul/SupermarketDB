package com.skvrahul.miniproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

import com.skvrahul.miniproject.R;

public class AddOrDelEmployees extends AppCompatActivity {
    private String TAG = "AddOrRemoveEmployeesActivity";
    Button addEmployeeButton;
    Button removeEmployeeButton;
    Button viewEmployeesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_del_employees);
        addEmployeeButton = findViewById(R.id.add_customer_btn);
        removeEmployeeButton = findViewById(R.id.rem_customer_btn);
        viewEmployeesButton = findViewById(R.id.view_employees_btn);
        addEmployeeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start NewActivity.class
                Intent i = new Intent(AddOrDelEmployees.this, AddEmployeeActivity.class);
                startActivity(i);
            }
        });
        removeEmployeeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start NewActivity.class
                Intent i = new Intent(AddOrDelEmployees.this, RemoveEmployeeActivity.class);
                startActivity(i);
            }
        });
        viewEmployeesButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddOrDelEmployees.this,viewListOfEmployees.class);
                startActivity(i);
            }
        });
    }
}
