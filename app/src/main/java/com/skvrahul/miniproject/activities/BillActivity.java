package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.DAO.InvoiceDAO;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.CartItem;
import com.skvrahul.miniproject.models.Contains;
import com.skvrahul.miniproject.models.Invoice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BillActivity extends AppCompatActivity {
    private ArrayList<CartItem> cartItems;
    private String TAG = "BillActivity";
    AppDatabase db;
    TextView totalTV;
    EditText custID;
    int invoice_id;
    double totalBill;
    ArrayList<Contains> invoiceContains;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        totalTV = findViewById(R.id.bill_total_tv);
        custID = findViewById(R.id.bill_c_id_et);


        cartItems = (ArrayList<CartItem>) getIntent().getSerializableExtra("cartItems");
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        totalBill = 0;
        invoiceContains = new ArrayList<>();
        invoice_id = 0;
        try{
            invoice_id = db.invoiceDAO().getMaxID() + 1;
        }catch (Exception e){

        }

        for(int i=0;i<cartItems.size();i++){
            CartItem ci = cartItems.get(i);
            totalBill+=ci.getQty()*ci.getItem().getPrice();
            Contains c = new Contains(invoice_id, ci.getItem().getItem_id(),ci.getQty());
            invoiceContains.add(c);
        }
        totalTV.setText("The total bill is Rs."+totalBill);
    }
    public void submitClicked(View view){

        if(cartItems.size()==0 || cartItems==null){
            displayMessage("No items in cart!");
            return;
        }
        //Getting SharedPref
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        final int c_id;
        try{
            c_id = Integer.parseInt(custID.getText().toString());
        }catch (Exception e){
            displayMessage("Please enter customer ID in correct format!");
            return;
        }
        final InvoiceDAO invoiceDAO = db.invoiceDAO();
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(date);
        final Intent i = new Intent(this, LoggedInActivity.class);
        db.runInTransaction(new Runnable() {
            @Override
            public void run() {
                try {

                    invoiceDAO.insert(new Invoice(invoice_id, pref.getInt("empID", 12212), c_id, formattedDate, totalBill));
                    for (int i = 0; i < invoiceContains.size(); i++) {
                        db.containsDAO().insert(invoiceContains.get(i));
                    }
                    displayMessage("Succesfully finished Billing. You will receive a message shortly");
                    startActivity(i);
                }catch (Exception e){
                    displayMessage("There was an error creating the invoice. Check your customer ID!");
                }
            }
        });



    }
    private void displayMessage(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_LONG).show();
    }
}
