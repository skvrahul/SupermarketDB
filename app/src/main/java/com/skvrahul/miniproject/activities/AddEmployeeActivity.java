package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.*;

public class AddEmployeeActivity extends AppCompatActivity {
    AppDatabase db;
    private EditText name;
    private EditText salary;
    private EditText DateOfEmployment;
    private EditText phoneNumber;
    private EditText eId;
    private Button addEmployeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        name = findViewById(R.id.e_name_txt);
        salary = findViewById(R.id.e_sal_txt);
        eId = findViewById(R.id.e_id_txt);
        phoneNumber = findViewById(R.id.e_ph_txt);
        addEmployeeButton = findViewById(R.id.add_employee_btn);
        DateOfEmployment = findViewById(R.id.e_date_txt);


        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (eId == null || db == null) {
                    return;
                }
                try {

                    Employee e = new Employee(name.getText().toString(), Integer.parseInt(eId.getText().toString()));
                    db.employeeDAO().insert(e);
                    Toast.makeText(getApplicationContext(), "Employee " + name.getText().toString() + "has been successfully added", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to add employee!!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}