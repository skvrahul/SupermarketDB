package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.Customer;

public class RemoveCustomerActivity extends AppCompatActivity {
    private String TAG = "remove customer activity";
    AppDatabase db;
    Button delCustomerButton;
    EditText cId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_customer);

        cId = findViewById(R.id.c_id_txt);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        delCustomerButton = findViewById(R.id.c_del_btn);
        delCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cId == null || db == null) {
                    return;
                }
                try {
                    Customer c = db.customerDAO().getCustomer(Integer.parseInt(cId.getText().toString())).get(0);
                    //Log.d(TAG, "onClick: "+c);
                    if (c==null)
                    {
                        throw new Exception();
                    }
                    db.customerDAO().delEmployee(Integer.parseInt(cId.getText().toString()));

                    Toast.makeText(getApplicationContext(), "Customer " + cId.getText().toString() + " has been successfully deleted", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to delete customer!!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
