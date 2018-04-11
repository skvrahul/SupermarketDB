package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.DAO.InvoiceDAO;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.CartItem;
import com.skvrahul.miniproject.models.Contains;
import com.skvrahul.miniproject.models.Invoice;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
    DecimalFormat decf;
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
        decf = new DecimalFormat("##.##");
        decf.setRoundingMode(RoundingMode.DOWN);

        totalTV.setText("The total bill is Rs."+decf.format(totalBill));
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
                    sendBillSMS(c_id);
                    startActivity(i);
                }catch (Exception e){
                    displayMessage("There was an error creating the invoice. Check your customer ID!");
                }
            }
        });



    }
    private void displayMessage(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }
    private void sendBillSMS(int c_id){
        String number = db.customerDAO().getCustomer(c_id).get(0).getPhoneNum();
        if(number.length()<3){
            return;
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        String message = generateBillString(c_id);
        String BASE_URL ="http://api.msg91.com/api/sendhttp.php?sender=MSGIND&route=4&authkey=207961Af2g1m2sbRri5ac749b7&country=0";
        Uri uri = Uri.parse(BASE_URL).buildUpon().appendQueryParameter("message", message).appendQueryParameter("mobiles",number.toString()).build();
        String url = uri.toString();
        Log.i(TAG, "Request = : "+url);


// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i(TAG, "Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                displayMessage("Error Sending Message");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    private String generateBillString(int c_id){
        String name = db.customerDAO().getCustomer(c_id).get(0).getCustName();
        StringBuilder bill = new StringBuilder("Hi "+name+" your bill total is "+decf.format(totalBill)+"\n");
        for(int i=0;i<cartItems.size();i++){
            CartItem it = cartItems.get(i);
            bill = bill.append(" - "+it.getItem().getItemName()+" Rs."+it.getItem().getPrice()+""+" -  "+it.getQty()+" Units \n");
        }
        return bill.toString();
    }
}
