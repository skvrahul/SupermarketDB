package com.skvrahul.miniproject.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.skvrahul.miniproject.AppDatabase;
import com.skvrahul.miniproject.DAO.InventoryDAO;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.adapters.CartItemAdapter;
import com.skvrahul.miniproject.models.CartItem;
import com.skvrahul.miniproject.models.Inventory;

import java.util.ArrayList;
import java.util.List;

public class AddItemsActivity extends AppCompatActivity {
    TextView empName;
    EditText prodID;
    RecyclerView cartRV;
    CartItemAdapter adapter;
    ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    private AppDatabase db;
    private String TAG = "AddItemsActivity";
    private int REQUEST_CODE = 44353;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-db").allowMainThreadQueries().build();
        empName = findViewById(R.id.add_items_name_tv);
        prodID = findViewById(R.id.add_items_pid_et);
        String name = getIntent().getStringExtra("name");
        empName.setText("Logged in as ");
//        empName.append(name);
        cartRV = findViewById(R.id.add_items_rv);
        CartItemAdapter.DeleteClickListener deleteClickListener = new CartItemAdapter.DeleteClickListener() {
            @Override
            public void onClick(CartItem item) {
                //Deleting the item from the Cart
                cartItems.remove(item);
                adapter.notifyDataSetChanged();
            }
        };

        //Binding the RecyclerView to the Adapter
        adapter = new CartItemAdapter(cartItems, deleteClickListener);
        cartRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cartRV.setItemAnimator(new DefaultItemAnimator());
        cartRV.setAdapter(adapter);
    }
    public boolean checkAvailable(int qty, String pid){
        if(db == null){
            return false;
        }
        InventoryDAO invDAO = db.inventoryDAO();
        try{
            Inventory item = invDAO.getInventory(pid).get(0);
            if(item.getStock()>=qty){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public void addProductClicked(View view){
        //Add Product clicked
        //TODO: Launch a dialog asking qty to be added to the cart
        addItem();
    }
    public void addItem(){
        if(db == null){
            return;
        }
        String productID = prodID.getText().toString();
        productID = productID.trim();

        InventoryDAO invDAO = db.inventoryDAO();

        //Searching in current cart to increment qty
        for(int i=0;i<cartItems.size();i++){
            CartItem ci = cartItems.get(i);
            if(ci.getItem().getItem_id().equals(productID)){
                boolean inStock = checkAvailable(ci.getQty()+1,productID);
                if(inStock){
                    ci.incrementQty();
                    Toast.makeText(this, "Qty of "+ci.getItem().getItemName()+" increased by one...",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Sorry more units are not available",Toast.LENGTH_LONG).show();
                }
                adapter.notifyDataSetChanged();
                return;
            }
        }


        //Not in current cart so adding a new entry
        try {
            boolean inStock = checkAvailable(1,productID);
            Inventory item = invDAO.getInventory(productID).get(0);
            if (inStock) {
                cartItems.add(new CartItem(item));
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Sorry this product is currently not in stock", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "Please check your product id!", Toast.LENGTH_LONG).show();
        }
    }
    public void submitClicked(View view){
        if(cartItems==null){
            return;
        }
        Intent i = new Intent(this, BillActivity.class);
        i.putExtra("cartItems", cartItems);
        startActivity(i);
    }
    public void scanBarcodeClicked(View view){
        Intent intent = new Intent(AddItemsActivity.this, ScanCodeActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null && requestCode == REQUEST_CODE){
            String id = data.getStringExtra("product_id");
            prodID.setText(id);
            addItem();
        }
    }
}
