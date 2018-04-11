package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.Employee;

public class RemoveEmployeeActivity extends AppCompatActivity {
    private String TAG = "remove employee activity";
    AppDatabase db;
    Button delEmployeeButton;
    EditText eId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_employee);
        delEmployeeButton = findViewById(R.id.e_del_btn);
        eId = findViewById(R.id.e_id_txt);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        delEmployeeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (eId == null || db == null) {
                    return;
                }
                try {
                    Employee e = db.employeeDAO().getEmployee(Integer.parseInt(eId.getText().toString()));
                    //Log.d(TAG, "onClick: "+e);
                    if (e==null)
                    {
                        throw new Exception();
                    }
                    db.employeeDAO().delEmployee(Integer.parseInt(eId.getText().toString()));

                    Toast.makeText(getApplicationContext(), "Employee " + eId.getText().toString() + " has been successfully deleted", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to delete employee!!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
