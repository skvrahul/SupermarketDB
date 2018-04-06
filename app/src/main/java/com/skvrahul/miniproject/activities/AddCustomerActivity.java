package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.Employee;

public class AddCustomerActivity extends AppCompatActivity {
    AppDatabase db;
    private EditText name;
    private EditText phoneNumber;
    private EditText cId;
    private Button addCustomerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        name = findViewById(R.id.c_name_txt);

        cId = findViewById(R.id.c_id_txt);
        phoneNumber = findViewById(R.id.c_ph_txt);
        addCustomerButton = findViewById(R.id.c_addcust_btn);


        addCustomerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (cId == null || db == null) {
                    return;
                }
                try {

                    Employee e = new Employee(name.getText().toString(), Integer.parseInt(cId.getText().toString()));
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
