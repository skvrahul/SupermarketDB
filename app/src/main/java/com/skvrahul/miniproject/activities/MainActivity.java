package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.*;

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
        empID.setText("12212");
        addDummyData();
    }
    public void addDummyData(){
        if(db==null){
            return;
        }
        try {
            //Inserting dummy items into inventory
            Inventory i1 = new Inventory();
            i1.setItem_id("127");
            i1.setCat_id(44);
            i1.setItemName("Mango");
            i1.setStock(4);
            i1.setPrice(43.4);


            Inventory i2 = new Inventory();
            i2.setItem_id("126");
            i2.setCat_id(44);
            i2.setItemName("Avacado");
            i2.setStock(12);
            i2.setPrice(158.3);
            //72527273070
            Inventory i3 = new Inventory();
            i3.setItem_id("72527273070");
            i3.setCat_id(41);
            i3.setItemName("Lays Chips");
            i3.setStock(123);
            i3.setPrice(10);
            db.inventoryDAO().insertAll(i1,i2, i3);


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
    private void addTrigger(){
        String triggerQuery = "CREATE TRIGGER  IF NOT EXISTS inventory_update_trigger INSERT ON Invoice\n" +
                "BEGIN \n" +
                "    UPDATE TABLE inventory SET stock = stock - :new.\n" +
                "END";
    }
    public void customerLoginClicked(View view){
        //'Customer Login' Button Clicked
        //Handle the button click below
        if(empID==null || db == null){
            return;
        }
        try{
            int eID = Integer.parseInt(empID.getText().toString());
            Employee emp = db.employeeDAO().getEmployee(eID);
            Toast.makeText(this, "Welcome "+emp.getEmpName(),Toast.LENGTH_LONG).show();
            String ename = emp.getEmpName();
            Intent i = new Intent(this, LoggedInActivity.class);

            //Intent i = new Intent(this, AddItemsActivity.class);
            //i.putExtra("name", ename);
            startActivity(i);

            //TODO: Launch the checkout Screen here
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Unable to login. Please check your ID!",Toast.LENGTH_LONG).show();
        }
    }
}