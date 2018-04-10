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

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private EditText empID;
    private EditText empPass;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        empID = findViewById(R.id.emp_id_et);
        empPass = findViewById(R.id.emp_pass_et);
        empID.setText("12212");
        empPass.setText("admin");
        addDummyData();
        addTrigger();
    }
    public void addDummyData(){
        if(db==null){
            return;
        }
        try {

            //Inserting dummy categories into Category
            Category cat1 = new  Category(1,"Fruit");
            Category cat2 = new  Category(2,"Vegetables");
            Category cat3 = new  Category(3,"Chips");
            Category cat4 = new  Category(4,"Dairy");
            db.categoryDAO().insertAll(cat1,cat2,cat3,cat4);
            //Inserting dummy items into inventory
            Inventory i1 = new Inventory();
            i1.setItem_id("127");
            i1.setCat_id(1);
            i1.setItemName("Mango");
            i1.setStock(4);
            i1.setPrice(43.4);


            Inventory i2 = new Inventory();
            i2.setItem_id("126");
            i2.setCat_id(2);
            i2.setItemName("Avacado");
            i2.setStock(12);
            i2.setPrice(158.3);
            //72527273070
            Inventory i3 = new Inventory();
            i3.setItem_id("72527273070");
            i3.setCat_id(3);
            i3.setItemName("Lays Chips");
            i3.setStock(123);
            i3.setPrice(10);
            db.inventoryDAO().insertAll(i1,i2, i3);


            //Inserting a few Dummy employees
            Employee e1 = new Employee("Ram", 12212, "9481754291",300000.0);
            Employee e2 = new Employee("Joseph", 17665, "9481754299",310000.0);
            Employee e3 = new Employee("Lucy", 377328, "9716851750",350000.0);
            Employee e4 = new Employee("Tina", 464739, "9769832913",400000.0);
            Employee e5 = new Employee("Jack", 464736, "9187399549",410000.0);
            Employee e6 = new Employee("Harvey", 232314, "9505935194",250000);
            Employee e7 = new Employee("Tom", 343423, "9034994396",320000);
            Employee e8 = new Employee("Rita",234578, "9034994396",210000);
            db.employeeDAO().insertAll(e1, e2, e3, e4, e5, e6, e7, e8);

            Customer c1 = new Customer("Richard", 123001, "9164776488");
            Customer c2 = new Customer("John",143312, "9723517978");
            Customer c3 = new Customer("Rohit",1141003, "9466210144");
            Customer c4 = new Customer("Mark", 232814, "9955950818");
            Customer c5 = new Customer("Tony",223465, "9842006400");
            Customer c6 = new Customer("Riya",445676, "9476561035");
            Customer c7 = new Customer("Reeti",876547, "9223478531");
            Customer c8 = new Customer("Tax",832178, "9449383997");
            db.customerDAO().insertAll(c1,c2,c3,c4,c5,c6,c7,c8);

        }catch(Exception e){
            Log.i(TAG, "addDummyData: Already added data.Skipping....");

        }
    }
    private void addTrigger(){
        String triggerQuery = "CREATE TRIGGER  IF NOT EXISTS inventory_update_trigger INSERT ON contains\n" +
                "BEGIN \n" +
                "   UPDATE inventory SET stock = stock - NEW.quantity WHERE item_id=NEW.item_id;"+
                "END";
        db.execQuery(triggerQuery);

    }
    public void customerLoginClicked(View view){
        //'Customer Login' Button Clicked
        //Handle the button click below
        if(empID==null || db == null){
            return;
        }
        try{
            int eID = Integer.parseInt(empID.getText().toString());
            String password = empPass.getText().toString();
            Employee emp = db.employeeDAO().getEmployee(eID);
            if(Objects.equals(emp.getPassword(), password))
            {
                Toast.makeText(this, "Welcome "+emp.getEmpName(),Toast.LENGTH_SHORT).show();
                //String ename = emp.getEmpName();
                Intent i = new Intent(this, LoggedInActivity.class);

                //Intent i = new Intent(this, AddItemsActivity.class);
                //i.putExtra("name", ename);
                startActivity(i);
            }
            else
            {
                throw new Exception();
            }


            //TODO: Launch the checkout Screen here
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Unable to login. Please check your ID! and password",Toast.LENGTH_SHORT).show();
        }
    }
}
