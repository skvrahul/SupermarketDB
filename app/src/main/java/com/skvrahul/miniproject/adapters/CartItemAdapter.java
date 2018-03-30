package com.skvrahul.miniproject.adapters;

/**
 * Created by skvrahul on 30/3/18.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.CartItem;
import com.skvrahul.miniproject.models.Inventory;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.MyViewHolder> {
    public interface DeleteClickListener{
        void onClick();
    }
    private List<CartItem> cartItems;
    private DeleteClickListener deleteClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, units;
        public ImageView delete, productImage;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.cart_item_name);
            units = (TextView) view.findViewById(R.id.cart_item_units);
            price = (TextView) view.findViewById(R.id.cart_item_price);
        }
    }


    public CartItemAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public CartItemAdapter(List<CartItem> cartItems, DeleteClickListener deleteClickListener) {
        this.cartItems = cartItems;
        this.deleteClickListener = deleteClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.name.setText(item.getItem().getItemName());
        holder.units.setText(item.getQty());
        holder.price.setText(item.getItem().getPrice()+"x"+item.getQty()+"="+(item.getQty()*item.getItem().getPrice()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteClickListener!=null){
                    deleteClickListener.onClick();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}