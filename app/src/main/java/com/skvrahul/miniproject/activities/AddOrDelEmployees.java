package com.skvrahul.miniproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.app.Activity;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.*;

public class AddOrDelEmployees extends AppCompatActivity {
    private String TAG = "AddOrRemoveEmployeesActivity";
    Button addEmployeeButton;
    Button removeEmployeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_del_employees);
        addEmployeeButton = findViewById(R.id.add_employee_btn);
        removeEmployeeButton = findViewById(R.id.rem_employee_btn);
        addEmployeeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent i = new Intent(AddOrDelEmployees.this,
                        AddEmployeeActivity.class);
                startActivity(i);
            }
        });
        removeEmployeeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent i = new Intent(AddOrDelEmployees.this,
                        RemoveEmployeeActivity.class);
                startActivity(i);
            }
        });
    }
}
