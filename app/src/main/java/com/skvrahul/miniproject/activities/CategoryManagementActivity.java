package com.skvrahul.miniproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skvrahul.miniproject.R;

public class CategoryManagementActivity extends AppCompatActivity {
    Button addCategoryButton;
    Button remCategoryButton;
    Button listOfCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);
        addCategoryButton = findViewById(R.id.add_cat_btn);
        remCategoryButton = findViewById(R.id.rem_cat_btn);
        listOfCategories = findViewById(R.id.cat_view_btn);
        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryManagementActivity.this,AddCategoryActivity.class);
                startActivity(i);
            }
        });
        remCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryManagementActivity.this,RemoveCategoryActivity.class);
                startActivity(i);
            }
        });
        listOfCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryManagementActivity.this,ViewListOfCategoriesActivity.class);
                startActivity(i);
            }
        });
    }
}
