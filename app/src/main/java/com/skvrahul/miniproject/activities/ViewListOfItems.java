package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.DAO.InventoryDAO;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.adapters.EmployeeAdapter;
import com.skvrahul.miniproject.adapters.ItemAdapter;
import com.skvrahul.miniproject.models.Inventory;

import java.util.ArrayList;

public class ViewListOfItems extends AppCompatActivity {
    private String TAG = "Item list";
    private AppDatabase db;
    private Button listItemsButton;

    RecyclerView itemRV;
    ItemAdapter adapter;
    ArrayList<Inventory> items = new ArrayList<Inventory>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_of_items);


        //Loading Views
        listItemsButton = (Button) findViewById(R.id.i_list_refresh_btn);
        itemRV = findViewById(R.id.i_RV);

        //Fetching the DB
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        final InventoryDAO iDAO = db.inventoryDAO();
        items = (ArrayList<Inventory>) iDAO.getAllInventories();
        ItemAdapter.DeleteClickListener deleteClickListener = new ItemAdapter.DeleteClickListener() {
            @Override
            public void onClick(Inventory item) {
                db.inventoryDAO().delItem(item.getItem_id());
                int i = items.indexOf(item);
                items.remove(item);
                adapter.notifyItemRemoved(i);
            }
        };

        //Setting the Adapter
        adapter = new ItemAdapter(items);
        itemRV.setAdapter(adapter);
        itemRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        itemRV.setItemAnimator(new DefaultItemAnimator());

        //'Refresh' OnClick
        listItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.clear();
                items.addAll(iDAO.getAllInventories());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
