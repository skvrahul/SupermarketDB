package com.skvrahul.miniproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.CartItem;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity {
    private ArrayList<CartItem> cartItems;
    private String TAG = "BillActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        cartItems = (ArrayList<CartItem>) getIntent().getSerializableExtra("cartItems");
        for(int i=0;i<cartItems.size();i++){
            CartItem ci = cartItems.get(i);
            Log.i(TAG, "Bill "+ci.getItem().getItemName()+"  "+ci.getQty()+" Units");
        }
    }
}
