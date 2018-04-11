package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.DAO.CategoryDAO;
import com.skvrahul.miniproject.DAO.CustomerDAO;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.adapters.CategoryAdapter;
import com.skvrahul.miniproject.adapters.CustomerAdapter;
import com.skvrahul.miniproject.models.Category;
import com.skvrahul.miniproject.models.Customer;

import java.util.ArrayList;

public class ViewListOfCategoriesActivity extends AppCompatActivity {
    private String TAG = "list of categories";
    private AppDatabase db;
    private Button listCategoriesButton;
    RecyclerView categoryRV;
    CategoryAdapter adapter;
    ArrayList<Category> categories = new ArrayList<Category>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_of_categories);

        //Loading Views
        listCategoriesButton = (Button) findViewById(R.id.cat_list_refresh_btn);
        categoryRV = findViewById(R.id.cat_RV);

        //Fetching the DB
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        final CategoryDAO catDAO = db.categoryDAO();
        categories = (ArrayList<Category>) catDAO.getAllCategories();
        Log.i(TAG, "onCreate: "+categories.size());
        CategoryAdapter.DeleteClickListener deleteClickListener = new CategoryAdapter.DeleteClickListener() {
            @Override
            public void onClick(Category item) {
                db.categoryDAO().delCategory(item.getCat_id());
                int i = categories.indexOf(item);
                categories.remove(item);
                adapter.notifyItemRemoved(i);
            }
        };
        //Setting the Adapter
        adapter = new CategoryAdapter(categories, deleteClickListener);
        categoryRV.setAdapter(adapter);
        categoryRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        categoryRV.setItemAnimator(new DefaultItemAnimator());

        //'Refresh' OnClick
        listCategoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categories.clear();
                categories.addAll(catDAO.getAllCategories());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
