package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.Inventory;

public class RemoveItemActivity extends AppCompatActivity {
    AppDatabase db;
    Button remItemButton;
    EditText iId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);
        remItemButton = findViewById(R.id.i_del_btn);
        iId = findViewById(R.id.i_id_txt);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        remItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (iId == null || db == null) {
                    return;
                }
                try {
                    Inventory i = db.inventoryDAO().getInventory(iId.getText().toString()).get(0);
                    if(i==null)
                    {
                        throw new Exception();
                    }

                    db.inventoryDAO().delItem(iId.getText().toString());

                    Toast.makeText(getApplicationContext(), "Item " + iId.getText().toString() + " has been successfully deleted", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to delete Item!!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
