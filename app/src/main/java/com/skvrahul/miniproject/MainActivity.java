package com.skvrahul.miniproject;

import android.appwidget.AppWidgetManager;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private EditText empID;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        empID = findViewById(R.id.emp_id_et);
        addDummyData();
    }
    public void addDummyData(){
        if(db==null){
            return;
        }
        try {
            //Inserting a few Dummy employees
            Employee e1 = new Employee("Ram", 12212);
            Employee e2 = new Employee("Joseph", 17665);
            Employee e3 = new Employee("Lucy", 377328);
            Employee e4 = new Employee("Tina", 464739);
            Employee e5 = new Employee("Jack", 464736);
            db.employeeDAO().insertAll(e1, e2, e3, e4, e5);
        }catch(Exception e){
            Log.i(TAG, "addDummyData: Already added data.Skipping....");

        }
    }
    public void customerLoginClicked(View view){
        //'Customer Login' Button Clicked
        //Handle the button click below
        if(empID==null || db == null){
            return;
        }
        try{
            int eID = Integer.parseInt(empID.getText().toString());
            Employee emp = db.employeeDAO().getEmployee(eID).get(0);
            Toast.makeText(this, "Welcome "+emp.getEmpName(),Toast.LENGTH_LONG).show();
            //TODO: Launch the checkout Screen here
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Unable to login. Please check your ID!",Toast.LENGTH_LONG).show();
        }
    }
}