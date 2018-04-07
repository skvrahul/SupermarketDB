package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.DAO.EmployeeDAO;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.adapters.EmployeeAdapter;

import com.skvrahul.miniproject.models.Employee;
import java.util.ArrayList;

public class viewListOfEmployees extends AppCompatActivity {

    private String TAG = "Employee list";
    private AppDatabase db;
    private Button listEmployeesButton;

    RecyclerView employeeRV;
    EmployeeAdapter adapter;
    ArrayList<Employee> employees = new ArrayList<Employee>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_of_employees);

        //Loading Views
        listEmployeesButton = (Button) findViewById(R.id.list_refresh_btn);
        employeeRV = findViewById(R.id.e_RV);

        //Fetching the DB
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        final EmployeeDAO eDAO = db.employeeDAO();
        employees = (ArrayList<Employee>) eDAO.getAllEmployees();

        //Setting the Adapter
        adapter = new EmployeeAdapter(employees);
        employeeRV.setAdapter(adapter);
        employeeRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        employeeRV.setItemAnimator(new DefaultItemAnimator());

        //'Refresh' OnClick
        listEmployeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employees.clear();
                employees.addAll(eDAO.getAllEmployees());
                adapter.notifyDataSetChanged();
            }
        });

    }
}
