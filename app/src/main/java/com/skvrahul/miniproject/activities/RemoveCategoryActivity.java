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

public class RemoveCategoryActivity extends AppCompatActivity {
    AppDatabase db;
    private String TAG="Remove Category Activity";
    private Button removeCategoryButton;
    private EditText catId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_category);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        catId = findViewById(R.id.cat_id_txt);
        removeCategoryButton = findViewById(R.id.del_cat_btn);
        removeCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (catId == null || db == null) {
                    return;
                }
                try {
                    db.categoryDAO().delCategory(Integer.parseInt(catId.getText().toString()));

                    Toast.makeText(getApplicationContext(), "Category " + catId.getText().toString() + "has been successfully deleted", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to delete Category!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
