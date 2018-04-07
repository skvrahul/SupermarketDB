package com.skvrahul.miniproject.adapters;


import com.skvrahul.miniproject.R;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.Customer;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {
    @Override
    public int getItemCount() {
        return Customers.size();
    }

    public interface DeleteClickListener{
        void onClick(Customer item);
    }
    public CustomerAdapter(){}
    private List<Customer> Customers;
    private DeleteClickListener deleteClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name,phoneNumber;


        public MyViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.c_id_view);
            name = view.findViewById(R.id.c_name_view);
            phoneNumber = view.findViewById(R.id.c_phone_view);
            //startDate = view.findViewById(R.id.e_da)
            //delete = view.findViewById(R.id.cart_item_delete);

        }
    }


    public CustomerAdapter(List<Customer> Customers) {
        this.Customers = Customers;
    }

    public CustomerAdapter(List<Customer> Customers, DeleteClickListener deleteClickListener) {
        this.Customers = Customers;
        //this.deleteClickListener = deleteClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View employeeView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_customer_view, parent, false);

        return new MyViewHolder(employeeView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Customer e = Customers.get(position);
        holder.name.setText(e.getCustName());
        holder.id.setText(e.getC_id()+"");
        holder.phoneNumber.setText(e.getPhoneNum());
        Log.d(TAG, "onBindViewHolder: called");
    }


}