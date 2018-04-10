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
import com.skvrahul.miniproject.models.Category;
import com.skvrahul.miniproject.models.Customer;

public class AddCategoryActivity extends AppCompatActivity {
    private AppDatabase db;
    private EditText catId;
    private EditText catName;
    private Button addCatButton;
    private String TAG = "Add Category Acivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        catId = findViewById(R.id.cat_id_txt);
        catName = findViewById(R.id.cat_name_txt);
        addCatButton = findViewById(R.id.add_cat_btn);
        addCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (catId == null ) {
                    Log.i(TAG, "onClick: NULL");
                    return;
                }
                try {

                    Category cat = new Category(Integer.parseInt(catId.getText().toString()), catName.getText().toString());
                    db.categoryDAO().insert(cat);
                    Toast.makeText(getApplicationContext(), "Category " + catName.getText().toString() + "has been successfully added", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to add Category!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
