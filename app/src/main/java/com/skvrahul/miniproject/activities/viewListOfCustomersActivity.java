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
import com.skvrahul.miniproject.DAO.CustomerDAO;
import com.skvrahul.miniproject.DAO.EmployeeDAO;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.adapters.CustomerAdapter;
import com.skvrahul.miniproject.models.Customer;
import com.skvrahul.miniproject.models.Employee;

import java.util.ArrayList;

public class viewListOfCustomersActivity extends AppCompatActivity {
    private String TAG = "Customer list";
    private AppDatabase db;
    private Button listCustomersButton;

    RecyclerView customerRV;
    CustomerAdapter adapter;
    ArrayList<Customer> customers = new ArrayList<Customer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_of_customers);

        //Loading Views
        listCustomersButton = (Button) findViewById(R.id.c_list_refresh_btn);
        customerRV = findViewById(R.id.c_RV);

        //Fetching the DB
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        final CustomerDAO cDAO = db.customerDAO();
        customers = (ArrayList<Customer>) cDAO.getAllCustomers();

        //Setting the Adapter
        adapter = new CustomerAdapter(customers);
        customerRV.setAdapter(adapter);
        customerRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        customerRV.setItemAnimator(new DefaultItemAnimator());

        //'Refresh' OnClick
        listCustomersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customers.clear();
                customers.addAll(cDAO.getAllCustomers());
                adapter.notifyDataSetChanged();
            }
        });

    }

}
