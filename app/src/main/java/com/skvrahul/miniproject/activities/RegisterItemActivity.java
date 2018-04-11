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

public class RegisterItemActivity extends AppCompatActivity {
    AppDatabase db;
    Button addItemButton;
    private EditText itemId;
    private EditText itemName;
    private EditText itemCost;
    private EditText itemQuantity;
    private EditText itemCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_item);
        addItemButton = findViewById(R.id.i_regitem_btn);
        itemName = findViewById(R.id.i_name_txt);
        itemQuantity = findViewById(R.id.i_qty_txt);
        itemCat = findViewById(R.id.i_cat_txt);
        itemCost = findViewById(R.id.i_price_txt2);
        itemId = findViewById(R.id.i_id_txt);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemId==null)
                {
                    return;
                }
                try {


                    Inventory i = new Inventory( itemId.getText().toString(),itemName.getText().toString(),Double.parseDouble(itemCost.getText().toString()),Integer.parseInt(itemQuantity.getText().toString()),Integer.parseInt(itemCat.getText().toString()));

                    db.inventoryDAO().insert(i);
                    Toast.makeText(getApplicationContext(), "item " + itemName.getText().toString() + " has been successfully added", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to add item!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
